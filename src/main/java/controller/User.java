package controller;

public class User {
    final String  username;
    final String  password;
    final String Q1ans;
    final String Q2ans;
    final String Q3ans;
    final String Q4ans;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getQ1ans() {
        return Q1ans;
    }

    public String getQ2ans() {
        return Q2ans;
    }

    public String getQ3ans() {
        return Q3ans;
    }

    public String getQ4ans() {
        return Q4ans;
    }

    public User(String username, String password, String q1ans, String q2ans, String q3ans, String q4ans) {
        this.username = username;
        this.password = password;
        Q1ans = q1ans;
        Q2ans = q2ans;
        Q3ans = q3ans;
        Q4ans = q4ans;
    }
}
