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
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.util.StringFormatUtils;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainDrinkAdapter extends FirestoreAdapter<MainDrinkAdapter.ViewHolder> {

    public interface OnMaindrinkSelectedListener{
        void OnMaindrinkClicked(DocumentSnapshot snapshot);
    }

    private OnMaindrinkSelectedListener mListener;

    public MainDrinkAdapter(Query query,OnMaindrinkSelectedListener listener) {
        super(query);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MainDrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MainDrinkAdapter.ViewHolder(inflater.inflate(R.layout.product_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainDrinkAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position),mListener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_image)
        ImageView drinkImage;

        @BindView(R.id.drink_name)
        TextView maindrinkName;

        @BindView(R.id.drink_price)
        TextView maindrinkPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final DocumentSnapshot snapshot, final OnMaindrinkSelectedListener mListener) {
            MainDrink mainDrink = snapshot.toObject(MainDrink.class);
            Glide.with(drinkImage.getContext()).load(mainDrink.getImageURL()).dontAnimate().placeholder(R.drawable.loading).error(R.drawable.default_drink).into(drinkImage);
            maindrinkName.setText(mainDrink.getName());
            maindrinkPrice.setText(StringFormatUtils.FormatCurrency(mainDrink.getPrice()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null)
                        mListener.OnMaindrinkClicked(snapshot);
                }
            });
        }
    }
}
