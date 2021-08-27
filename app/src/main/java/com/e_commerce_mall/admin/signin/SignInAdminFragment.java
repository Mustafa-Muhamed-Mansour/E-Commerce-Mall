package com.e_commerce_mall.admin.signin;

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
import com.e_commerce_mall.databinding.SignInAdminFragmentBinding;

public class SignInAdminFragment extends Fragment
{

    private SignInAdminFragmentBinding binding;
    private NavController navController;
    private SignInAdminViewModel signInAdminViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SignInAdminFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        signInAdminViewModel = new ViewModelProvider(this).get(SignInAdminViewModel.class);

        binding.btnSignInAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = binding.editEmailSignInAdmin.getText().toString();
                String password = binding.editPasswordSignInAdmin.getText().toString();

                if (TextUtils.isEmpty(email))
                {
                    binding.editEmailSignInAdmin.setError(getString(R.string.please_enter_your_email));
                    binding.editEmailSignInAdmin.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    binding.editPasswordSignInAdmin.setError(getString(R.string.please_enter_your_password));
                    binding.editPasswordSignInAdmin.requestFocus();
                    return;
                }

                else
                {
                    signInAdminViewModel.signIn(email, password);
                }
            }
        });

        binding.imBtnBackAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signInAdminFragment_to_signInFragment);
            }
        });

        binding.txtSignUpAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_signInAdminFragment_to_signUpAdminFragment);
            }
        });

        signInAdminViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Sucess Admin"))
                {
                    Toast.makeText(getContext(), "Sucessfully in signIn", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_signInAdminFragment_to_adminCategoryFragment);
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

        signInAdminViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}