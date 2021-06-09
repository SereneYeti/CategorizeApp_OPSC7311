package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.graphics.Bitmap;

public class Recycling {

    private String id;
    private String name;
    private Bitmap image;
    private String goalAmount;

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }

    private String DateCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(String goalAmount) {
        this.goalAmount = goalAmount;
    }

    public Recycling(String id, String name, Bitmap image, String goalAmount, String dateCreated) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.goalAmount = goalAmount;
        this.DateCreated = dateCreated;
    }
}
