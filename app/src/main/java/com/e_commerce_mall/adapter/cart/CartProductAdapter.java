package com.e_commerce_mall.adapter.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.CartModel;
import com.e_commerce_mall.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>
{

    public static int totalPrice = 0, productTotalPrice;

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
       holder.textCartProductName.setText(model.getCartProductName());
       holder.textCartProductDescription.setText(model.getCartProductDescription());
       holder.textCartrProductPrice.setText(model.getCartProductPrice() + " £");
       holder.textCartrProductQuantity.setText(model.getCartProductQuantity());
       Picasso
                .get()
                .load(model.getCartProductImage())
                .placeholder(R.drawable.ic_add_new_product)
                .error(R.drawable.ic_add_new_product)
                .into(holder.cartProductImage);

        productTotalPrice = ((Integer.valueOf(model.getCartProductPrice())) * (Integer.valueOf(model.getCartProductQuantity())));
//        productTotalPrice = ((Integer.parseInt(model.getCategory_price())) * (Integer.parseInt(model.getNumber_quantity())));
        totalPrice = totalPrice + productTotalPrice;
    }

    @Override
    public int getItemCount()
    {
        return cartModels.size();
    }

    public class CartProductViewHolder extends RecyclerView.ViewHolder
    {

        private CircleImageView cartProductImage;
        private TextView textCartProductName, textCartProductDescription, textCartrProductPrice, textCartrProductQuantity;

        public CartProductViewHolder(@NonNull View itemView)
        {

            super(itemView);

            cartProductImage = itemView.findViewById(R.id.item_cart_product_img);
            textCartProductName = itemView.findViewById(R.id.txt_item_cart_product_name);
            textCartProductDescription = itemView.findViewById(R.id.txt_item_cart_product_description);
            textCartrProductPrice = itemView.findViewById(R.id.txt_item_cart_product_price);
            textCartrProductQuantity = itemView.findViewById(R.id.txt_item_cart_product_quantity);
        }
    }
}
