package com.e_commerce_mall.admin.signup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e_commerce_mall.model.AdminModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpAdminViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference adminRef =FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void signUp(String email, String password)
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
                            String adminID = firebaseAuth.getCurrentUser().getUid();
                            stringMutableLiveData.setValue("Sucess Admin");
                            AdminModel adminModel = new AdminModel(adminID, email);
                            adminRef.child("Admins' accounts").child(adminID).setValue(adminModel);
                        }
                        else
                        {
                            stringMutableLiveData.setValue("Failure Admin");
                        }
                    }
                });
    }
}