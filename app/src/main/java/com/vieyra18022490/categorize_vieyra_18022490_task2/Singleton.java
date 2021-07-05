package com.vieyra18022490.categorize_vieyra_18022490_task2;

import java.util.ArrayList;
import java.util.Hashtable;

public class Singleton {
    private static Singleton INSTANCE = null;

    // other instance variables can be here

    public ArrayList<String> categoryNames = new ArrayList<String>();
    public ArrayList<Integer> Goals = new ArrayList<>();
    public Hashtable<String,ArrayList<Item>> Catagories = new Hashtable<>();
    StringBuilder stringBuilder = new StringBuilder();

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

    public ArrayList<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(ArrayList<String> categoryNames) { this.categoryNames = categoryNames;  }


    public ArrayList<Integer> getGoals() {
        return Goals;
    }

    public void setGoals(ArrayList<Integer> goals) {
        Goals = goals;
    }

    public Hashtable<String, ArrayList<Item>> getCatagories() {
        return Catagories;
    }

    public void setCatagories(Hashtable<String, ArrayList<Item>> catagories) { Catagories = catagories;  }
}


