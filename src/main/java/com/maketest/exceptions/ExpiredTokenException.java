package com.maketest.exceptions;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
public class ExpiredTokenException extends RuntimeException {

    String sessionToken;

    public ExpiredTokenException(String token){
        this.sessionToken = token;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
