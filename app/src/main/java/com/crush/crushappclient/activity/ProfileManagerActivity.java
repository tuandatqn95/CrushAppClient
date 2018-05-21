package com.crush.crushappclient.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.crush.crushappclient.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileManagerActivity extends AppCompatActivity {
    Spinner spinnerSex,  spinnerDate;
    EditText edtName,edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);
        init();
        addSpinner();

    }

    private void addSpinner() {
        List<String> list = new ArrayList<>();
        list.add("Chọn giới tính");
        list.add("Nam");
        list.add("Nữ");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapter);
        spinnerDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
        }
        });
    }

    private void init() {
        spinnerSex = (Spinner) findViewById(R.id.spinnerSex);
        spinnerDate = (Spinner) findViewById(R.id.spinnerDate);
        edtName = (EditText) findViewById(R.id.edtname);
        edtEmail = (EditText) findViewById(R.id.edtemail);

    }
}
