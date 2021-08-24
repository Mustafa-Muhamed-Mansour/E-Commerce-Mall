package com.e_commerce_mall.user;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_commerce_mall.R;
import com.e_commerce_mall.databinding.SignUpFragmentBinding;

public class SignUpFragment extends Fragment
{

    private SignUpFragmentBinding binding;
    private NavController navController;
    private SignUpViewModel signUpViewModel;


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


    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();


    }
}