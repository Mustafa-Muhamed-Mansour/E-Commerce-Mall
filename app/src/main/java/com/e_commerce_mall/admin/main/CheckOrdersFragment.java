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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_commerce_mall.R;
import com.e_commerce_mall.adapter.order.OrderAdapter;
import com.e_commerce_mall.adapter.user.UserProductAdapter;
import com.e_commerce_mall.databinding.CheckOrdersFragmentBinding;
import com.e_commerce_mall.model.OrderModel;
import com.e_commerce_mall.model.ProductModel;

import java.util.ArrayList;

public class CheckOrdersFragment extends Fragment
{

    private CheckOrdersFragmentBinding binding;
    private NavController navController;
    private CheckOrdersViewModel checkOrdersViewModel;
    private OrderAdapter orderAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = CheckOrdersFragmentBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        checkOrdersViewModel = new ViewModelProvider(requireActivity()).get(CheckOrdersViewModel.class);



        checkOrdersViewModel.orderModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<OrderModel>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<OrderModel> orderModels)
            {
                orderAdapter = new OrderAdapter(orderModels);
                binding.rVCheckOrder.setAdapter(orderAdapter);
                binding.rVCheckOrder.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.rVCheckOrder.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                orderAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();

        checkOrdersViewModel.retriveOrder();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        checkOrdersViewModel.orderModelMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}