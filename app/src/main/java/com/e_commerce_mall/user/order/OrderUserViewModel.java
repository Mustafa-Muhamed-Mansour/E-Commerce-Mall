package com.e_commerce_mall.user.order;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.OrderModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class OrderUserViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference();
    private StorageReference orderImageRef = FirebaseStorage.getInstance().getReference().child("Order Images User").child("randomImage");
    public MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();


    public void addOrder(String orderPaied, String orderUserName, String orderUserPhone, String orderUserAddress)
    {

        String orderID = firebaseAuth.getCurrentUser().getUid();
        String randomKey = orderRef.push().getKey();

        OrderModel orderModel = new OrderModel(randomKey, orderID, orderPaied, orderUserName, orderUserPhone, orderUserAddress);
        orderRef.child("Order's").child(randomKey).setValue(orderModel);
        deleteRef.child("Cart").removeValue();
        booleanMutableLiveData.setValue(true);
    }
}