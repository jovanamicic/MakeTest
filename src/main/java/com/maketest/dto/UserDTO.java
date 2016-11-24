<<<<<<< HEAD
package com.maketest.dto;

import com.maketest.model.Session;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
public class UserDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Session userSession;

    public UserDTO(User u){
        this(u.getId(),u.getEmail(),u.getFirstName(),u.getLastName(),u.getPassword(),u.getUserSession());
    }

    public UserDTO(){}

    public UserDTO(int id, String email, String firstName, String lastName, String password, Session userSession) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userSession = userSession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
=======
package com.maketest.dto;

import com.maketest.model.Session;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
public class UserDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private SessionDTO userSession;

    public UserDTO(User u){
        this.id = u.getId();
        this.email = u.getEmail();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.password = u.getPassword();
        Session userS = u.getUserSession();
        this.userSession = new SessionDTO(userS.getId(),userS.getSessionToken(), userS.getSessionExpire());
    }

    public UserDTO(){}

    public UserDTO(int id, String email, String firstName, String lastName, String password, SessionDTO userSession) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userSession = userSession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SessionDTO getUserSession() {
        return userSession;
    }

    public void setUserSession(SessionDTO userSession) {
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
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
