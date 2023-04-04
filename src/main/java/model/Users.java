package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

import static DAO.UsersQuery.getAllUsers;

public class Users {
    private Integer userId;
    private String userName;
    private String password;
    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();


    public Users (Integer userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static Users getUserFromId(Integer id) {
        allUsers = getAllUsers();

        Users users = allUsers.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        return users;
    }
}
