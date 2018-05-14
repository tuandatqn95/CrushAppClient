package com.crush.crushappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.adapter.ToppingAdapter;
import com.crush.crushappclient.model.MainDrink;

public class ProductInfoActivity extends AppCompatActivity {
    TextView txtvName,txtvPrice;
    ImageView imgvDrink;
    LinearLayout linearLayout;
    NumberPicker npQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        getMainDrink();

    }

    private void getMainDrink() {
        Intent intent = getIntent();
        MainDrink mainDrink = (MainDrink) intent.getSerializableExtra("mainDrink");
        txtvName.setText(mainDrink.getName());
        txtvPrice.setText(mainDrink.getPrice()+"");
        //imgvDrink.setImageResource(Integer.valueOf(mainDrink.getImage()));

    }

    private void init() {
        txtvName = (TextView) findViewById(R.id.txtvdrinkName);
        txtvPrice = (TextView) findViewById(R.id.txtvdrinkPrice);
        imgvDrink = (ImageView) findViewById(R.id.imageViewDrink);
        linearLayout = (LinearLayout) findViewById(R.id.horizontalLinear);
        npQuantity = (NumberPicker) findViewById(R.id.numberPickerQuantity);
        npQuantity.setMinValue(1);
        npQuantity.setMaxValue(50);
        final ToppingAdapter toppingAdapter = new ToppingAdapter(this,seedData.getListTopping());
        for(int i=0;i<toppingAdapter.getCount();i++){
            final View viewItem = toppingAdapter.getView(i,null,null);
            linearLayout.addView(viewItem);
            viewItem.setId((int) toppingAdapter.getItemId(i));
            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ProductInfoActivity.this, + viewItem.getId()+"", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
