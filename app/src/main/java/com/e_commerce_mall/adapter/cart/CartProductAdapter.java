package com.e_commerce_mall.adapter.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.CartModel;

import java.util.ArrayList;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>
{

    ArrayList<CartModel> cartModels;

    public CartProductAdapter(ArrayList<CartModel> cartModels)
    {
        this.cartModels = cartModels;
    }

    @NonNull
    @Override
    public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_cart, parent, false);
        return new CartProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductViewHolder holder, int position)
    {
       CartModel model = cartModels.get(position);
    }

    @Override
    public int getItemCount()
    {
        return cartModels.size();
    }

    public class CartProductViewHolder extends RecyclerView.ViewHolder
    {


        public CartProductViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
