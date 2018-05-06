package com.crush.crushappclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.crush.crushappclient.ProductInfoActivity;
import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.MainDrinkAdapter;
import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.seedData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class TabProductFragment extends Fragment {

    private static final String TAG = TabProductFragment.class.getSimpleName();

    private static final String ARG_PARAM = "CATEGORY";

    private GridView gridView;
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
        gridView = (GridView) rootView.findViewById(R.id.gridViewProduct);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), ProductInfoActivity.class));
            }
        });

        Log.d(TAG, category.getName());
        db.collection("categories").document(category.getId()).collection("maindrinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        mainDrinkList.add(document.toObject(MainDrink.class));
                    }
                    setupGridView(gridView, mainDrinkList);
                }
            }
        });


        return rootView;
    }

    private void setupGridView(GridView gridView, List<MainDrink> categories) {
        MainDrinkAdapter adapter = new MainDrinkAdapter(getActivity(), mainDrinkList);
        gridView.setAdapter(adapter);
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
