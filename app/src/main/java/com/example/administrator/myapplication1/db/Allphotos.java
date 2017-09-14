package com.example.administrator.myapplication1.db;

/**
 * Created by Liu on 2017/7/10 0010.
 */

public class Allphotos {

    private String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }



    @Override
    public String toString() {
        return "Allphotos{" +
                "photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
