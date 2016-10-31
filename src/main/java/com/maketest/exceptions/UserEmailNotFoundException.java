package com.maketest.exceptions;

/**
 * Created by Jovana Micic on 31-Oct-16.
 */
public class UserEmailNotFoundException  extends RuntimeException {

    private String email;

    public UserEmailNotFoundException(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
