package com.e_commerce_mall.adapter.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>
{

    ArrayList<OrderModel> orderModels;

    public OrderAdapter(ArrayList<OrderModel> orderModels)
    {
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position)
    {
        OrderModel model = orderModels.get(position);
    }

    @Override
    public int getItemCount()
    {
        return orderModels.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder
    {

        public OrderViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
