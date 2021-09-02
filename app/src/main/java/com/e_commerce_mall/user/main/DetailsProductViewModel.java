package com.e_commerce_mall.user.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.CartModel;
import com.e_commerce_mall.model.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DetailsProductViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference addCartRef = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();

    public void addCart(String randomKey, String cartImage, String cartName, String cartDescription, String cartPrice, String cartQuantity)
    {
        String cartID = firebaseAuth.getCurrentUser().getUid();
        CartModel cartModel = new CartModel(randomKey, cartID, cartImage, cartName, cartDescription, cartPrice, cartQuantity);
        addCartRef.child("Cart").child(randomKey).setValue(cartModel);

        booleanMutableLiveData.setValue(true);
    }
}