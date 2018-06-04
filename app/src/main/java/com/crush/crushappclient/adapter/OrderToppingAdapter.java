package com.crush.crushappclient.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.fragment.model.Topping;
import com.crush.crushappclient.util.StringFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderToppingAdapter extends RecyclerView.Adapter<OrderToppingAdapter.ViewHolder>{

    private List<Topping> toppingList;
    private long quantity;

    public OrderToppingAdapter(List<Topping> toppingList, long quantity) {
        this.toppingList = toppingList;
        this.quantity = quantity;
    }

    public OrderToppingAdapter(){}

    @NonNull
    @Override
    public OrderToppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new OrderToppingAdapter.ViewHolder(inflater.inflate(R.layout.order_item_topping_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderToppingAdapter.ViewHolder holder, int position) {
       holder.bind(toppingList.get(position),quantity);
    }

    @Override
    public int getItemCount() {
        return toppingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.orderitem_topping_name)
        TextView toppingName;

        @BindView(R.id.orderitem_topping_quanlity)
        TextView toppingQuanlity;

        @BindView(R.id.orderitem_topping_price)
        TextView toppingPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Topping topping, long quantity) {
            toppingName.setText(topping.getName());
            toppingPrice.setText(StringFormatUtils.FormatCurrency(topping.getPrice()*quantity));
            toppingQuanlity.setText(quantity+"");

            GradientDrawable background = new GradientDrawable();
            background.setShape(GradientDrawable.RECTANGLE);
            background.setCornerRadius(25);
            background.setStroke(1, Color.BLACK);
            toppingQuanlity.setBackground(background);
        }
    }
}
