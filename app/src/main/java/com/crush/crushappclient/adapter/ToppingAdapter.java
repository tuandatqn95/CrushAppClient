package com.crush.crushappclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.Topping;

import java.util.List;

public class ToppingAdapter extends BaseAdapter {
    private Context context;
    private List<Topping> listTopping;

    public ToppingAdapter(Context context, List<Topping> listTopping) {
        this.context = context;
        this.listTopping = listTopping;
    }

    @Override
    public int getCount() {
        return listTopping.size();
    }

    @Override
    public Object getItem(int i) {
        return listTopping.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listTopping.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.topping_item_layout, null);

        ImageView imgvProduct = (ImageView) item.findViewById(R.id.imgvproduct);
        TextView txtvName = (TextView) item.findViewById(R.id.txtvname);
        TextView txtvPrice = (TextView) item.findViewById(R.id.txtvprice);


        // gán giá trị
        Topping topping = listTopping.get(i);
        txtvName.setText(topping.getName());
        txtvPrice.setText(topping.getPrice()+"");
        // byte array to imageView
        //Bitmap imageProduct = BitmapFactory.decodeByteArray(mainDrink.getImage(), 0, mainDrink.getImage().length);
        imgvProduct.setImageResource(R.drawable.topping);

        return item;
    }


}
