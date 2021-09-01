package com.e_commerce_mall.seller.signin;

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
import com.e_commerce_mall.databinding.SignInSellerFragmentBinding;

public class SignInSellerFragment extends Fragment
{

    private SignInSellerFragmentBinding binding;
    private NavController navController;
    private SignInSellerViewModel signInSellerViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = SignInSellerFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        signInSellerViewModel = new ViewModelProvider(requireActivity()).get(SignInSellerViewModel.class);

        binding.textSignUpSeller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signInSellerFragment_to_signUpSellerFragment);
            }
        });

        binding.txtSignInSeller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = binding.editEmailSignInSeller.getText().toString();
                String password = binding.editPasswordSignInSeller.getText().toString();

                if ( TextUtils.isEmpty(email))
                {
                    binding.editEmailSignInSeller.setError(getString(R.string.please_enter_your_email));
                    binding.editEmailSignInSeller.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    binding.editPasswordSignInSeller.setError(getString(R.string.please_enter_your_password));
                    binding.editPasswordSignInSeller.requestFocus();
                    return;
                }

                else
                {
                    signInSellerViewModel.signIn(email, password);
                }
            }
        });

        signInSellerViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Sucess Seller"))
                {
                    Toast.makeText(getContext(), "Sucessfully in signIn", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_signInSellerFragment_to_sellerMainFragment);
                }
                else
                {
                    Toast.makeText(getContext(), "Failure in signIn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        signInSellerViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}