package com.example.datapresistanse;

import android.media.audiofx.AcousticEchoCanceler;

public class User {
    private int id;
    private String name;
    private String email;
    private String country;

    private String phone;

    private String Password;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String age) {
        this.country = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(String name, String email, String country, String phone, String password) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.phone = phone;
        Password = password;
    }

    public User(int id, String name, String email, String country, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.phone = phone;
        Password = password;
    }


}
