package com.crush.crushappclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.MenuProfileAdapter;
import com.crush.crushappclient.adapter.NotificationAdapter;
import com.crush.crushappclient.model.MenuProfile;
import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.seedData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ListView lvMenu;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        lvMenu = (ListView) rootView.findViewById(R.id.lvmenu);
        List<MenuProfile> menuProfileList = seedData.getmenuProfileList();
        MenuProfileAdapter adapter = new MenuProfileAdapter(getActivity(),menuProfileList);
        lvMenu.setAdapter(adapter);

        return rootView;
    }

}
