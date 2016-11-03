package com.maketest.dto;

/**
 * Created by Jovana Micic on 03-Nov-16.
 */
public class UserProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String profilePhotoRelativePath;

    public UserProfileDTO(){}

    public UserProfileDTO(String firstName, String lastName, String email, String profilePhotoRelativePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePhotoRelativePath = profilePhotoRelativePath;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePhotoRelativePath() {
        return profilePhotoRelativePath;
    }

    public void setProfilePhotoRelativePath(String profilePhotoRelativePath) {
        this.profilePhotoRelativePath = profilePhotoRelativePath;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", profilePhotoRelativePath='" + profilePhotoRelativePath + '\'' +
                '}';
    }
}
