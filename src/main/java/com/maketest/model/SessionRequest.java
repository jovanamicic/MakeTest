package com.maketest.model;

/**
 * Created by Jovana Micic on 18-Nov-16.
 */
public class SessionRequest {
    private String email;
    private String password;

    public SessionRequest(){}

    public SessionRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
