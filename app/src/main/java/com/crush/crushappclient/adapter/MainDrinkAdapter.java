package com.crush.crushappclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class MainDrinkAdapter extends RecyclerView.Adapter<MainDrinkAdapter.RecyclerViewHolder> {
    private Context context;
    private List<MainDrink> listMainDrink;
    private OnItemClickedListener onItemClickedListener;

    public MainDrinkAdapter(Context context, List<MainDrink> listMainDrink) {
        this.context = context;
        this.listMainDrink = listMainDrink;
    }

    @Override
    public MainDrinkAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.product_item_layout, null);
        return new MainDrinkAdapter.RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MainDrinkAdapter.RecyclerViewHolder holder,int position) {
        final MainDrink mainDrink = listMainDrink.get(position);
        holder.txtvName.setText(mainDrink.getName());
        holder.txtvPrice.setText(mainDrink.getPrice()+"");
        holder.imgvProduct.setImageResource(R.drawable.trasua);
        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(mainDrink);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listMainDrink.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imgvProduct;
        TextView txtvName;
        TextView txtvPrice;
        LinearLayout line;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imgvProduct = (ImageView) itemView.findViewById(R.id.imgvproduct);
            txtvName = (TextView) itemView.findViewById(R.id.txtvname);
            txtvPrice = (TextView) itemView.findViewById(R.id.txtvprice);
            line = (LinearLayout) itemView.findViewById(R.id.product_item_line);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
    }
    public interface OnItemClickedListener {
        void onItemClick(MainDrink mainDrink);
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
