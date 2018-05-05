package com.crush.crushappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.crush.crushappclient.adapter.ToppingAdapter;

public class ProductInfoActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    NumberPicker npQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        init();

    }

    private void init() {
        linearLayout = (LinearLayout) findViewById(R.id.horizontalLinear);
        npQuantity = (NumberPicker) findViewById(R.id.numberPickerQuantity);
        npQuantity.setMinValue(1);
        npQuantity.setMaxValue(50);
        ToppingAdapter toppingAdapter = new ToppingAdapter(this,seedData.getListTopping());
        for(int i=0;i<toppingAdapter.getCount();i++){
            linearLayout.addView(toppingAdapter.getView(i,null,null));
        }
    }
}
