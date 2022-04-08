package controller;

public class UserSession {
    private User user = null;
    private static UserSession instance = null;

    public UserSession() {

    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    public User getUser() {
        return user;
    }
    public void newUserSession(final User user) {
        this.user = user;
    }

    public void destroyUserSession() {
        this.user = null;
    }
}
