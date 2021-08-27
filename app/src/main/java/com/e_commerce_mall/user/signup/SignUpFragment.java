package com.e_commerce_mall.user.signup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e_commerce_mall.R;
import com.e_commerce_mall.databinding.SignUpFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment
{

    private SignUpFragmentBinding binding;
    private NavController navController;
    private SignUpViewModel signUpViewModel;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SignUpFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        progressDialog=new ProgressDialog(getActivity());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnSignUpUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = binding.editEmailSignUpUser.getText().toString();
                String fullName = binding.editFullNameSignUpUser.getText().toString();
                String password = binding.editPasswordSignUpUser.getText().toString();
                String confirmPassword = binding.editConfirmPasswordSignUpUser.getText().toString();

                if (TextUtils.isEmpty(email))
                {
                    binding.editEmailSignUpUser.setError(getString(R.string.please_enter_your_email));
                    binding.editEmailSignUpUser.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(fullName))
                {
                    binding.editFullNameSignUpUser.setError(getString(R.string.please_enter_your_full_name));
                    binding.editFullNameSignUpUser.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    binding.editPasswordSignUpUser.setError(getString(R.string.please_enter_your_password));
                    binding.editPasswordSignUpUser.requestFocus();
                    return;
                }

                if (!password.equals(confirmPassword))
                {
                    binding.editConfirmPasswordSignUpUser.setError(getString(R.string.please_enter_your_confirm_password));
                    binding.editConfirmPasswordSignUpUser.requestFocus();
                    return;
                }

                else
                {
                    progressDialog.setTitle("New Account");
                    progressDialog.setMessage("Please Wait while we are creating a new account");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    signUpViewModel.signUp(email, fullName, password);
                }
            }
        });

        binding.txtSignInUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signUpFragment_to_signInFragment);
            }
        });

        signUpViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Sucess User"))
                {
                    progressDialog.cancel();
                    Toast.makeText(getContext(), "Sucessfully in signUp", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_signUpFragment_to_signInFragment);
                }
                else
                {
                    progressDialog.cancel();
                    Toast.makeText(getContext(), "Failure in signUp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        signUpViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}