package com.crush.crushappclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.NotificationAdapter;
import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.seedData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private ListView listView;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        listView = (ListView) rootView.findViewById(R.id.lvNotification);
        List<Notification> notificationList = seedData.getNotificationList();
        NotificationAdapter adapter = new NotificationAdapter(getActivity(),notificationList);
        listView.setAdapter(adapter);


        return rootView;

    }

}
