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
    private FirebaseAuth firebaseAuth;
    private DatabaseReference productRef;
    private StorageReference productImageRef;
    static final int GALLERY_PICK = 1;
    private Uri photoProductPathUri;
    private String productImage;


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

        firebaseAuth = FirebaseAuth.getInstance();
        productRef = FirebaseDatabase.getInstance().getReference();
        productImageRef = FirebaseStorage.getInstance().getReference();

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
                checkRequestPermission();
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

    private void checkRequestPermission()
    {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, GALLERY_PICK);
        }
        else
        {
            openGallery();
        }
    }

    private void openGallery()
    {
        CropImage
                .activity()
                .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                .start(getActivity(), this);

        Picasso.get()
                .load(photoProductPathUri)
                .into(binding.productImg);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && data != null && requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
//        {
//            CropImage.ActivityResult activityResult = CropImage.getActivityResult(data);
//            photoProductPathUri = activityResult.getUri();
//            final StorageReference filePath = productImageRef.child("Images Products").child(photoProductPathUri.getLastPathSegment() /*+ ".jpg" */);
//            final UploadTask uploadTask = filePath.putFile(photoProductPathUri);
//            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
//            {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
//                {
//                    if(!task.isSuccessful())
//                    {
//                        throw task.getException();
//                    }
//                    return filePath.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>()
//            {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task)
//                {
//                    if (task.isSuccessful())
//                    {
//
//                        Uri downloadUri = task.getResult();
//
//                        productImage = downloadUri.toString();
//
//                        productRef
//                                .child("Products")
//                                .child(firebaseAuth.getCurrentUser().getUid())
//                                .child("productImage")
//                                .setValue(productImage)
//                                .addOnCompleteListener(new OnCompleteListener<Void>()
//                                {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task)
//                                    {
//                                        if (task.isSuccessful())
//                                        {
//                                            Toast.makeText(getContext(), "Image Save In Database, Done", Toast.LENGTH_SHORT).show();
//                                        }
//                                        else
//                                        {
//                                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                    }
//                }
//            });
//
//
//        }
//    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        addNewProductViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}