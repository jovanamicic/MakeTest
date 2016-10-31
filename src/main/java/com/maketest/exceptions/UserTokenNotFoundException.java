package com.maketest.exceptions;

/**
 * Created by Jovana Micic on 31-Oct-16.
 */
public class UserTokenNotFoundException extends RuntimeException {

    private String id;

    public UserTokenNotFoundException(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
