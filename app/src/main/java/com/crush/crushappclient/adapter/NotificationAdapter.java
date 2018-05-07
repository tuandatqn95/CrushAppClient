package com.crush.crushappclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.Notification;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {
    private Context context;
    private List<Notification> notificationList;

    public NotificationAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int i) {
        return notificationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    static class ViewHolder{
        ImageView imgv;
        TextView txtvTittle ;
        TextView txtvContent;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        View item = view;
        if (view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.notification_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imgv = (ImageView) item.findViewById(R.id.imageView);
            viewHolder.txtvTittle = (TextView) item.findViewById(R.id.txtvTittle);
            viewHolder.txtvContent = (TextView) item.findViewById(R.id.txtvContent);
            item.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) item.getTag();
        }

        // gán giá trị
        Notification notification = notificationList.get(i);
        viewHolder.txtvTittle.setText(notification.getTittle());
        viewHolder.txtvContent.setText(notification.getContent());
        viewHolder.imgv.setImageResource(R.drawable.trasua);

        return item;
    }
}
