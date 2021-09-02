package com.e_commerce_mall.user.main;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e_commerce_mall.R;
import com.e_commerce_mall.adapter.user.UserProductAdapter;
import com.e_commerce_mall.databinding.MainFragmentBinding;
import com.e_commerce_mall.databinding.NavHeaderMainBinding;
import com.e_commerce_mall.model.ProductModel;
import com.e_commerce_mall.model.UserModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainFragment extends Fragment
{

    private MainFragmentBinding binding;
    private NavController navController;
    private MainViewModel mainViewModel;
    private ActionBarDrawerToggle toggle;
    private UserProductAdapter userProductAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.toolbar.setTitle("Home");
        toggle = new ActionBarDrawerToggle(getActivity(), binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.drawer_home:
                        navController.navigate(R.id.action_mainFragment_self);
                        break;
                    case R.id.drawer_cart:
                        navController.navigate(R.id.action_mainFragment_to_cartUserFragment);
                        binding.toolbar.setTitle("Cart");
                        break;
                    case R.id.drawer_search:
                        navController.navigate(R.id.action_mainFragment_to_searchUserFragment);
                        binding.toolbar.setTitle("Search");
                        break;
                    case R.id.drawer_setting:
                        navController.navigate(R.id.action_mainFragment_to_settingUserFragment);
                        binding.toolbar.setTitle("Setting");
                        break;
                    case R.id.drawer_logout:
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                        navController.navigate(R.id.action_mainFragment_to_signUpFragment);
                        break;
                    default:
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        break;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        View viewHeader = binding.navView.getHeaderView(0);
        NavHeaderMainBinding navHeaderMainBinding = NavHeaderMainBinding.bind(viewHeader);


        mainViewModel.productModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<ProductModel>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<ProductModel> productModels)
            {
                userProductAdapter = new UserProductAdapter(productModels);
                binding.rV.setAdapter(userProductAdapter);
                binding.rV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.rV.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                binding.rV.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
                binding.rV.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
                userProductAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onStart()
    {
        super.onStart();

        mainViewModel.retriveProduct();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        mainViewModel.productModelMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}