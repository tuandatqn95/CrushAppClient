package com.crush.crushappclient.DBHelper;

import android.support.annotation.NonNull;

import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.Topping;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainDrinkDBHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<MainDrink> mainDrinkList;
    private static MainDrinkDBHelper instance;

    private MainDrinkDBHelper(){
        mainDrinkList = new ArrayList<>();

    }
    public static synchronized MainDrinkDBHelper getInstance(){

        if(instance==null){
            instance = new MainDrinkDBHelper();
        }

        return instance;
    }
    public void update(){
        mainDrinkList.clear();
        db.collection("maindrinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    for (final QueryDocumentSnapshot document : task.getResult()) {
                        MainDrink mainDrink = document.toObject(MainDrink.class);
                        //mainDrink.setCategoryId(document.getId());
                        mainDrinkList.add(mainDrink);
                    }
                }
            }
        });
    }
    public List<MainDrink> gets() {
        return mainDrinkList;
    }
}
