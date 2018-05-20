package com.crush.crushappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.DBHelper.ToppingHelper;
import com.crush.crushappclient.adapter.NotificationAdapter;
import com.crush.crushappclient.adapter.ToppingAdapter;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.model.Topping;

import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {
    private TextView txtvName,txtvPrice;
    private ImageView imgvDrink;
    private LinearLayout linearLayout;
    private NumberPicker npQuantity;
    private RecyclerView recyclerView;
    ToppingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        adapter.notifyDataSetChanged();
        getMainDrink();

    }

    private void getMainDrink() {
        Intent intent = getIntent();
        MainDrink mainDrink = (MainDrink) intent.getSerializableExtra("mainDrink");
        txtvName.setText(mainDrink.getName());
        txtvPrice.setText(mainDrink.getPrice()+"");
        //imgvDrink.setImageResource(Integer.valueOf(mainDrink.getImage()));

    }

    private void init() {
        txtvName = (TextView) findViewById(R.id.txtvdrinkName);
        txtvPrice = (TextView) findViewById(R.id.txtvdrinkPrice);
        imgvDrink = (ImageView) findViewById(R.id.imageViewDrink);

        npQuantity = (NumberPicker) findViewById(R.id.numberPickerQuantity);
        npQuantity.setMinValue(1);
        npQuantity.setMaxValue(50);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTopping);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Topping> toppings = ToppingHelper.getInstance().getTopping();
        adapter = new ToppingAdapter(this,toppings);
        adapter.setOnItemClickedListener(new ToppingAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(Topping topping) {

            }
        });
        recyclerView.setAdapter(adapter);

    }
}
