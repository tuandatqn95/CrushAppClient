package com.crush.crushappclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.crush.crushappclient.DBHelper.CategoryDBHelper;
import com.crush.crushappclient.DBHelper.MainDrinkDBHelper;
import com.crush.crushappclient.DBHelper.NotificationDBHelper;
import com.crush.crushappclient.DBHelper.ToppingHelper;
import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.BottomNavigationPageAdapter;
import com.crush.crushappclient.fragment.NotificationFragment;
import com.crush.crushappclient.fragment.ProductFragment;
import com.crush.crushappclient.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPaper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPaper.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPaper.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPaper.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPaper = (ViewPager) findViewById(R.id.navigationViewPaper);

        setupViewPaper(viewPaper);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        viewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                        break;
                }
                viewPaper.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CategoryDBHelper.getInstance().update();
        ToppingHelper.getInstance().update();
        MainDrinkDBHelper.getInstance().update();
        NotificationDBHelper.getInstance().update();
    }

    private void setupViewPaper(ViewPager viewPaper) {
        BottomNavigationPageAdapter adapter = new BottomNavigationPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductFragment());
        adapter.addFragment(new ProfileFragment());
        adapter.addFragment(new NotificationFragment());
        viewPaper.setAdapter(adapter);
    }


}
