package com.crush.crushappclient.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.BottomNavigationPageAdapter;
import com.crush.crushappclient.fragment.NotificationFragment;
import com.crush.crushappclient.fragment.ProductFragment;
import com.crush.crushappclient.fragment.ProfileFragment;
import com.crush.crushappclient.viewmodel.MainActivityViewModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.Collections;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements EventListener<DocumentSnapshot> {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 9;
    private static final int RC_SIGN_IN = 9001;
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
    private MainActivityViewModel mViewModel;
    private FirebaseFirestore mFirestore;
    private DocumentReference mUserRef;
    private ListenerRegistration mUserRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mFirestore = FirebaseFirestore.getInstance();

        viewPaper = (ViewPager) findViewById(R.id.navigationViewPaper);
        setupViewPaper(viewPaper);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        FirebaseFirestore.setLoggingEnabled(true);

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
        // Start sign in if necessary
        if (shouldStartSignIn()) {
            startSignIn();
            return;
        } else {
            mUserRef = mFirestore.collection("customers").document(FirebaseAuth.getInstance().getUid());
            mUserRegistration = mUserRef.addSnapshotListener(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mUserRegistration != null) {
            mUserRegistration.remove();
            mUserRegistration = null;
        }
    }

    private void setupViewPaper(ViewPager viewPaper) {
        BottomNavigationPageAdapter adapter = new BottomNavigationPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductFragment());
        adapter.addFragment(new ProfileFragment());
        adapter.addFragment(new NotificationFragment());
        viewPaper.setAdapter(adapter);
    }

    private boolean shouldStartSignIn() {
        return (!mViewModel.getIsSigningIn() && FirebaseAuth.getInstance().getCurrentUser() == null);
    }

    private void startSignIn() {

        // Sign in with FirebaseUI
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Collections.singletonList(
                        new AuthUI.IdpConfig.PhoneBuilder().setDefaultCountryIso("VN").build()))

                .build();

        startActivityForResult(intent, RC_SIGN_IN);
        mViewModel.setIsSigningIn(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                mViewModel.setIsSigningIn(true);
                mUserRef = mFirestore.collection("customers").document(FirebaseAuth.getInstance().getUid());
                mUserRegistration = mUserRef.addSnapshotListener(this);
            } else {
                if (response == null) {
                    // User pressed the back button.
                    finish();
                } else if (response.getError() != null
                        && response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSignInErrorDialog(R.string.message_no_network);
                } else {
                    showSignInErrorDialog(R.string.message_unknown);
                }
            }
        }
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showSignInErrorDialog(@StringRes int message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.title_sign_in_error)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.option_retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startSignIn();
                    }
                })
                .setNegativeButton(R.string.option_exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();

        dialog.show();
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        Log.d(TAG, "onEvent:snapshot "+snapshot);
        if(snapshot.getData() == null){
            Intent updateInfoIntent  = new Intent(this,ProfileManagerActivity.class);
            startActivity(updateInfoIntent);
        }
    }
}
