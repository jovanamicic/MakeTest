package com.maketest.model;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column (name = "activated", unique = false, nullable = false)
    private Boolean activated;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "userTests")
    private Set<Test> tests = new HashSet<Test>();

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
                ", activated=" + activated +
                ", tests=" + tests +
                '}';
    }
}
