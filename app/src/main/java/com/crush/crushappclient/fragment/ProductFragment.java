package com.crush.crushappclient.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.TabViewPaperAdapter;
import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.MainDrink;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifTextView;


public class ProductFragment extends Fragment {


    private final String TAG = ProductFragment.class.getSimpleName();
    private GifTextView gifTextViewLoading;
    private TabLayout tabLayout;
    private ViewPager viewPaper;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        gifTextViewLoading = (GifTextView) rootView.findViewById(R.id.loadingGif);
        gifTextViewLoading.setVisibility(View.VISIBLE);
        viewPaper = (ViewPager) rootView.findViewById(R.id.viewPaper);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPaper);

        db.collection("categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    List<Category> categoryList = new ArrayList<>();
                    for (final QueryDocumentSnapshot document : task.getResult()) {
                         Category category = new Category(document.getId(),document.getString("name"));
                        Log.d(TAG, "Get: "+category.getId()+"-"+category.getName());
                        categoryList.add(category);
                    }
                    Log.d(TAG, "Size: " + categoryList.size());
                    setupViewPaper(viewPaper, categoryList);
                }
            }
        });


        return rootView;
    }

    private void setupViewPaper(ViewPager viewPaper, List<Category> categories) {
        TabViewPaperAdapter adapter = new TabViewPaperAdapter(getActivity().getSupportFragmentManager());
        for (Category category : categories) {
            Log.d(TAG, "NewIns: "+category.getId()+"-"+category.getName());
            adapter.addFragment(TabProductFragment.newInstance(category), category.getName());
        }
        viewPaper.setAdapter(adapter);
        gifTextViewLoading.setVisibility(View.GONE);
    }


}
