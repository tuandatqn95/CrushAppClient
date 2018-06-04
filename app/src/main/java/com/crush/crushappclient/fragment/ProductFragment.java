package com.crush.crushappclient.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.activity.CartActivity;
import com.crush.crushappclient.activity.ProductInfoActivity;
import com.crush.crushappclient.adapter.TabViewPaperAdapter;
import com.crush.crushappclient.fragment.model.OrderItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProductFragment extends Fragment {


    public static final String LIST_ORDER_ITEM = "LIST_ORDER_ITEM";
    public static final int REQUEST_CODE_ORDER_COMPLETE = 11;
    private final String TAG = ProductFragment.class.getSimpleName();

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPaper)
    ViewPager viewPaper;

    @BindView(R.id.shopping_cart)
    ImageView cart;

    @BindView(R.id.cart_quantity)
    TextView cart_quantity;

    private OrderItem orderItem;

    private FirebaseFirestore mFirestore;
    private Query mQuery;

    TabViewPaperAdapter mAdapter;
    private long quantity = 0;
    private List<OrderItem> orderItemList = new ArrayList<>();

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, rootView);

        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("categories");

        mAdapter = new TabViewPaperAdapter(mQuery, getFragmentManager());
        viewPaper.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPaper);

        init();
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null)
            mAdapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null)
            mAdapter.stopListening();
    }

    public Object getOrderItem() {

        return orderItem;
    }

    private void init() {
        GradientDrawable cartBackground = new GradientDrawable();
        cartBackground.setShape(GradientDrawable.RECTANGLE);
        cartBackground.setCornerRadius(25);
        cartBackground.setColor(Color.RED);
        cart_quantity.setBackground(cartBackground);
        setCartQuantity(quantity);
    }

    @OnClick(R.id.shopping_cart)
    public void OnCartClicked(View view){
        Intent intent = new Intent(getActivity(), CartActivity.class);
        intent.putExtra(LIST_ORDER_ITEM, (Serializable) orderItemList);
        getActivity().startActivityForResult(intent, REQUEST_CODE_ORDER_COMPLETE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TabProductFragment.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                OrderItem orderItem = (OrderItem) data.getSerializableExtra(ProductInfoActivity.KEY_ORDER_ITEM);
                orderItemList.add(orderItem);
                setCartQuantity(quantity+orderItem.getQuantity());
                Log.d(TAG, "onActivityResult:" + orderItemList.size());
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
        if (requestCode == REQUEST_CODE_ORDER_COMPLETE) {
            // TODO: 6/3/2018 Delete order when complete
            if(resultCode == Activity.RESULT_OK) {
                orderItemList.clear();
                setCartQuantity(0);
                Toast.makeText(getActivity(), "Order successfully!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setCartQuantity(long quan){
        quantity = quan;
        cart_quantity.setText(quantity + "");
    }
}
