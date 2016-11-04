package com.maketest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
@Entity
@Table(name="session")
public class Session implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="session_id", unique = true, nullable = false)
    private Integer id;

    @Column(name="session_token", unique = true, nullable = false)
    private String sessionToken;

    @Column(name = "session_expire", unique = false, nullable = false)
    private Date sessionExpire;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "userSession")
    private Set<User> users = new HashSet<User>();

    public Session(){

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionToken='" + sessionToken + '\'' +
                ", sessionExpire=" + sessionExpire +
                ", users=" + users +
                '}';
    }
}



