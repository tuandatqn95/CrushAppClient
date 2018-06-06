package com.crush.crushappclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.MenuListView;

import java.util.List;

public class MenuListViewAdapter extends BaseAdapter {
    private Context context;
    private List<MenuListView> menuListViewList;

    public MenuListViewAdapter(Context context, List<MenuListView> menuListViewList) {
        this.context = context;
        this.menuListViewList = menuListViewList;
    }

    @Override
    public int getCount() {
        return menuListViewList.size();
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
        MenuListView menuListView = menuListViewList.get(i);
        viewHolder.imgvIcon.setImageResource(menuListView.getIcon());
        viewHolder.txtvContent.setText(menuListView.getContent());

        return view;
    }

    public static class ViewHolder{
        ImageView imgvIcon;
        TextView txtvContent;

    }


}
