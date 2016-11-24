package com.maketest.dto;

import com.maketest.model.Session;

import java.util.Date;

/**
 * Created by Jovana Micic on 22-Nov-16.
 */
public class SessionDTO {

    private Integer id;
    private String sessionToken;
    private Date sessionExpire;

    public SessionDTO(Session s){
        this(s.getId(),s.getSessionToken(), s.getSessionExpire());
    }

    public SessionDTO(Integer id, String sessionToken, Date sessionExpire) {
        this.id = id;
        this.sessionToken = sessionToken;
        this.sessionExpire = sessionExpire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Date getSessionExpire() {
        return sessionExpire;
    }

    public void setSessionExpire(Date sessionExpire) {
        this.sessionExpire = sessionExpire;
    }
}
