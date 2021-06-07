package com.vieyra18022490.categorize_vieyra_18022490_task2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Singleton {
    private static Singleton INSTANCE = null;

    // other instance variables can be here

    public ArrayList<String> Categories = new ArrayList<String>();
    public ArrayList<Integer> Goals = new ArrayList<>();
    public Hashtable<String,Item> CatagoryItems = new Hashtable<>();

    private Singleton() {};

    public static synchronized Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return(INSTANCE);
    }

    public String testVar2;

    public String getTestVar2() {
        return testVar2;
    }

    public void setTestVar2(String testVar2) {
        this.testVar2 = testVar2;
    }

    public ArrayList<String> getCategories() {
        return Categories;
    }

    public void setCategories(ArrayList<String> categories) {
        Categories = categories;
    }


    public ArrayList<Integer> getGoals() {
        return Goals;
    }

    public void setGoals(ArrayList<Integer> goals) {
        Goals = goals;
    }

    public Hashtable<String, Item> getCatagoryItems() {
        return CatagoryItems;
    }

    public void setCatagoryItems(Hashtable<String, Item> catagoryItems) {
        CatagoryItems = catagoryItems;
    }
}


