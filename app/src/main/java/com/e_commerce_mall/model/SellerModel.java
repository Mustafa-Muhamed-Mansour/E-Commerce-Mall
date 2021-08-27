package com.e_commerce_mall.model;

public class SellerModel
{
    private String sellerID;
    private String name;
    private String phone;
    private String email;
    private String shopAddress;

    public SellerModel(String sellerID, String name, String phone, String email, String shopAddress)
    {
        this.sellerID=sellerID;
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.shopAddress=shopAddress;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getShopAddress() {
        return shopAddress;
    }
}
