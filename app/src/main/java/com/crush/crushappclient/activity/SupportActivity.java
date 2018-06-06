package com.crush.crushappclient.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.MenuListViewAdapter;
import com.crush.crushappclient.model.MenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupportActivity extends AppCompatActivity {

    @BindView(R.id.lvMenuSupport)
    ListView lvMenuSupport;

    @BindView(R.id.support_activity_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        ButterKnife.bind(SupportActivity.this);
        addToolBar();
        List<MenuListView> menuListViewList = new ArrayList<>();
        menuListViewList.add(new MenuListView(R.drawable.icons8_user_50px, "Facebook"));
        menuListViewList.add(new MenuListView(R.drawable.icons8_whatsapp_50px, "0906903693"));
        MenuListViewAdapter adapter = new MenuListViewAdapter(SupportActivity.this, menuListViewList);
        lvMenuSupport.setAdapter(adapter);


    }

    @SuppressLint("ResourceAsColor")
    private void addToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //toolbar.setLogo(R.drawable.crush_logo_yellow);
        toolbar.setTitle("Crush Milk Tea");
        toolbar.setTitleTextColor(R.color.colorMainYellow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 16908332:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
