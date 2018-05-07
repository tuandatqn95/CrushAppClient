package com.crush.crushappclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.R;
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
    static class ViewHolder{
        ImageView imgvIcon;
        TextView txtvContent;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        View item = view;
        if (view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.menu_profile_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imgvIcon = (ImageView) item.findViewById(R.id.imgvIcon);
            viewHolder.txtvContent = (TextView) item.findViewById(R.id.txtvContent1);
            item.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) item.getTag();
        }

        // gán giá trị
        MenuProfile menuProfile = menuProfileList.get(i);
        viewHolder.txtvContent.setText(menuProfile.getContent());
        viewHolder.imgvIcon.setImageResource(R.drawable.ic_home_black_24dp);

        return item;
    }
}
