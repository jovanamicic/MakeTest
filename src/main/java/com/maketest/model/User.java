package com.maketest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */

@Entity
@Table(name="user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="user_id", unique = true, nullable = false)
    private Integer id;

    @Column (name = "email", unique = true, nullable = false)
    private String email;

    @Column (name = "password", unique = false, nullable = false)
    private String password;

    @Column (name = "first_name", unique = false, nullable = false)
    private String firstName;

    @Column (name = "last_name", unique = false, nullable = false)
    private String lastName;

    @Column (name = "profile_photo_relative_path", unique = false, nullable = true)
    private String profilePhotoRelativePath;

    @Column (name = "token", unique = false, nullable = false)
    private String token;

    @Column (name = "token_expire_date", unique = false, nullable = false)
    private Date tokenExpireDate;

    @Column (name = "activated", unique = false, nullable = false)
    private Boolean activated;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "userTests")
    private Set<Test> tests = new HashSet<Test>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<UserAuthority> userAuthorities = new HashSet<UserAuthority>();

    @ManyToOne
    @JoinColumn(name="user_session", referencedColumnName = "session_id")
    private Session userSession;

    public Session getUserSession() {
        return userSession;
    }

    public void setUserSession(Session userSession) {
        this.userSession = userSession;
    }

    public Set<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public User(){
        this.activated = false;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpireDate() {
        return tokenExpireDate;
    }

    public void setTokenExpireDate(Date tokenExpireDate) {
        this.tokenExpireDate = tokenExpireDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getProfilePhotoRelativePath() {
        return profilePhotoRelativePath;
    }

    public void setProfilePhotoRelativePath(String profilePhotoRelativePath) {
        this.profilePhotoRelativePath = profilePhotoRelativePath;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePhotoRelativePath='" + profilePhotoRelativePath + '\'' +
                ", token='" + token + '\'' +
                ", tokenExpireDate=" + tokenExpireDate +
                ", activated=" + activated +
                ", tests=" + tests +
                ", userAuthorities=" + userAuthorities +
                '}';
    }
}
