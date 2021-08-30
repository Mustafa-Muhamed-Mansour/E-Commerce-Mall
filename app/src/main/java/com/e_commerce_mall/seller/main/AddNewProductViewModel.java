package com.e_commerce_mall.seller.main;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.ProductModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNewProductViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference productRef = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void addProduct(String productImage, String productName, String productDescription, String productPrice)
    {
        String productID = firebaseAuth.getCurrentUser().getUid();
        String randomKey = productRef.push().getKey();
        ProductModel productModel = new ProductModel(randomKey, productID, productImage, productName, productDescription, productPrice);
        productRef.child("Products").child(productID).child(randomKey).setValue(productModel);
        productRef.child("Product Admin").child(randomKey).setValue(productModel);
        stringMutableLiveData.setValue("Done Add Product");
    }


}