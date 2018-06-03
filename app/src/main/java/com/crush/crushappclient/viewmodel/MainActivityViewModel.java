package com.crush.crushappclient.viewmodel;

import android.arch.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private boolean mIsSigningIn;


    public MainActivityViewModel() {
        mIsSigningIn = false;
    }

    public boolean getIsSigningIn() {
        return mIsSigningIn;
    }

    public void setIsSigningIn(boolean mIsSigningIn) {
        this.mIsSigningIn = mIsSigningIn;
    }

}
