package com.e_commerce_mall.admin.main;

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

import com.e_commerce_mall.databinding.CheckOrdersFragmentBinding;

public class CheckOrdersFragment extends Fragment
{

    private CheckOrdersFragmentBinding binding;
    private NavController navController;
    private CheckOrdersViewModel checkOrdersViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = CheckOrdersFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController =Navigation.findNavController(view);
        checkOrdersViewModel = new ViewModelProvider(getActivity()).get(CheckOrdersViewModel.class);


    }

    @Override
    public void onStart()
    {
        super.onStart();


    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();


    }
}