package model;

/**
 * Class for instantiating the CurrUser object.
 * This class uses the Singleton design pattern and is used to store the current user's ID and name to be used for later logging purposes.
 *
 * @author Colin Roberson
 */
public class CurrUser {

    /**
     * The ID of the current user
     */
    private Integer userId;

    /**
     * The name of the current user
     */
    private String userName;

    /**
     * static method to create CurrUser instance
     */
    private static CurrUser currUser = new CurrUser();

    /**
     * Constructor for CurrUser object
     */
    private CurrUser() {}

    /**
     * Static method to get the CurrUser object
     *
     * @return CurrUser
     */
    public static CurrUser getCurrUser() {
        return currUser;
    }

    /**
     * Get current user ID
     *
     * @return User ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Get current user name
     *
     * @return User name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the current user ID
     *
     * @param userId the ID to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Set the current user name
     *
     * @param userName the name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
