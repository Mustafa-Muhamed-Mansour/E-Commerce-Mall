package com.e_commerce_mall.user.cart;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.CartModel;
import com.e_commerce_mall.model.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartUserViewModel extends ViewModel
{

    private ArrayList<CartModel> cartModels = new ArrayList<>();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference retriveRef = FirebaseDatabase.getInstance().getReference();
    private MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<CartModel>> cartModelMutableLiveData = new MutableLiveData<>();


    public void retriveProduct()
    {
        retriveRef
                .child("Cart")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        cartModels.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            CartModel cartModel = dataSnapshot.getValue(CartModel.class);
                            cartModels.add(cartModel);
                        }
                        cartModelMutableLiveData.setValue(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        stringMutableLiveData.postValue(error.getMessage());
                    }
                });
    }
}