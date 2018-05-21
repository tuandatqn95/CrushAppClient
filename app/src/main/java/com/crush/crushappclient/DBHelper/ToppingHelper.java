package com.crush.crushappclient.DBHelper;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.Topping;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ToppingHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Topping> toppingList;
    private static ToppingHelper instance;

    private ToppingHelper(){
        toppingList = new ArrayList<>();
    }

    public static synchronized ToppingHelper getInstance(){

        if(instance==null){
            instance = new ToppingHelper();
        }

        return instance;
    }

    public void update(){

        toppingList.clear();
        db.collection("toppings").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    for (final QueryDocumentSnapshot document : task.getResult()) {
                        Topping topping = document.toObject(Topping.class);
                        topping.setId(document.getId());
                        toppingList.add(topping);
                    }
                }
            }
        });
    }

    public List<Topping> gets(){

        return toppingList;
    }


}
