package com.crush.crushappclient.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class ProfileManagerActivity extends AppCompatActivity {

    @BindView(R.id.spinnerSex)
    Spinner spinnerSex;

    @BindView(R.id.edtname)
    EditText edtName;

    @BindView(R.id.edtemail)
    EditText edtEmail;

    @BindView(R.id.txtvdate)
    TextView txtvDate;

    @BindView(R.id.btnupdate)
    Button btnUpdate;

    Calendar cal;
    Date dateFinish;
    Date hourFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        addSpinner();
        addEvent();
    }

    private void addEvent() {
        txtvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void addSpinner() {
        List<String> list = new ArrayList<>();
        list.add("Không xác định");
        list.add("Nam");
        list.add("Nữ");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapter);

    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                txtvDate.setText((dayOfMonth>9?dayOfMonth:"0"+dayOfMonth) +"/"+(monthOfYear>8?monthOfYear+1:"0"+(monthOfYear+1))+"/"+year);
            }
        };
        String s=txtvDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                ProfileManagerActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày tháng năm sinh");
        pic.show();
    }
}
