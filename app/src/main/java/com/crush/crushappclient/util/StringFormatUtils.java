package com.crush.crushappclient.util;

import android.text.format.DateFormat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StringFormatUtils {

    public static String FormatCurrency(long money){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(money) + " VNĐ";
    }

    public static String getFormatCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        return date;
    }

    public static String FormatToDate(Date dateIn){
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateIn);
        String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
        return  formatedDate;
    }


}
