package com.e_commerce_mall.seller.register;

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
import com.e_commerce_mall.databinding.SignUpSellerFragmentBinding;

public class SignUpSellerFragment extends Fragment
{

    private SignUpSellerFragmentBinding binding;
    private NavController navController;
    private SignUpSellerViewModel signUpSellerViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SignUpSellerFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        signUpSellerViewModel = new ViewModelProvider(this).get(SignUpSellerViewModel.class);

        binding.imBtnBackSeller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signUpSellerFragment_to_signInFragment);
            }
        });

        binding.btnSignInSeller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signUpSellerFragment_to_signInSellerFragment);
            }
        });

        binding.btnSignUpSeller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = binding.editNameSignUpSeller.getText().toString();
                String phone = binding.editPhoneSignUpSeller.getText().toString();
                String email = binding.editEmailSignUpSeller.getText().toString();
                String password = binding.editPasswordSignUpSeller.getText().toString();
                String shopAddress = binding.editShopAddressSignUpSeller.getText().toString();

                if (TextUtils.isEmpty(name))
                {
                    binding.editNameSignUpSeller.setError(getString(R.string.please_enter_your_name));
                    binding.editNameSignUpSeller.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phone))
                {
                    binding.editPhoneSignUpSeller.setError(getString(R.string.please_enter_your_phone));
                    binding.editPhoneSignUpSeller.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email))
                {
                    binding.editEmailSignUpSeller.setError(getString(R.string.please_enter_your_email));
                    binding.editEmailSignUpSeller.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    binding.editPasswordSignUpSeller.setError(getString(R.string.please_enter_your_password));
                    binding.editPasswordSignUpSeller.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(shopAddress))
                {
                    binding.editShopAddressSignUpSeller.setError(getString(R.string.please_enter_your_shop_address));
                    binding.editShopAddressSignUpSeller.requestFocus();
                    return;
                }

                else
                {
                    signUpSellerViewModel.signUp(name, phone, email, password, shopAddress);
                }
            }
        });

        signUpSellerViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Sucess Seller"))
                {
                    Toast.makeText(getContext(), "Sucessfully in signUp", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_signUpSellerFragment_to_signInSellerFragment);
                }
                else
                {
                    Toast.makeText(getContext(), "Failure in signUp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        signUpSellerViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}