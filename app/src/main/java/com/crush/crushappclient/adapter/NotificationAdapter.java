package com.crush.crushappclient.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crush.crushappclient.R;
import com.crush.crushappclient.model.Notification;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends FirestoreAdapter<NotificationAdapter.ViewHolder>{

    public interface OnNotificationSelectedListener {

        void OnNotificationSelected(DocumentSnapshot snapshot);
    }

    public NotificationAdapter(Query query) {
        super(query);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NotificationAdapter.ViewHolder(inflater.inflate(R.layout.notification_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notification_image)
        ImageView notificationImage;

        @BindView(R.id.notification_tittle)
        TextView notificationTittle;

        @BindView(R.id.notification_content)
        TextView notificationContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(DocumentSnapshot snapshot) {
            Notification notification = snapshot.toObject(Notification.class);
            Glide.with(itemView.getContext()).load(notification.getImageURL()).into(notificationImage);
            notificationTittle.setText(notification.getTittle());
            notificationContent.setText(notification.getContent());
        }
    }
}
