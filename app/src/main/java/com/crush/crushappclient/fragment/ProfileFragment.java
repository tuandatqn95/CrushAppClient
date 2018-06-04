package com.crush.crushappclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.activity.ProfileManagerActivity;
import com.crush.crushappclient.adapter.MenuProfileAdapter;
import com.crush.crushappclient.model.MenuProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ListView lvMenu;
    RecyclerView recyclerViewMenuProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        lvMenu = (ListView) rootView.findViewById(R.id.lvMenuProfile);

        List<MenuProfile> menuProfileList = new ArrayList<>();
        menuProfileList.add(new MenuProfile(R.drawable.icons8_user_50px,"Quản lý tài khoảng"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_order_history_50px,"Lịch sử đơn hàng"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_info_50px,"Giới thiệu"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_settings_50px,"Cài đặt cấu hình"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_whatsapp_50px,"Hỗ trợ"));
        MenuProfileAdapter adapter = new MenuProfileAdapter(getActivity(),menuProfileList);
        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        sendCustomer();
                        break;
                    case 1:

                }
            }
        });

        return rootView;
    }
    private void sendCustomer(){
        Intent intent = new Intent(getActivity(), ProfileManagerActivity.class);
        startActivity(intent);
    }
}
