package com.crush.crushappclient.model;

public class MenuListView {
    private int icon;
    private String content;

    public MenuListView(int icon, String content) {
        this.icon = icon;
        this.content = content;
    }

    public MenuListView(){}

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
