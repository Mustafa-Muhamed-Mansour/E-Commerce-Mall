package com.e_commerce_mall.seller.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewProductViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference productRef = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void addProduct(String productName, String productDescription, String productPrice)
    {
        String productID = firebaseAuth.getCurrentUser().getUid();
        ProductModel productModel = new ProductModel(productID, productName, productDescription, productPrice);
        productRef.child("Products").child(productID).setValue(productModel);
        stringMutableLiveData.setValue("Done Add Product");
    }
}