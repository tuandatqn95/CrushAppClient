package com.crush.crushappclient.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crush.crushappclient.activity.ProductInfoActivity;
import com.crush.crushappclient.R;
import com.crush.crushappclient.ClassSupport.SeparatorDecoration;
import com.crush.crushappclient.adapter.MainDrinkAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabProductFragment extends Fragment {

    private static final String TAG = TabProductFragment.class.getSimpleName();

    private static final String ARG_PARAM = "CATEGORY_ID";
    public static final int REQUEST_CODE = 9;

    @BindView(R.id.recyclerViewProduct)
    RecyclerView recyclerViewProduct;

    MainDrinkAdapter mAdapter;

    String categoryId;
    DocumentReference categoryRef;

    FirebaseFirestore mFirestore;
    Query mQuery;


    public TabProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null) {
            categoryId =  getArguments().getString(ARG_PARAM);

        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab_product, container, false);
        ButterKnife.bind(this,rootView);

        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("categories").document(categoryId).collection("maindrinks");


        mAdapter = new MainDrinkAdapter(mQuery, new MainDrinkAdapter.OnMaindrinkSelectedListener() {
            @Override
            public void OnMaindrinkClicked(DocumentSnapshot snapshot) {
                Intent intent = new Intent(getActivity(),ProductInfoActivity.class);
                Log.d(TAG, "OnMaindrinkClicked: "+getActivity());
                intent.putExtra(ProductInfoActivity.ARG_PARAM_1, categoryId);
                intent.putExtra(ProductInfoActivity.ARG_PARAM_2,snapshot.getId());
                getActivity().startActivityForResult(intent,REQUEST_CODE);
            }
        });

        recyclerViewProduct.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerViewProduct.setLayoutManager(layoutManager);
        recyclerViewProduct.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProduct.addItemDecoration(new SeparatorDecoration(getActivity(),Color.TRANSPARENT,3f));


        return rootView;
    }



    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter != null)
            mAdapter.startListening();
    }



    @Override
    public void onStop() {
        super.onStop();
        if(mAdapter != null)
            mAdapter.stopListening();
    }

    public static TabProductFragment newInstance(String categoryId) {

        TabProductFragment fragment = new TabProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, categoryId);
        fragment.setArguments(args);
        return fragment;
    }

}
