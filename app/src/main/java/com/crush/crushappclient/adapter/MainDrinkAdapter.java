package com.crush.crushappclient.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.MainDrink;

import java.util.ArrayList;
import java.util.List;

public class MainDrinkAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MainDrink> listMainDrink;

    public MainDrinkAdapter(Context context, ArrayList<MainDrink> listMainDrink) {
        this.context = context;
        this.listMainDrink = listMainDrink;
    }

    @Override
    public int getCount() {
        return listMainDrink.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgvProduct;
        TextView txtvName;
        TextView txtvPrice;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.product_layout, null);

        ImageView imgvProduct = (ImageView) item.findViewById(R.id.imgvproduct);
        TextView txtvName = (TextView) item.findViewById(R.id.txtvname);
        TextView txtvPrice = (TextView) item.findViewById(R.id.txtvprice);


        // gán giá trị
        MainDrink mainDrink = listMainDrink.get(i);
        txtvName.setText(mainDrink.getName());
        txtvPrice.setText(mainDrink.getPrice()+"");
        // byte array to imageView
        Bitmap imageProduct = BitmapFactory.decodeByteArray(mainDrink.getImage(), 0, mainDrink.getImage().length);
        imgvProduct.setImageBitmap(imageProduct);

        return item;
    }
}
