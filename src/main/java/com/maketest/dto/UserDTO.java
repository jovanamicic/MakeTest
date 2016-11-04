package com.maketest.dto;

import com.maketest.model.Session;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Session userSession;

    public UserDTO(){

    }

    public UserDTO(String email, String firstName, String lastName, String password, Session userSession) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userSession = userSession;
    }

    public Session getUserSession() {
        return userSession;
    }

    public void setUserSession(Session userSession) {
        this.userSession = userSession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", userSession=" + userSession +
                '}';
    }
}
