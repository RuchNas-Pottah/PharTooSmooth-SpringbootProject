package com.pharmacy.pharmacymanagementsystem.models;

import jakarta.validation.constraints.NotBlank;
public class User {
    @NotBlank
    private String userEmail;

    @NotBlank
    private String password;

    @NotBlank
    private String role;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [userEmail=" + userEmail + ", password=" + password + ", role=" + role + "]";
    }

    // @Override
    // public String toString() {
    //     return "{" +
    //         " userEmail='" + getUserEmail() + "'" +
    //         ", password='" + getPassword() + "'" +
    //         ", role='" + getRole() + "'" +
    //         "}";
    // }
    

}
