package com.e_commerce_mall.user.order;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.e_commerce_mall.databinding.OrderUserFragmentBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class OrderUserFragment extends Fragment
{

    private OrderUserFragmentBinding binding;
    private NavController navController;
    private OrderUserViewModel orderUserViewModel;
    private String orderUserImage;
    private Uri resultUri;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = OrderUserFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        orderUserViewModel = new ViewModelProvider(requireActivity()).get(OrderUserViewModel.class);

        binding.btnOrderUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = binding.editFullNameOrderUser.getText().toString();
                String phone = binding.editPhoneOrderUser.getText().toString();
                String address = binding.editAddressOrderUser.getText().toString();
                String paied = binding.txtPaiedOrderUser.toString();

                if (TextUtils.isEmpty(name))
                {
                    binding.editFullNameOrderUser.setText(getString(R.string.please_enter_your_full_name));
                    binding.editFullNameOrderUser.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phone))
                {
                    binding.editPhoneOrderUser.setText(getString(R.string.please_enter_your_phone));
                    binding.editPhoneOrderUser.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(address))
                {
                    binding.editAddressOrderUser.setText(getString(R.string.please_enter_your_full_address));
                    binding.editAddressOrderUser.requestFocus();
                    return;
                }

//                if (orderUserImage == null)
//                {
//                    Toast.makeText(getActivity(), "The Photo Please", Toast.LENGTH_SHORT).show();
//                }

                else
                {
                    orderUserViewModel.addOrder(paied, orderUserImage, name, phone, address);
                }
            }
        });

        binding.profilePictureOrderUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openGallery();
            }
        });

        orderUserViewModel.booleanMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>()
        {
            @Override
            public void onChanged(Boolean aBoolean)
            {
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
    public void onDestroyView()
    {
        super.onDestroyView();

        orderUserViewModel.booleanMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}