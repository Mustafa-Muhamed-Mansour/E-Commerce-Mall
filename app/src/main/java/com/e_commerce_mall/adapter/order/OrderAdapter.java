package com.e_commerce_mall.adapter.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_commerce_mall.R;
import com.e_commerce_mall.model.OrderModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.textOrderPaied.setText(model.getOrderPaied());
        holder.textOrderUserName.setText(model.getOrderUserName());
        holder.textOrderUserPhone.setText(model.getOrderUserPhone());
        holder.textOrderUserAddress.setText(model.getOrderUserAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference();
                deleteRef.child("Order's").child(model.getRandomKey()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return orderModels.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder
    {

        private TextView textOrderPaied, textOrderUserName, textOrderUserPhone, textOrderUserAddress;

        public OrderViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textOrderPaied = itemView.findViewById(R.id.txt_item_order_paied);
            textOrderUserName = itemView.findViewById(R.id.txt_item_order_user_name);
            textOrderUserPhone = itemView.findViewById(R.id.txt_item_order_user_phone);
            textOrderUserAddress = itemView.findViewById(R.id.txt_item_order_user_address);
        }
    }
}
