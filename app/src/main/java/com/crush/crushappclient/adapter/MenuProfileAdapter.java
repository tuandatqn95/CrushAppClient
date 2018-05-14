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

public class MenuProfileAdapter extends RecyclerView.Adapter<MenuProfileAdapter.MyViewHolder> {
    private Context context;
    private List<MenuProfile> menuProfileList;

    public MenuProfileAdapter(Context context, List<MenuProfile> menuProfileList) {
        this.context = context;
        this.menuProfileList = menuProfileList;
    }
    @Override
    public MenuProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.menu_profile_item_layout, null);
        return new MenuProfileAdapter.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MenuProfileAdapter.MyViewHolder holder, int position) {
        MenuProfile menuProfile = menuProfileList.get(position);
        holder.txtvContent.setText(menuProfile.getContent());
        holder.imgvIcon.setImageResource(R.drawable.ic_home_black_24dp);
    }

    @Override
    public int getItemCount() {
        return menuProfileList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgvIcon;
        TextView txtvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgvIcon = (ImageView) itemView.findViewById(R.id.imgvIcon);
            txtvContent = (TextView) itemView.findViewById(R.id.txtvContent1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
}
