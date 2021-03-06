package com.e_commerce_mall.seller.main;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.e_commerce_mall.R;
import com.e_commerce_mall.adapter.seller.SellerProductAdapter;
import com.e_commerce_mall.databinding.SellerMainFragmentBinding;
import com.e_commerce_mall.model.ProductModel;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellerMainFragment extends Fragment
{

    private SellerMainFragmentBinding binding;
    private NavController navController;
    private SellerMainFragmentViewModel sellerMainFragmentViewModel;
    private SellerProductAdapter sellerProductAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SellerMainFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        sellerMainFragmentViewModel = new ViewModelProvider(requireActivity()).get(SellerMainFragmentViewModel.class);

        binding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        navController.navigate(R.id.action_sellerMainFragment_self);
                        break;
                    case R.id.nav_add_new_product:
                        navController.navigate(R.id.action_sellerMainFragment_to_addNewProductFragment);
                        break;
                    case R.id.nav_loout:

                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                        navController.navigate(R.id.action_sellerMainFragment_to_signInFragment);
                        break;
                    default:
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        sellerMainFragmentViewModel.productModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<ProductModel>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<ProductModel> productModels)
            {
                sellerProductAdapter = new SellerProductAdapter(productModels);
                binding.rVSeller.setAdapter(sellerProductAdapter);
                binding.rVSeller.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.rVSeller.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
                sellerProductAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();

        sellerMainFragmentViewModel.retriveProduct();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        sellerMainFragmentViewModel.productModelMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}