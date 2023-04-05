package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CurrUser;
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
            String sql = "SELECT * FROM USERS WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
// This will only be false if the above query did not return anything. This will only happen if the username/password combo is no good.
            if (rs.next() != false) {
                String userName = rs.getString("User_Name");
                Integer userId = rs.getInt("User_ID");

                CurrUser currUser = CurrUser.getCurrUser();
                currUser.setUserName(userName);
                currUser.setUserId(userId);
                return true;
            } else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
