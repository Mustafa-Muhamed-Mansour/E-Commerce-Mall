package com.e_commerce_mall.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchUserProductAdapter extends RecyclerView.Adapter<SearchUserProductAdapter.SearchUserProductViewHolder>
{

    ArrayList<ProductModel> productModels;

    public SearchUserProductAdapter(ArrayList<ProductModel> productModels)
    {
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public SearchUserProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent, false);
        return new SearchUserProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUserProductViewHolder holder, int position)
    {
        ProductModel model = productModels.get(position);
        holder.textSearchUserProductName.setText(model.getProductName());
        holder.textSearchUserProductDescription.setText(model.getProductDescription());
        holder.textSearchUserProductPrice.setText(model.getProductPrice() + " £");
        Picasso
                .get()
                .load(model.getProductImage())
                .placeholder(R.drawable.ic_add_new_product)
                .error(R.drawable.ic_add_new_product)
                .into(holder.searchUserProductImage);
    }

    @Override
    public int getItemCount()
    {
        return productModels.size();
    }

    public class SearchUserProductViewHolder extends RecyclerView.ViewHolder
    {

        private CircleImageView searchUserProductImage;
        private TextView textSearchUserProductName, textSearchUserProductDescription, textSearchUserProductPrice;

        public SearchUserProductViewHolder(@NonNull View itemView)
        {
            super(itemView);

            searchUserProductImage = itemView.findViewById(R.id.item_user_product_img);
            textSearchUserProductName = itemView.findViewById(R.id.txt_item_user_product_name);
            textSearchUserProductDescription = itemView.findViewById(R.id.txt_item_user_product_description);
            textSearchUserProductPrice = itemView.findViewById(R.id.txt_item_user_product_price);
        }
    }
}
