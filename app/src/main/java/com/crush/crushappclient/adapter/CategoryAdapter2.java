package com.crush.crushappclient.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.Query;

public class CategoryAdapter2 extends FirestoreAdapter<CategoryAdapter2.ViewHolder> {



    public CategoryAdapter2(Query query) {
        super(query);
    }

    @NonNull
    @Override
    public CategoryAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter2.ViewHolder holder, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
