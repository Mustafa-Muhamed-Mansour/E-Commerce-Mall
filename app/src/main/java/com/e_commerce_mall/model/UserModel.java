package com.e_commerce_mall.model;

public class UserModel
{
    private String userID;
    private String email;
    private String fullName;


    public UserModel(String userID, String email, String fullName)
    {
        this.userID = userID;
        this.email = email;
        this.fullName = fullName;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }
}
