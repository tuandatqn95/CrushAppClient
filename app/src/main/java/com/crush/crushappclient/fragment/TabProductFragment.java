package com.crush.crushappclient.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.crush.crushappclient.SeparatorDecoration;
import com.crush.crushappclient.adapter.MainDrinkAdapter;
import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.MainDrink;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifTextView;


public class TabProductFragment extends Fragment {

    private static final String TAG = TabProductFragment.class.getSimpleName();

    private static final String ARG_PARAM = "CATEGORY";
    private GifTextView gifTextViewLoading;
    private RecyclerView recyclerViewProduct;
    private Category category;
    private List<MainDrink> mainDrinkList = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public TabProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            category = (Category) getArguments().getSerializable(ARG_PARAM);
            Log.d(TAG, "Received: "+category.getId()+"-"+category.getName());
        }
        View rootView = inflater.inflate(R.layout.fragment_tab_product, container, false);
        gifTextViewLoading = (GifTextView) rootView.findViewById(R.id.loadingGif);
        gifTextViewLoading.setVisibility(View.VISIBLE);

        recyclerViewProduct = (RecyclerView) rootView.findViewById(R.id.recyclerViewProduct);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerViewProduct.setLayoutManager(layoutManager);
        recyclerViewProduct.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProduct.addItemDecoration(new SeparatorDecoration(getActivity(),Color.TRANSPARENT,3f));

        Log.d(TAG, category.getName());

        db.collection("categories").document(category.getId()).collection("maindrinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    mainDrinkList.clear();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        mainDrinkList.add(document.toObject(MainDrink.class));
                    }
                    setupRecyclerView(recyclerViewProduct, mainDrinkList);

                }
            }
        });


        return rootView;
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<MainDrink> categories) {
        MainDrinkAdapter adapter = new MainDrinkAdapter(getActivity(), mainDrinkList);
        adapter.setOnItemClickedListener(new MainDrinkAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(MainDrink mainDrink) {
                if(mainDrink.getName()==null){
                    return;
                }
                Intent intent = new Intent(getActivity(),ProductInfoActivity.class);
                intent.putExtra("mainDrink",(Serializable) mainDrink);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        gifTextViewLoading.setVisibility(View.GONE);

    }

    public static TabProductFragment newInstance(Category category) {
        Log.d(TAG, "Sent: "+category.getId()+"-"+category.getName());
        TabProductFragment fragment = new TabProductFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, category);

        fragment.setArguments(args);
        return fragment;
    }

}
