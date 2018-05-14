package com.crush.crushappclient;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.crush.crushappclient.model.Category;
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

//        list.add(new MainDrink(1,"Trà sữa truyền thống",20000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua,1));
//        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua,1));

        return list;
    }
    public static List<Topping> getListTopping (){
        List<Topping> listTopping = new ArrayList<>();
        listTopping.add(new Topping(11,"pudding",10000,null));
        listTopping.add(new Topping(22,"pudding",12000,null));
        listTopping.add(new Topping(33,"pudding",13000,null));
        listTopping.add(new Topping(44,"pudding",14000,null));
        listTopping.add(new Topping(55,"pudding",15000,null));
        listTopping.add(new Topping(66,"pudding",16000,null));

        return listTopping;
    }
    public static List<Notification> getNotificationList(){
        List<Notification> listNotification = new ArrayList<>();

        listNotification.add(new Notification(1,1,"Khuyến mãi 1","blalalalalalalal"));
        listNotification.add(new Notification(2,1,"Khuyến mãi 1","blalalalalalalal"));
        listNotification.add(new Notification(3,1,"Khuyến mãi 1","blalalalalalalal"));
        listNotification.add(new Notification(4,1,"Khuyến mãi 1","blalalalalalalal"));
        listNotification.add(new Notification(5,1,"Khuyến mãi 1","blalalalalalalal"));

        return listNotification;

    }
    public static List<MenuProfile> getmenuProfileList(){
        List<MenuProfile> menuProfileList = new ArrayList<>();

        menuProfileList.add(new MenuProfile(1,"Quản lý tài khoảng"));
        menuProfileList.add(new MenuProfile(1,"Lịch sử đơn hàng"));
        menuProfileList.add(new MenuProfile(1,"Giới thiệu"));
        menuProfileList.add(new MenuProfile(1,"Cài đặt cấu hình"));
        menuProfileList.add(new MenuProfile(1,"Hỗ trợ"));

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
