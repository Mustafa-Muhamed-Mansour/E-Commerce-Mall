package com.e_commerce_mall.seller.main;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.ProductModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNewProductViewModel extends ViewModel {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference productRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference productAdminRef = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Image").child("randomName");
    public MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();


    public void addProduct(String url, String productName, String productDescription, String productPrice) {
        String productID = firebaseAuth.getCurrentUser().getUid();
        String randomKey = productRef.push().getKey();
        storageReference.putFile(Uri.parse(url)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri)
                    {

                        ProductModel productModel = new ProductModel(randomKey, productID, uri.toString(), productName, productDescription, productPrice);
                        productRef.child("Products").child(productID).child(randomKey).setValue(productModel);
                        productRef.child("Product Admin").child(randomKey).setValue(productModel);
                        booleanMutableLiveData.setValue(true);
                        
                    }
                });
            }
        });
    }

}