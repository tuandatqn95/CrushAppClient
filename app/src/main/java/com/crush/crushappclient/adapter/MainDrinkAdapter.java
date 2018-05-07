package com.crush.crushappclient.adapter;

import android.content.Context;
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
    private List<MainDrink> listMainDrink;
    public MainDrinkAdapter(Context context, List<MainDrink> listMainDrink) {
        this.context = context;
        this.listMainDrink = listMainDrink;
    }

    @Override
    public int getCount() {
        return listMainDrink.size();
    }

    @Override
    public Object getItem(int i) {
        return listMainDrink.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        ImageView imgvProduct;
        TextView txtvName;
        TextView txtvPrice;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        View item = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.product_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imgvProduct = (ImageView) item.findViewById(R.id.imgvproduct);
            viewHolder.txtvName = (TextView) item.findViewById(R.id.txtvname);
            viewHolder.txtvPrice = (TextView) item.findViewById(R.id.txtvprice);
            item.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) item.getTag();
        }



        // gán giá trị
        MainDrink mainDrink = listMainDrink.get(i);
        viewHolder.txtvName.setText(mainDrink.getName());
        viewHolder.txtvPrice.setText(mainDrink.getPrice()+"");
        // byte array to imageView
        //Bitmap imageProduct = BitmapFactory.decodeByteArray(mainDrink.getImage(), 0, mainDrink.getImage().length);
        viewHolder.imgvProduct.setImageResource(R.drawable.trasua);

        return item;
    }


}
