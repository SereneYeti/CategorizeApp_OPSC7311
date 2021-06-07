package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.widget.ImageView;

import java.util.List;

public class Item {

    public String key;
    public String Name;

    public Item(String key, String name, int itemNum, ImageView picture) {
        this.key = key;
        Name = name;
        this.itemNum = itemNum;
        this.picture = picture;
    }

    public int itemNum;
    public ImageView picture;

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

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }
}
