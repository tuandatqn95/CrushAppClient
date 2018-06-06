package com.crush.crushappclient.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.OrderItem;
import com.crush.crushappclient.util.StringFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private List<OrderItem> orderItemList;
    private int selectedPosition;

    public interface OnOrderItemSelectedListener {
        void OnOrderItemDelete(int position);
    }

    private OnOrderItemSelectedListener mListener;

    public OrderItemAdapter(List<OrderItem> orderItemList, OnOrderItemSelectedListener mListener) {
        this.orderItemList = orderItemList;
        this.mListener = mListener;
    }


    @NonNull
    @Override
    public OrderItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new OrderItemAdapter.ViewHolder(inflater.inflate(R.layout.order_item_layout, parent, false));

        return new OrderItemAdapter.ViewHolder(inflater.inflate(R.layout.order_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.ViewHolder holder, int position) {
        holder.bind(orderItemList.get(position), position, mListener);
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.orderitem_drink_name)
        TextView drinkName;

        @BindView(R.id.orderitem_drink_price)
        TextView drinkPrice;

        @BindView(R.id.orderitem_drink_quanlity)
        TextView drinkQuantity;

        @BindView(R.id.recyclerViewOrderTopping)
        RecyclerView recyclerViewTopping;

        @BindView(R.id.delete_order)
        ImageView deleteOrder;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            GradientDrawable background = new GradientDrawable();
            background.setShape(GradientDrawable.RECTANGLE);
            background.setCornerRadius(25);
            background.setStroke(1, Color.BLACK);

            drinkQuantity.setBackground(background);
        }

        public void bind(final OrderItem orderItem, final int position, final OnOrderItemSelectedListener mListener) {

            drinkName.setText(orderItem.getMaindrink().getName());
            drinkQuantity.setText(orderItem.getQuantity() + "");
            drinkPrice.setText(StringFormatUtils.FormatCurrency(orderItem.getMaindrink().getPrice() * orderItem.getQuantity()));
            final OrderToppingAdapter adapter = new OrderToppingAdapter(orderItem.getToppings(), orderItem.getQuantity());
            recyclerViewTopping.setAdapter(adapter);
            recyclerViewTopping.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            recyclerViewTopping.setItemAnimator(new DefaultItemAnimator());
            deleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderItemList.remove(position);
                    notifyDataSetChanged();
                    if (mListener != null)
                        mListener.OnOrderItemDelete(position);
                }
            });

        }

    }
}
