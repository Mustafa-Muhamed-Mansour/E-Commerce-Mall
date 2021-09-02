package com.e_commerce_mall.model;

public class CartModel
{

    private String randomKey;
    private String cartID;
    private String cartProductImage;
    private String cartProductName;
    private String cartProductDescription;
    private String cartProductPrice;
    private String cartProductQuantity;


    public CartModel()
    {
    }


    public CartModel(String randomKey, String cartID, String cartProductImage, String cartProductName, String cartProductDescription, String cartProductPrice, String cartProductQuantity)
    {
        this.randomKey = randomKey;
        this.cartID = cartID;
        this.cartProductImage = cartProductImage;
        this.cartProductName = cartProductName;
        this.cartProductDescription = cartProductDescription;
        this.cartProductPrice = cartProductPrice;
        this.cartProductQuantity = cartProductQuantity;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public String getCartID() {
        return cartID;
    }

    public String getCartProductImage() {
        return cartProductImage;
    }

    public String getCartProductName() {
        return cartProductName;
    }

    public String getCartProductDescription() {
        return cartProductDescription;
    }

    public String getCartProductPrice() {
        return cartProductPrice;
    }

    public String getCartProductQuantity() {
        return cartProductQuantity;
    }
}
