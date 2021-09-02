package com.e_commerce_mall.model;

public class OrderModel
{

    private String randomKey;
    private String orderID;
    private String orderPaied;
    private String orderUserProfileImage;
    private String orderUserName;
    private String orderUserPhone;
    private String orderUserAddress;

    public OrderModel(String randomKey, String orderID, String orderPaied, String orderUserProfileImage, String orderUserName, String orderUserPhone, String orderUserAddress)
    {
        this.randomKey = randomKey;
        this.orderID = orderID;
        this.orderPaied = orderPaied;
        this.orderUserProfileImage = orderUserProfileImage;
        this.orderUserName = orderUserName;
        this.orderUserPhone = orderUserPhone;
        this.orderUserAddress = orderUserAddress;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderPaied() {
        return orderPaied;
    }

    public String getOrderUserProfileImage() {
        return orderUserProfileImage;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public String getOrderUserPhone() {
        return orderUserPhone;
    }

    public String getOrderUserAddress() {
        return orderUserAddress;
    }
}
