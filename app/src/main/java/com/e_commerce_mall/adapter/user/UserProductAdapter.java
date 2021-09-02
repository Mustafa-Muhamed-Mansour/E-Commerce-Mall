package com.e_commerce_mall.adapter.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.UserProductViewHolder>
{

    private ArrayList<ProductModel> productModels;

    public UserProductAdapter(ArrayList<ProductModel> productModels)
    {
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public UserProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent, false);
        return new UserProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProductViewHolder holder, int position)
    {
        ProductModel model = productModels.get(position);
        holder.textUserProductName.setText(model.getProductName());
        holder.textUserProductDescription.setText(model.getProductDescription());
        holder.textUserProductPrice.setText(model.getProductPrice() + " £");
        Picasso
                .get()
                .load(model.getProductImage())
                .placeholder(R.drawable.ic_add_new_product)
                .error(R.drawable.ic_add_new_product)
                .into(holder.userProductImage);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle detailsProductBundle = new Bundle();
                detailsProductBundle.putString("randomKey", model.getRandomKey());
                detailsProductBundle.putString("productName", model.getProductName());
                detailsProductBundle.putString("productDescription", model.getProductDescription());
                detailsProductBundle.putString("productPrice", model.getProductPrice());
                detailsProductBundle.putString("productImage", model.getProductImage());
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailsProductFragment, detailsProductBundle);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return productModels.size();
    }

    public class UserProductViewHolder extends RecyclerView.ViewHolder
    {

        private CircleImageView userProductImage;
        private TextView textUserProductName, textUserProductDescription, textUserProductPrice;

        public UserProductViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userProductImage = itemView.findViewById(R.id.item_user_product_img);
            textUserProductName = itemView.findViewById(R.id.txt_item_user_product_name);
            textUserProductDescription = itemView.findViewById(R.id.txt_item_user_product_description);
            textUserProductPrice = itemView.findViewById(R.id.txt_item_user_product_price);
        }
    }
}
