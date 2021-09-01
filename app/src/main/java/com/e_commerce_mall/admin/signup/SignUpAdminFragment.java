package com.e_commerce_mall.admin.signup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.e_commerce_mall.databinding.SignUpAdminFragmentBinding;

public class SignUpAdminFragment extends Fragment
{

    private SignUpAdminFragmentBinding binding;
    private NavController navController;
    private SignUpAdminViewModel signUpAdminViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SignUpAdminFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController =Navigation.findNavController(view);
        signUpAdminViewModel = new ViewModelProvider(requireActivity()).get(SignUpAdminViewModel.class);

        binding.btnSignUpAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = binding.editEmailSignUpAdmin.getText().toString();
                String password = binding.editPasswordSignUpAdmin.getText().toString();
                String confirmPassword = binding.editConfirmPasswordSignUpAdmin.getText().toString();

                if (TextUtils.isEmpty(email))
                {
                    binding.editEmailSignUpAdmin.setError(getString(R.string.please_enter_your_email));
                    binding.editEmailSignUpAdmin.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    binding.editPasswordSignUpAdmin.setError(getString(R.string.please_enter_your_password));
                    binding.editPasswordSignUpAdmin.requestFocus();
                    return;
                }

                if (!password.equals(confirmPassword))
                {
                    binding.editConfirmPasswordSignUpAdmin.setError(getString(R.string.please_enter_your_confirm_password));
                    binding.editConfirmPasswordSignUpAdmin.requestFocus();
                    return;
                }

                else
                {
                    signUpAdminViewModel.signUp(email, password);
                }
            }
        });

        binding.txtSignInAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signUpAdminFragment_to_signInAdminFragment);
            }
        });

        signUpAdminViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Sucess Admin"))
                {
                    Toast.makeText(getContext(), "Sucessfully in signUp", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_signUpAdminFragment_to_adminCategoryFragment);
                }
                else
                {
                    Toast.makeText(getContext(), "Failure in signUp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}