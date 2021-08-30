package com.e_commerce_mall.seller.main;


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
    private ArrayList<ProductModel> productModels;
    private SellerProductAdapter sellerProductAdapter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference retriveRef;


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

        firebaseAuth = FirebaseAuth.getInstance();
        retriveRef = FirebaseDatabase.getInstance().getReference();

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
                        firebaseAuth.signOut();
                        navController.navigate(R.id.action_sellerMainFragment_to_signInSellerFragment);
                        break;
                    default:
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        binding.rVSeller.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.rVSeller.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        productModels = new ArrayList<>();
        sellerProductAdapter = new SellerProductAdapter(productModels);
        binding.rVSeller.setAdapter(sellerProductAdapter);
        retriveRef.child("Products").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                productModels.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ProductModel productModel = dataSnapshot.getValue(ProductModel.class);
                    productModels.add(productModel);
                }
                sellerProductAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}