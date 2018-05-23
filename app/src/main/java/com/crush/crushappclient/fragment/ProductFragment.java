package com.crush.crushappclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crush.crushappclient.DBHelper.CategoryDBHelper;
import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.TabViewPaperAdapter;
import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.OrderItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifTextView;


public class ProductFragment extends Fragment {


    private final String TAG = ProductFragment.class.getSimpleName();

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPaper)
    ViewPager viewPaper;

    private OrderItem orderItem;

    private FirebaseFirestore mFirestore;
    private Query mQuery;

    TabViewPaperAdapter mAdapter;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this,rootView);

        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("categories");

        mAdapter = new TabViewPaperAdapter(mQuery, getFragmentManager());
        viewPaper.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPaper);


        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter!=null)
            mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAdapter!=null)
            mAdapter.stopListening();
    }

    public Object getOrderItem() {

        return orderItem;
    }
}
