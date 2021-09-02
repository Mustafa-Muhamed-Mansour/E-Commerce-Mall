package com.e_commerce_mall.user.cart;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_commerce_mall.R;
import com.e_commerce_mall.adapter.cart.CartProductAdapter;
import com.e_commerce_mall.databinding.CartUserFragmentBinding;
import com.e_commerce_mall.model.CartModel;

import java.util.ArrayList;

public class CartUserFragment extends Fragment
{

    private CartUserFragmentBinding binding;
    private NavController navController;
    private CartUserViewModel cartUserViewModel;
    private CartProductAdapter cartProductAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = CartUserFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        cartUserViewModel = new ViewModelProvider(requireActivity()).get(CartUserViewModel.class);

        binding.btnOrderNow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle bundlePrice = new Bundle();
                bundlePrice.putString("total_price", String.valueOf(CartProductAdapter.totalPrice) + " £");
                navController.navigate(R.id.action_cartUserFragment_to_orderUserFragment, bundlePrice);
            }
        });

        cartUserViewModel.cartModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<CartModel>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<CartModel> cartModels)
            {
                cartProductAdapter = new CartProductAdapter(cartModels);
                binding.rVCart.setAdapter(cartProductAdapter);
                binding.rVCart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.rVCart.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                cartProductAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();

        cartUserViewModel.retriveProduct();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        cartUserViewModel.cartModelMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}