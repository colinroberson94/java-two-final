package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UsersQuery {
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users user = new Users(userId, userName, password);

                usersObservableList.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usersObservableList;
    }

    public static boolean verifyUser(String username, String password) {
        try {
            String sql = "SELECT * FROM USERS WHERE User_Name = '" + username + "' AND Password = '" + password + "'";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            rs.next();
            if (rs.getString("User_Name").equals(username)) {
                if (rs.getString("Password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;
    }
}
