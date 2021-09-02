package com.e_commerce_mall.user.search;

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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_commerce_mall.R;
import com.e_commerce_mall.adapter.search.SearchProductAdapter;
import com.e_commerce_mall.adapter.search.SearchUserProductAdapter;
import com.e_commerce_mall.adapter.user.UserProductAdapter;
import com.e_commerce_mall.databinding.SearchUserFragmentBinding;
import com.e_commerce_mall.model.ProductModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class SearchUserFragment extends Fragment
{

    private SearchUserFragmentBinding binding;
    private NavController navController;
    private SearchUserViewModel searchUserViewModel;
    private SearchUserProductAdapter searchUserProductAdapter;
    private SearchProductAdapter searchProductAdapter;
    private FirebaseRecyclerOptions<ProductModel> options;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SearchUserFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        searchUserViewModel = new ViewModelProvider(requireActivity()).get(SearchUserViewModel.class);

        binding.editTxtSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (editable.toString() != null)
                {
                    loadData(editable.toString());
                }
            }
        });

        searchUserViewModel.productModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<ProductModel>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<ProductModel> productModels)
            {
                searchUserProductAdapter = new SearchUserProductAdapter(productModels);
                binding.rVSearch.setAdapter(searchUserProductAdapter);
                binding.rVSearch.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.rVSearch.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                searchUserProductAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadData(String date)
    {
        Query query = FirebaseDatabase.getInstance().getReference().child("Product Admin").orderByChild("productName").startAt(date).endAt(date + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(query, ProductModel.class).build();
        searchProductAdapter = new SearchProductAdapter(options);
        binding.rVSearch.setAdapter(searchProductAdapter);
        searchProductAdapter.startListening();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        searchUserViewModel.searchProduct();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        searchUserViewModel.productModelMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}