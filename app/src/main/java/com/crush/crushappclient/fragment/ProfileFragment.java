package com.crush.crushappclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.activity.MainActivity;
import com.crush.crushappclient.activity.ProfileManagerActivity;
import com.crush.crushappclient.activity.SupportActivity;
import com.crush.crushappclient.adapter.MenuListViewAdapter;
import com.crush.crushappclient.model.MenuListView;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{
    private static final String TAG = "ProfileFragment";

    ListView lvMenuProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        lvMenuProfile = (ListView) rootView.findViewById(R.id.lvMenuProfile);
        List<MenuListView> menuListViewList = new ArrayList<>();
        menuListViewList.add(new MenuListView(R.drawable.icons8_user_50px, "Quản lý tài khoảng"));
        menuListViewList.add(new MenuListView(R.drawable.icons8_order_history_50px, "Lịch sử đơn hàng"));
        menuListViewList.add(new MenuListView(R.drawable.icons8_info_50px, "Giới thiệu"));
        menuListViewList.add(new MenuListView(R.drawable.icons8_settings_50px, "Cài đặt cấu hình"));
        menuListViewList.add(new MenuListView(R.drawable.icons8_whatsapp_50px, "Hỗ trợ"));
        menuListViewList.add(new MenuListView(R.drawable.icons8_exit_50px_1, "Đăng xuất"));
        MenuListViewAdapter adapter = new MenuListViewAdapter(getActivity(), menuListViewList);
        lvMenuProfile.setAdapter(adapter);
        lvMenuProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        updateCustomer();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        support();
                        break;
                    case 5:
                        logout();
                        break;

                }
            }
        });

        return rootView;
    }

    private void support() {
        Intent intent = new Intent(getActivity(), SupportActivity.class);
        startActivity(intent);
    }

    private void logout() {
        AuthUI.getInstance().signOut(getContext()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Intent refresh = new Intent(getContext(), MainActivity.class);
                startActivity(refresh);
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Signout fail!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void updateCustomer() {
        Intent intent = new Intent(getActivity(), ProfileManagerActivity.class);
        startActivity(intent);
    }




}
