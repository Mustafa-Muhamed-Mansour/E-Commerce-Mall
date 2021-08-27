package com.e_commerce_mall.admin;

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
import com.e_commerce_mall.databinding.FragmentAdminCategoryBinding;
import com.google.firebase.auth.FirebaseAuth;

public class AdminCategoryFragment extends Fragment
{

    private FragmentAdminCategoryBinding binding;
    private NavController navController;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        binding = FragmentAdminCategoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController =Navigation.findNavController(view);

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnProductsMaintain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_adminCategoryFragment_to_productsMaintenanceFragment);
            }
        });

        binding.btnCheckNewOrders.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_adminCategoryFragment_to_checkOrdersFragment);
            }
        });

        binding.btnLogOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                firebaseAuth.signOut();
                navController.navigate(R.id.action_adminCategoryFragment_to_signInAdminFragment);
            }
        });
    }
}