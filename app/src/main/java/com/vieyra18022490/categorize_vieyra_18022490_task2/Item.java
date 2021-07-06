package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.List;

public class Item {

    public String key;
    public String Name;
    public String Date;



    public Item() {

    }

    public Item(String key, String name, int itemNum, Bitmap picture) {
        this.key = key;
        Name = name;
        this.itemNum = itemNum;
        this.picture = picture;
        Date = "";
    }
    public Item(String key, String name, String date) {
        this.key = key;
        Name = name;
        if(date == null)
            Date = "";
        else
            Date = date;
    }
    public Item(String key, String name) {
        this.key = key;
        Name = name;
        Date = "";
    }

    public Item(String key, String name, int itemNum, Bitmap picture, String date) {
        this.key = key;
        Name = name;
        this.itemNum = itemNum;
        this.picture = picture;
        Date = date;
    }

    public int itemNum;
    public Bitmap picture;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getDate() { return Date;  }

    public void setDate(String date) { Date = date; }
}
