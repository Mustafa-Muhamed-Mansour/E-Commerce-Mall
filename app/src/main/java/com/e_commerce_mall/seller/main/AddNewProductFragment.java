package com.e_commerce_mall.seller.main;

import static android.app.Activity.RESULT_OK;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class AddNewProductFragment extends Fragment
{

    private AddNewProductFragmentBinding binding;
    private NavController navController;
    private AddNewProductViewModel addNewProductViewModel;
    private String productImage;
    private Uri resultUri;


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
                    addNewProductViewModel.addProduct(productImage, productName, productDescription, productPrice);
                }

            }
        });

        binding.productImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openGallery();
            }
        });

        addNewProductViewModel.booleanMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>()
        {
            @Override
            public void onChanged(Boolean aBoolean)
            {
                if (aBoolean)
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


    private void openGallery()
    {
        CropImage
                .activity()
                .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                .start(getActivity(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
            {
                resultUri = result.getUri();
                productImage = resultUri.toString();
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }

        Picasso
                .get()
                .load(resultUri)
                .into(binding.productImg);

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        addNewProductViewModel.booleanMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}