package com.crush.crushappclient.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crush.crushappclient.R;
import com.crush.crushappclient.model.Topping;
import com.crush.crushappclient.util.StringFormatUtils;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ToppingAdapter extends FirestoreAdapter<ToppingAdapter.ViewHolder> {

    public interface OnToppingSelectedListener {

        void OnToppingSelected(DocumentSnapshot snapshot);

        void OnToppingDeselected(DocumentSnapshot snapshot);
    }

    private List<Integer> selectedPosition;

    private OnToppingSelectedListener mListener;

    public ToppingAdapter(Query query, OnToppingSelectedListener listener) {
        super(query);
        this.mListener = listener;
        this.selectedPosition = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.topping_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), position, mListener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgvtopping)
        ImageView toppingImage;

        @BindView(R.id.txtvname)
        TextView toppingName;

        @BindView(R.id.txtvprice)
        TextView toppingPrice;

        @BindView(R.id.imgvcheck)
        ImageView selectedTopping;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot, int position, final OnToppingSelectedListener mListener) {
            Topping topping = snapshot.toObject(Topping.class);
            Glide.with(toppingImage.getContext()).load(topping.getImageURL()).placeholder(R.drawable.loading).error(R.drawable.default_topping).into(toppingImage);
            toppingName.setText(topping.getName());
            toppingPrice.setText(StringFormatUtils.FormatCurrency(topping.getPrice()));
            selectedTopping.setImageResource(R.drawable.check);
            boolean isSelected = selectedTopping.getVisibility() == View.VISIBLE;
            selectedTopping.setVisibility(isSelected ? View.VISIBLE : View.INVISIBLE);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        if (selectedTopping.getVisibility() == View.VISIBLE) {
                            selectedTopping.setVisibility(View.INVISIBLE);
                            mListener.OnToppingDeselected(snapshot);
                        } else {
                            selectedTopping.setVisibility(View.VISIBLE);
                            mListener.OnToppingSelected(snapshot);
                        }
                    }

                }
            });
        }
    }
}
