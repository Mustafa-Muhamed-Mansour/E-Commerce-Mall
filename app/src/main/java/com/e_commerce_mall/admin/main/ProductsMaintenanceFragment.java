package com.e_commerce_mall.admin.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_commerce_mall.adapter.admin.AdminProductAdapter;
import com.e_commerce_mall.databinding.ProductsMaintenanceFragmentBinding;
import com.e_commerce_mall.model.ProductModel;

import java.util.ArrayList;

public class ProductsMaintenanceFragment extends Fragment
{

    private ProductsMaintenanceFragmentBinding binding;
    private NavController navController;
    private ProductsMaintenanceViewModel productsMaintenanceViewModel;
    private AdminProductAdapter adminProductAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = ProductsMaintenanceFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        productsMaintenanceViewModel = new ViewModelProvider(requireActivity()).get(ProductsMaintenanceViewModel.class);

        productsMaintenanceViewModel.productModelMutableLiveData.observe(requireActivity(), new Observer<ArrayList<ProductModel>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<ProductModel> productModels)
            {
                adminProductAdapter = new AdminProductAdapter(productModels);
                binding.rVProductsMaintenance.setAdapter(adminProductAdapter);
                binding.rVProductsMaintenance.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                binding.rVProductsMaintenance.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                adminProductAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onStart()
    {
        super.onStart();

        productsMaintenanceViewModel.retriveProduct();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        productsMaintenanceViewModel.productModelMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}