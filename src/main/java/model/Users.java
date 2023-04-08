package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.UsersQuery.getAllUsers;

/**
 * Class for instantiating User objects.
 *
 * @author Colin Roberson
 */
public class Users {

    /**
     * The ID of the user.
     */
    private Integer userId;

    /**
     * The name of the user.
     */
    private String userName;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * List of all user objects
     */
    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    /**
     * Constructor for new instances of user objects.
     *
     * @param userId The ID for the user.
     * @param userName The name of the user.
     * @param password The password of the user.
     */
    public Users (Integer userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Override the toString method to return the User's Name
     *
     * @return userName
     */
    @Override
    public String toString() {
        return userName;
    }

    /**
     * Get User ID
     *
     * @return User ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Get User Name
     *
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get User password
     *
     * @return User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get user object from user ID. Filters through all user objects and returns the first object that matches the provided ID.
     *
     * @param id User ID to search for.
     * @return user object corresponding with provided ID.
     */
    public static Users getUserFromId(Integer id) {
        allUsers = getAllUsers();

        Users users = allUsers.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        return users;
    }
}
