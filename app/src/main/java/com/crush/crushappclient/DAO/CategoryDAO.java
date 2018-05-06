package com.crush.crushappclient.DAO;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crush.crushappclient.model.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO  {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Category> listCategory = new ArrayList<>();



}
