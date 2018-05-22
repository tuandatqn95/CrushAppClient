package com.crush.crushappclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.RecyclerViewHolder>{
    private Context context;
    private List<Notification> notificationList;
    private OnItemClickedListener onItemClickedListener;

    public NotificationAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.notification_item_layout, null);
        return new RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder,final int position) {
        Notification notification = notificationList.get(position);
        holder.imgv.setImageResource(R.drawable.crush_logo);
        holder.txtvTittle.setText(notification.getTittle());
        holder.txtvContent.setText(notification.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(notificationList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imgv;
        TextView txtvTittle ;
        TextView txtvContent;

        private RecyclerViewHolder(final View itemView) {
            super(itemView);
            imgv = (ImageView) itemView.findViewById(R.id.imageView);
            txtvTittle = (TextView) itemView.findViewById(R.id.txtvTittle);
            txtvContent = (TextView) itemView.findViewById(R.id.txtvContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, itemView.getId()+"", Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
    }
    public interface OnItemClickedListener {
        void onItemClick(Notification notification);
    }
    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
