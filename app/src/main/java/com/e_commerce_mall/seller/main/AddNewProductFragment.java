package com.e_commerce_mall.seller.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e_commerce_mall.R;
import com.e_commerce_mall.databinding.AddNewProductFragmentBinding;

public class AddNewProductFragment extends Fragment
{

    private AddNewProductFragmentBinding binding;
    private NavController navController;
    private AddNewProductViewModel addNewProductViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = AddNewProductFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        addNewProductViewModel = new ViewModelProvider(getActivity()).get(AddNewProductViewModel.class);

        binding.btnAddNewProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String productName = binding.editProductName.getText().toString();
                String productDescription = binding.editProductDescription.getText().toString();
                String productPrice = binding.editProductPrice.getText().toString();

                if (TextUtils.isEmpty(productName))
                {
                    binding.editProductName.setError(getString(R.string.please_enter_product_name));
                    binding.editProductName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(productDescription))
                {
                    binding.editProductDescription.setError(getString(R.string.please_enter_product_description));
                    binding.editProductDescription.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(productPrice))
                {
                    binding.editProductPrice.setError(getString(R.string.please_enter_product_price));
                    binding.editProductPrice.requestFocus();
                    return;
                }

                else
                {
                    addNewProductViewModel.addProduct(productName, productDescription, productPrice);
                }
            }
        });

        addNewProductViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Done Add Product"))
                {
                    Toast.makeText(getContext(), "Sucessfully in Added Product", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_addNewProductFragment_to_sellerMainFragment);
                }
                else
                {
                    Toast.makeText(getContext(), "Failure in Added Product", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        addNewProductViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}