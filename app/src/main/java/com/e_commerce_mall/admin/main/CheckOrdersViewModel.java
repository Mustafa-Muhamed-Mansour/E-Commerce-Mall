package com.e_commerce_mall.admin.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.OrderModel;
import com.e_commerce_mall.model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CheckOrdersViewModel extends ViewModel
{

    private ArrayList<OrderModel> orderModels = new ArrayList<>();
    private DatabaseReference orderRetriveRef = FirebaseDatabase.getInstance().getReference();
    private MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<OrderModel>> orderModelMutableLiveData = new MutableLiveData<>();


    public void retriveOrder()
    {
        orderRetriveRef
                .child("Order's")
                .addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        orderModels.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                            orderModels.add(orderModel);
                        }
                        orderModelMutableLiveData.setValue(orderModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        stringMutableLiveData.postValue(error.getMessage());
                    }
                });
    }
}