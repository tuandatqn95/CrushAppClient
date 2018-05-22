package com.crush.crushappclient.Interface;

import android.icu.text.UnicodeSetSpanner;

import com.crush.crushappclient.model.Topping;

public interface OnToppingClickedListener {
    void OnSelected(Topping topping);
    void OnDeselected(Topping topping);
}
