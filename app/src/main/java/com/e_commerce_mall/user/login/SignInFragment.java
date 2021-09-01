package com.e_commerce_mall.user.login;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e_commerce_mall.R;
import com.e_commerce_mall.databinding.SignInFragmentBinding;

public class SignInFragment extends Fragment
{

    private SignInFragmentBinding binding;
    private NavController navController;
    private SignInViewModel signInViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SignInFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        signInViewModel = new ViewModelProvider(requireActivity()).get(SignInViewModel.class);

        binding.btnSignInUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = binding.editEmailSignInUser.getText().toString();
                String password = binding.editPasswordSignInUser.getText().toString();

                if (TextUtils.isEmpty(email))
                {
                    binding.editEmailSignInUser.setError(getString(R.string.please_enter_your_email));
                    binding.editEmailSignInUser.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    binding.editPasswordSignInUser.setError(getString(R.string.please_enter_your_password));
                    binding.editPasswordSignInUser.requestFocus();
                    return;
                }

                else
                {
                    signInViewModel.signIn(email, password);
                }
            }
        });

        binding.txtAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signInFragment_to_signInAdminFragment);
            }
        });

        binding.txtSignUpSeller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signInFragment_to_signUpSellerFragment);
            }
        });

        binding.txtSignUpUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signInFragment_to_signUpFragment);
            }
        });

        signInViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Sucess User"))
                {
                    Toast.makeText(getContext(), "Sucessfully in signIn", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_signInFragment_to_mainFragment);
                }
                else
                {
                    Toast.makeText(getContext(), "Failure in signIn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        signInViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}