package com.crush.crushappclient.model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String sex;
    private String password;

    public Customer(int id, String name, String email, String phone, String sex, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
