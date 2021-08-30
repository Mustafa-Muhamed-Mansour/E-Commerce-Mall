package com.e_commerce_mall.adapter.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.AdminProductViewHolder>
{

    ArrayList<ProductModel> productModels;

    public AdminProductAdapter(ArrayList<ProductModel> productModels)
    {
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public AdminProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_admin, parent, false);
        return new AdminProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminProductViewHolder holder, int position)
    {
        ProductModel model = productModels.get(position);
        holder.textAdminProductName.setText(model.getProductName());
        holder.textAdminProductDescription.setText(model.getProductDescription());
        holder.textAdminProductPrice.setText(model.getProductPrice() + " £");
        Picasso
                .get()
                .load(model.getProductImage())
                .placeholder(R.drawable.ic_add_new_product)
                .error(R.drawable.ic_add_new_product)
                .into(holder.adminProductImage);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle editProduct = new Bundle();
                editProduct.putString("productID", model.getProductID());
                editProduct.putString("randomKey", model.getRandomKey());
                editProduct.putString("productImage", model.getProductImage());
                editProduct.putString("productName", model.getProductName());
                editProduct.putString("productDescription", model.getProductDescription());
                editProduct.putString("productPrice", model.getProductPrice());
                Navigation.findNavController(view).navigate(R.id.action_productsMaintenanceFragment_to_productEditFragment, editProduct);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return productModels.size();
    }

    public class AdminProductViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView adminProductImage;
        private TextView textAdminProductName, textAdminProductDescription, textAdminProductPrice;

        public AdminProductViewHolder(@NonNull View itemView)
        {
            super(itemView);

            adminProductImage = itemView.findViewById(R.id.item_admin_product_img);
            textAdminProductName = itemView.findViewById(R.id.txt_item_admin_product_name);
            textAdminProductDescription = itemView.findViewById(R.id.txt_item_admin_product_description);
            textAdminProductPrice = itemView.findViewById(R.id.txt_item_admin_product_price);
        }
    }
}
