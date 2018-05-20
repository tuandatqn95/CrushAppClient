package com.crush.crushappclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.NotificationAdapter;
import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.seedData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNotification);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Notification> notificationList = seedData.getNotificationList();
        NotificationAdapter adapter = new NotificationAdapter(getActivity(),notificationList);
        adapter.setOnItemClickedListener(new NotificationAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(Notification notification) {
                Toast.makeText(getActivity(),notification.getId()+"", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);


        return rootView;

    }

}
