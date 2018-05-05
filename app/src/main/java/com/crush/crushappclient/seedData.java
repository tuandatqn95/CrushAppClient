package com.crush.crushappclient;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.ImageView;

import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.Topping;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class seedData {

    public static List<Category> getCategories(){
        List<Category> list = new ArrayList<>();

        list.add(new Category(1,"MILK TEA"));
        list.add(new Category(1,"FRESH FRUIT TEA"));
        list.add(new Category(1,"HANDMADE"));
        list.add(new Category(1,"MACCHIATO"));
        list.add(new Category(1,"SPECIAL DRINK"));
        list.add(new Category(1,"OTHERS"));
        return list;
    }

    public static List<MainDrink> getMainDrink(){
        List<MainDrink> list = new ArrayList<>();

        list = new ArrayList<>();

        list.add(new MainDrink(1,"Trà sữa truyền thống",20000,R.drawable.trasua,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua1,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua2,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua3,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua4,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua5,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua6,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua7,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua8,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua9,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua10,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua11,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua12,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,R.drawable.trasua13,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,R.drawable.trasua11,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,R.drawable.trasua7,1));


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
    private byte [] imageView_to_Byte(ImageView imageView){
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}
