package com.e_commerce_mall.seller.signup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.SellerModel;
import com.e_commerce_mall.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpSellerViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference sellerRef =FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void signUp(String name, String phone, String email, String password, String shopAddress)
    {
        firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            String sellerID = firebaseAuth.getCurrentUser().getUid();
                            stringMutableLiveData.setValue("Sucess Seller");
                            SellerModel userModel = new SellerModel(sellerID, name, phone, email, shopAddress);
                            sellerRef.child("Sellers' accounts").child(sellerID).setValue(userModel);
                        }
                        else
                        {
                            stringMutableLiveData.setValue("Failure Seller");
                        }
                    }
                });
    }

}