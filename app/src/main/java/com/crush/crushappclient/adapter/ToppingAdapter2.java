package com.crush.crushappclient.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.DBHelper.ToppingHelper;
import com.crush.crushappclient.Interface.OnToppingClickedListener;
import com.crush.crushappclient.activity.ProductInfoActivity;
import com.crush.crushappclient.R;
import com.crush.crushappclient.model.Topping;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ToppingAdapter2 extends RecyclerView.Adapter<ToppingAdapter2.RecyclerViewHolder> {
    private Context context;
    private List<Topping> toppingList;
    private List<Integer> selectedPosition = new ArrayList<>();
    private OnToppingClickedListener mListener;

    public ToppingAdapter2(Context context) {
        this.context = context;
        this.toppingList = ToppingHelper.getInstance().gets();
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.topping_item_layout, null);

        return new RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        Topping topping = toppingList.get(position);
        Picasso.with(context).load(topping.getImageURL()).placeholder(R.drawable.show_loader).into(holder.imgvTopping);
        holder.txtvName.setText(topping.getName());
        holder.txtvPrice.setText(String.valueOf(topping.getPrice()));
        holder.imgvCheck.setVisibility(selectedPosition.contains(position) ? View.VISIBLE : View.INVISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPosition.contains(position)) {
                    if (selectedPosition.indexOf(position) >= 0)
                        selectedPosition.remove(selectedPosition.indexOf(position));
                    if (mListener != null) {
                        mListener.OnDeselected(toppingList.get(position));
                    }
                } else {
                    if (selectedPosition.indexOf(position) < 0)
                        selectedPosition.add(position);
                    if (mListener != null) {
                        mListener.OnSelected(toppingList.get(position));
                    }
                }
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toppingList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgvCheck;
        ImageView imgvTopping;
        TextView txtvName;
        TextView txtvPrice;


        private RecyclerViewHolder(final View itemView) {
            super(itemView);
            imgvTopping = (ImageView) itemView.findViewById(R.id.imgvtopping);
            txtvName = (TextView) itemView.findViewById(R.id.txtvname);
            txtvPrice = (TextView) itemView.findViewById(R.id.txtvprice);
            imgvCheck = (ImageView) itemView.findViewById(R.id.imgvcheck);
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

}
