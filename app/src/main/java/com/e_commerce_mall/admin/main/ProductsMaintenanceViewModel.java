package com.e_commerce_mall.admin.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductsMaintenanceViewModel extends ViewModel
{

    private ArrayList<ProductModel> productModels = new ArrayList<>();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference adminRef = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<ProductModel>> productModelMutableLiveData  = new MutableLiveData<ArrayList<ProductModel>>();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();


    public void retriveProduct()
    {
        adminRef
                .child("Product Admin")
                .addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        productModels.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            ProductModel productModel = dataSnapshot.getValue(ProductModel.class);
                            productModels.add(productModel);
                        }
                        productModelMutableLiveData.postValue(productModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        stringMutableLiveData.setValue(error.getMessage());
                    }
                });
    }
}