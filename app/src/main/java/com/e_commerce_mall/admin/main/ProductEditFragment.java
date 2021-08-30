package com.e_commerce_mall.admin.main;

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
import com.e_commerce_mall.databinding.ProductEditFragmentBinding;
import com.e_commerce_mall.model.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ProductEditFragment extends Fragment
{

    private ProductEditFragmentBinding binding;
    private NavController navController;
    private DatabaseReference editRef;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = ProductEditFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);

        editRef = FirebaseDatabase.getInstance().getReference();

        String random_key = getArguments().getString("randomKey");
        String product_id = getArguments().getString("productID");
        String product_image = getArguments().getString("productImage");
        String product_name = getArguments().getString("productName");
        String product_description = getArguments().getString("productDescription");
        String product_price = getArguments().getString("productPrice");


        binding.editTxtProductName.setText(product_name);
        binding.editTxtProductDescription.setText(product_description);
        binding.editTxtProductPrice.setText(product_price);
        Picasso
                .get()
                .load(product_image)
                .into(binding.editProductImg);

        binding.btnEditProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String pName = binding.editTxtProductName.getText().toString();
                String pDescription = binding.editTxtProductDescription.getText().toString();
                String pPrice = binding.editTxtProductPrice.getText().toString();

                if (TextUtils.isEmpty(pName))
                {
                    binding.editTxtProductName.setError(getString(R.string.please_enter_product_name));
                    binding.editTxtProductName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pDescription))
                {
                    binding.editTxtProductDescription.setError(getString(R.string.please_enter_product_description));
                    binding.editTxtProductDescription.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pPrice))
                {
                    binding.editTxtProductPrice.setError(getString(R.string.please_enter_product_price));
                    binding.editTxtProductPrice.requestFocus();
                    return;
                }

                else
                {
                    ProductModel productModel = new ProductModel(random_key, product_id, product_image, pName, pDescription, pPrice);

                    editRef
                            .child("Product Admin")
                            .child(random_key)
                            .setValue(productModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    editRef
                            .child("Products")
                            .child(product_id)
                            .child(random_key)
                            .setValue(productModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(getContext(), "Done Also", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}