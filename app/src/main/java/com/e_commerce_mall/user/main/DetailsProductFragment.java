package com.e_commerce_mall.user.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e_commerce_mall.R;
import com.e_commerce_mall.databinding.DetailsProductFragmentBinding;
import com.squareup.picasso.Picasso;

public class DetailsProductFragment extends Fragment
{

    private DetailsProductFragmentBinding binding;
    private NavController navController;
    private DetailsProductViewModel detailsProductViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = DetailsProductFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        detailsProductViewModel = new ViewModelProvider(requireActivity()).get(DetailsProductViewModel.class);


        String random_key = getArguments().getString("randomKey");
        String product_image = getArguments().getString("productImage");
        String product_name = getArguments().getString("productName");
        String product_description = getArguments().getString("productDescription");
        String product_price = getArguments().getString("productPrice");

        binding.txtDetailsProductName.setText(product_name);
        binding.txtDetailsProductDescription.setText(product_description);
        binding.txtDetailsProductPrice.setText(product_price);
        Picasso
                .get()
                .load(product_image)
                .into(binding.detailsProductImg);

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                detailsProductViewModel.addCart(random_key, product_image, product_name, product_description, product_price, binding.elegantNumberBtn.getNumber());
            }
        });

        detailsProductViewModel.booleanMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>()
        {
            @Override
            public void onChanged(Boolean aBoolean)
            {
                Toast.makeText(getActivity(), "Lololololole", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        detailsProductViewModel.booleanMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}