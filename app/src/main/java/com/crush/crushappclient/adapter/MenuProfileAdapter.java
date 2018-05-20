package com.crush.crushappclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.MenuProfile;
import com.crush.crushappclient.model.Notification;

import java.util.List;

public class MenuProfileAdapter extends BaseAdapter {
    private Context context;
    private List<MenuProfile> menuProfileList;

    public MenuProfileAdapter(Context context, List<MenuProfile> menuProfileList) {
        this.context = context;
        this.menuProfileList = menuProfileList;
    }

    @Override
    public int getCount() {
        return menuProfileList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.menu_profile_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.imgvIcon = (ImageView) view.findViewById(R.id.imgvIcon);
            viewHolder.txtvContent = (TextView) view.findViewById(R.id.txtvContent1);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        MenuProfile menuProfile = menuProfileList.get(i);
        viewHolder.imgvIcon.setImageResource(menuProfile.getIcon());
        viewHolder.txtvContent.setText(menuProfile.getContent());

        return view;
    }

    public static class ViewHolder{
        ImageView imgvIcon;
        TextView txtvContent;

    }


}
