package com.e_commerce_mall.admin.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInAdminViewModel extends ViewModel
{
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void signIn(String email, String password)
    {
        firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            stringMutableLiveData.setValue("Sucess Admin");
                        }
                        else
                        {
                            stringMutableLiveData.setValue("Failure Admin");
                        }
                    }
                });
    }
}