package com.e_commerce_mall.model;

public class AdminModel
{
    private String adminID;
    private String email;

    public AdminModel(String adminID, String email)
    {
        this.adminID=adminID;
        this.email=email;
    }

    public String getAdminID() {
        return adminID;
    }

    public String getEmail() {
        return email;
    }
}
