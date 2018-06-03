package com.crush.crushappclient.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.crush.crushappclient.ClassSupport.SeparatorDecoration;
import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.OrderItemAdapter;
import com.crush.crushappclient.fragment.ProductFragment;
import com.crush.crushappclient.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {
    private static final String TAG = "CartActivity";
    @BindView(R.id.recyclerView_order_drink)
    RecyclerView rvOrderDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList = (List<OrderItem>) intent.getSerializableExtra(ProductFragment.LIST_ORDER_ITEM);
        OrderItemAdapter adapter = new OrderItemAdapter(orderItemList);

        rvOrderDrink.setAdapter(adapter);
        rvOrderDrink.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDrink.setItemAnimator(new DefaultItemAnimator());
        rvOrderDrink.addItemDecoration(new DividerItemDecoration(rvOrderDrink.getContext(),DividerItemDecoration.VERTICAL));

    }


}
