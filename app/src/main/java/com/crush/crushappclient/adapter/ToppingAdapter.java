package com.crush.crushappclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.model.Topping;

import java.util.List;

public class ToppingAdapter extends RecyclerView.Adapter<ToppingAdapter.RecyclerViewHolder> {
    private Context context;
    private List<Topping> toppingList;
    private OnItemClickedListener onItemClickedListener;

    public ToppingAdapter(Context context, List<Topping> toppingList) {
        this.context = context;
        this.toppingList = toppingList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.topping_item_layout, null);
        
        return new RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder,final int position) {
        Topping topping = toppingList.get(position);
        holder.imgvTopping.setImageResource(R.drawable.topping);
        holder.txtvName.setText(topping.getName());
        holder.txtvPrice.setText(topping.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(toppingList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return toppingList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imgvTopping;
        TextView txtvName;
        TextView txtvPrice;

        private RecyclerViewHolder(final View itemView) {
            super(itemView);
            imgvTopping = (ImageView) itemView.findViewById(R.id.imgvtopping);
            txtvName = (TextView) itemView.findViewById(R.id.txtvname);
            txtvPrice = (TextView) itemView.findViewById(R.id.txtvprice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, itemView.getHeight()+"", Toast.LENGTH_SHORT).show();
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
        void onItemClick(Topping topping);
    }
    public void setOnItemClickedListener(ToppingAdapter.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }


}
