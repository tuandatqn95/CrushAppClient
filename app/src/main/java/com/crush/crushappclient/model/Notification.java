package com.crush.crushappclient.model;

public class Notification {
    private int id;
    private int customerId;
    private String imageURL;
    private String tittle;
    private String content;

    public Notification(int id, int customerId, String imageURL, String tittle, String content) {
        this.id = id;
        this.customerId = customerId;
        this.imageURL = imageURL;
        this.tittle = tittle;
        this.content = content;
    }

    public Notification(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
