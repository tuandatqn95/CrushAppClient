package com.crush.crushappclient.ClassSupport;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.MenuProfile;
import com.crush.crushappclient.model.Notification;
import com.crush.crushappclient.model.Topping;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class seedData {



    public static List<MainDrink> getMainDrink(){
        List<MainDrink> list = new ArrayList<>();

        return list;
    }
    public static List<Topping> getListTopping (){
        List<Topping> listTopping = new ArrayList<>();



        return listTopping;
    }
    public static List<Notification> getNotificationList(){
        List<Notification> listNotification = new ArrayList<>();

        listNotification.add(new Notification(1,1,"Khuyến mãi 1","blalalalalalalal"));
        listNotification.add(new Notification(2,1,"Khuyến mãi 2","blalalalalalalalblalalalalalalalblalalalalalalalblalalalalalalalblalalalalalalal"));
        listNotification.add(new Notification(3,1,"Khuyến mãi 3","blalalalalalalal"));
        listNotification.add(new Notification(4,1,"Khuyến mãi 4","blalalalalalalal"));
        listNotification.add(new Notification(5,1,"Khuyến mãi 5","blalalalalalalal"));
        listNotification.add(new Notification(6,1,"Khuyến mãi 6","blalalalalalalal"));
        listNotification.add(new Notification(7,1,"Khuyến mãi 7","blalalalalalalal"));
        listNotification.add(new Notification(8,1,"Khuyến mãi 8","blalalalalalalal"));
        listNotification.add(new Notification(9,1,"Khuyến mãi 9","blalalalalalalal"));

        return listNotification;

    }
    public static List<MenuProfile> getmenuProfileList(){
        List<MenuProfile> menuProfileList = new ArrayList<>();

        menuProfileList.add(new MenuProfile(R.drawable.icons8_user_50px,"Quản lý tài khoản"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_order_history_50px,"Lịch sử đơn hàng"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_info_50px,"Giới thiệu"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_settings_50px,"Cài đặt cấu hình"));
        menuProfileList.add(new MenuProfile(R.drawable.icons8_whatsapp_50px,"Hỗ trợ"));

        return menuProfileList;

    }
    private byte [] imageView_to_Byte(ImageView imageView){
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}