package com.crush.crushappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ProfileManagerActivity extends AppCompatActivity {
    Spinner spinnerSex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);
        addSpinner();
    }

    private void addSpinner() {

        spinnerSex = (Spinner) findViewById(R.id.spinnerSex);
        List<String> list = new ArrayList<>();
        list.add("Chọn giới tính");
        list.add("Nam");
        list.add("Nữ");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapter);
    }
}
