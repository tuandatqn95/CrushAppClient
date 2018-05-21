package com.crush.crushappclient.DBHelper;

import android.support.annotation.NonNull;

import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.Topping;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryDBHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Category> categoryList;
    private static CategoryDBHelper instance;

    private CategoryDBHelper(){
        categoryList = new ArrayList<>();
    }
    public static synchronized CategoryDBHelper getInstance(){
        if(instance==null){
            instance = new CategoryDBHelper();
        }

        return instance;
    }
    public void update(){
        categoryList.clear();
        db.collection("categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    for (final QueryDocumentSnapshot document : task.getResult()) {
                        Category category = document.toObject(Category.class);
                        category.setId(document.getId());
                        categoryList.add(category);
                    }
                }
            }
        });
    }
    public List<Category> gets(){
        return categoryList;
    }
}
