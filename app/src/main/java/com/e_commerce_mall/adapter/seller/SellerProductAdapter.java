package com.e_commerce_mall.adapter.seller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SellerProductAdapter extends RecyclerView.Adapter<SellerProductAdapter.SellerProductViewHolder>
{

    ArrayList<ProductModel> productModels;

    public SellerProductAdapter(ArrayList<ProductModel> productModels)
    {
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public SellerProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_seller, parent, false);
        return new SellerProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerProductViewHolder holder, int position)
    {

        ProductModel model = productModels.get(position);
        holder.textSellerProductName.setText(model.getProductName());
        holder.textSellerProductDescription.setText(model.getProductDescription());
        holder.textSellerProductPrice.setText(model.getProductPrice() + " £");
        Picasso
                .get()
                .load(model.getProductImage())
                .placeholder(R.drawable.ic_add_new_product)
                .error(R.drawable.ic_add_new_product)
                .into(holder.sellerProductImage);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(holder.itemView.getContext(), "ID = " + model.getProductID(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return productModels.size();
    }

    public class SellerProductViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView sellerProductImage;
        private TextView textSellerProductName, textSellerProductDescription, textSellerProductPrice;

        public SellerProductViewHolder(@NonNull View itemView)
        {
            super(itemView);

            sellerProductImage = itemView.findViewById(R.id.item_seller_product_img);
            textSellerProductName = itemView.findViewById(R.id.txt_item_seller_product_name);
            textSellerProductDescription = itemView.findViewById(R.id.txt_item_seller_product_description);
            textSellerProductPrice = itemView.findViewById(R.id.txt_item_seller_product_price);
        }
    }
}
