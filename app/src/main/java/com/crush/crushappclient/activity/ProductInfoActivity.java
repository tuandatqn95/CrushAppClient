package com.crush.crushappclient.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.DBHelper.ToppingHelper;
import com.crush.crushappclient.Interface.OnToppingClickedListener;
import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.NotificationAdapter;
import com.crush.crushappclient.adapter.ToppingAdapter;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.model.OrderItem;
import com.crush.crushappclient.model.Topping;

import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {
    public static final int ADD_TOPPING = 1;
    public static final int REMOVE_TOPPING = 2;
    private TextView txtvName, txtvPrice;
    private ImageView imgvDrink,imgvBack;
    private Button btnOrder;
    private LinearLayout linearLayout;
    private NumberPicker npQuantity;
    private RecyclerView recyclerView;
    private OrderItem orderItem;
    private ToppingAdapter adapter;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
        adapter.notifyDataSetChanged();
        //getMainDrink();
    }

    private void init() {
        txtvName = (TextView) findViewById(R.id.txtvdrinkName);
        txtvPrice = (TextView) findViewById(R.id.txtvdrinkPrice);
        imgvDrink = (ImageView) findViewById(R.id.imageViewDrink);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        imgvBack = (ImageView) findViewById(R.id.imgvback);
        npQuantity = (NumberPicker) findViewById(R.id.numberPickerQuantity);
        npQuantity.setMinValue(1);
        npQuantity.setMaxValue(50);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTopping);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //List<Topping> toppings = ToppingHelper.getInstance().gets();
        orderItem = new OrderItem();
        orderItem.setMainDrink(getMainDrink());
        txtvName.setText(orderItem.getMainDrink().getName());
        txtvPrice.setText(orderItem.getMainDrink().getPrice() + "");

        adapter = new ToppingAdapter(this);
        adapter.setOnToppingClickedListener(new OnToppingClickedListener() {
            @Override
            public void OnSelected(Topping topping) {
                orderItem.getToppingList().add(topping);
                txtvPrice.setText(orderItem.getPrice() + "");
            }

            @Override
            public void OnDeselected(Topping topping) {
                orderItem.getToppingList().remove(topping);
                txtvPrice.setText(orderItem.getPrice() + "");
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private MainDrink getMainDrink() {
        Intent intent = getIntent();
        MainDrink mainDrink = (MainDrink) intent.getSerializableExtra("mainDrink");

        return mainDrink;
    }
    private void addEvent(){
        findViewById(R.id.imageViewDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
