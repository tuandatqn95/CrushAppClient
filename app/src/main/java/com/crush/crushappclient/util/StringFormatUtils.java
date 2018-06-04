package com.crush.crushappclient.util;

import android.text.format.DateFormat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringFormatUtils {

    public static String FormatCurrency(long money){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(money) + " VNĐ";
    }

    public static String getFormatCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());
        return date;
    }


}
