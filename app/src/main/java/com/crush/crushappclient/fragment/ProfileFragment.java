package com.crush.crushappclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.activity.MainActivity;
import com.crush.crushappclient.activity.ProfileManagerActivity;
import com.crush.crushappclient.adapter.MenuProfileAdapter;
import com.crush.crushappclient.model.Customer;
import com.crush.crushappclient.model.MenuProfile;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements EventListener<DocumentSnapshot> {
    private static final String TAG = "ProfileFragment";

    ListView lvMenu;
    RecyclerView recyclerViewMenuProfile;
    private FirebaseFirestore mFirestore;
    DocumentReference mUserRef;
    ListenerRegistration mUserRegistration;
    private DocumentSnapshot mSnapshot;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        lvMenu = (ListView) rootView.findViewById(R.id.lvMenuProfile);

        mFirestore = FirebaseFirestore.getInstance();
        mUserRef = mFirestore.collection("customers").document(FirebaseAuth.getInstance().getUid());


        List<MenuProfile> menuProfileList = new ArrayList<>();
        menuProfileList.add(new MenuProfile(R.drawable.icons8_user_50px, "Quản lý tài khoảng"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_order_history_50px, "Lịch sử đơn hàng"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_info_50px, "Giới thiệu"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_settings_50px, "Cài đặt cấu hình"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_whatsapp_50px, "Hỗ trợ"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_whatsapp_50px, "Đăng xuất"));
        MenuProfileAdapter adapter = new MenuProfileAdapter(getActivity(), menuProfileList);
        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        updateCustomer();
                        break;
                    case 5:
                        logout();
                        break;

                }
            }
        });

        return rootView;
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
    public void onStart() {
        super.onStart();
        mUserRegistration = mUserRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mUserRegistration != null) {
            mUserRegistration.remove();
            mUserRegistration = null;
        }
    }

    private void updateCustomer() {
        Intent intent = new Intent(getActivity(), ProfileManagerActivity.class);
        intent.putExtra(ProfileManagerActivity.USER_INFO, (Serializable) mSnapshot.toObject(Customer.class));
        startActivity(intent);
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        this.mSnapshot = snapshot;
        onUserLoaded(snapshot.toObject(Customer.class));
    }

    private void onUserLoaded(Customer customer) {

        Log.d(TAG, "onUserLoaded: Customer" + customer);
        // TODO: 6/4/2018 --  Loaded User, get info into view
    }


}
