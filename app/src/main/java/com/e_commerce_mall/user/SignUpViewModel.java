package com.e_commerce_mall.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void signUp(String email, String fullName, String password)
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
                            String userID = firebaseAuth.getCurrentUser().getUid();
                            stringMutableLiveData.setValue("Sucess User");
                            UserModel userModel = new UserModel(userID, email, fullName);
                            userRef.child("Users").child(userID).setValue(userModel);
                        }
                        else
                        {
                            stringMutableLiveData.setValue("Failure User");
                        }
                    }
                });
    }
}