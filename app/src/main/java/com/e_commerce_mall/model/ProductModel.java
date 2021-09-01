package com.e_commerce_mall.model;

public class ProductModel
{
    private String randomKey;
    private String productID;
    private String productImage;
    private String productName;
    private String productDescription;
    private String productPrice;

    public ProductModel()
    {
    }

    public ProductModel(String randomKey, String productID, String productImage, String productName, String productDescription, String productPrice)
    {
        this.randomKey = randomKey;
        this.productID = productID;
        this.productImage = productImage;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
