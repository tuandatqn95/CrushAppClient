package com.crush.crushappclient.model;

public class Notification {
    private int id;
    private int customerId;
    private String tittle;
    private String content;

    public Notification(int id, int customerId, String tittle, String content) {
        this.id = id;
        this.customerId = customerId;
        this.tittle = tittle;
        this.content = content;
    }

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
}
