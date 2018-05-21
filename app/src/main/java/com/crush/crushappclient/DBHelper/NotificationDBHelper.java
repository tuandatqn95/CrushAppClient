package com.crush.crushappclient.DBHelper;

import android.support.annotation.NonNull;

import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.model.Topping;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotificationDBHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Notification> notificationList;
    private static NotificationDBHelper instance;

    private NotificationDBHelper(){
        notificationList = new ArrayList<>();
    }

    public static synchronized NotificationDBHelper getInstance(){
        if(instance==null){
            instance = new NotificationDBHelper();
        }
        return instance;
    }
    public void update(){
        notificationList.clear();
        db.collection("notifications").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    for (final QueryDocumentSnapshot document : task.getResult()) {
                        Notification notification = document.toObject(Notification.class);
                        //notification.setId(document.getId());
                        notificationList.add(notification);
                    }
                }
            }
        });
    }

    public List<Notification> gets(){
        return notificationList;
    }
}
