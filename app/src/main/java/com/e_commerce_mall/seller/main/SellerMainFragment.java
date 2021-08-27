package com.e_commerce_mall.seller.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e_commerce_mall.R;
import com.e_commerce_mall.databinding.SellerMainFragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class SellerMainFragment extends Fragment
{

    private SellerMainFragmentBinding binding;
    private NavController navController;
    private FirebaseAuth firebaseAuth;


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
    }
}