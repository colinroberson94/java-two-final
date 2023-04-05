package model;

/*
Singleton class. This should instantiate and store the current user's ID and name to be used for later logging purposes.
 */
public class CurrUser {
    private Integer userId;
    private String userName;
    private static CurrUser currUser = new CurrUser();
    private CurrUser() {}
    public static CurrUser getCurrUser() {
        return currUser;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
