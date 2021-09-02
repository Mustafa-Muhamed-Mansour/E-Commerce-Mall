package com.e_commerce_mall.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.ProductModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchProductAdapter extends FirebaseRecyclerAdapter<ProductModel, SearchProductAdapter.SearchProductViewHolder>
{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SearchProductAdapter(@NonNull FirebaseRecyclerOptions<ProductModel> options)
    {
        super(options);
    }

    @NonNull
    @Override
    public SearchProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_search, parent, false);
        return new SearchProductViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchProductViewHolder holder, int position, @NonNull ProductModel model)
    {
        holder.textSearchProductName.setText(model.getProductName());
        holder.textSearchProductDescription.setText(model.getProductDescription());
        holder.textSearchProductPrice.setText(model.getProductPrice() + " £");
        Picasso
                .get()
                .load(model.getProductImage())
                .into(holder.searchProductImage);
    }

    public class SearchProductViewHolder extends RecyclerView.ViewHolder
    {

        private CircleImageView searchProductImage;
        private TextView textSearchProductName, textSearchProductDescription, textSearchProductPrice;

        public SearchProductViewHolder(@NonNull View itemView)
        {
            super(itemView);

            searchProductImage = itemView.findViewById(R.id.item_search_product_img);
            textSearchProductName = itemView.findViewById(R.id.txt_item_search_product_name);
            textSearchProductDescription = itemView.findViewById(R.id.txt_item_search_product_description);
            textSearchProductPrice = itemView.findViewById(R.id.txt_item_search_product_price);
        }
    }
}
