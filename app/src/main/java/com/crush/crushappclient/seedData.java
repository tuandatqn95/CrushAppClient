package com.crush.crushappclient;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.crush.crushappclient.model.Category;
import com.crush.crushappclient.model.MainDrink;

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
        list.add(new MainDrink(1,"Trà sữa truyền thống",20000,null,1));

        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,null,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,null,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,null,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,null,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,null,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,null,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,null,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,null,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,null,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,null,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,null,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,null,1));
        list.add(new MainDrink(1,"Trà sữa thái xanh",21000,null,1));
        list.add(new MainDrink(1,"Trà sữa dâu tây",22000,null,1));
        list.add(new MainDrink(1,"Trà sữa socola",23000,null,1));


        return list;
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
