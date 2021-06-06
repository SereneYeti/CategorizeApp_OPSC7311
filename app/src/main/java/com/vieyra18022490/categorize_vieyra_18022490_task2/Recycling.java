package com.vieyra18022490.categorize_vieyra_18022490_task2;

public class Recycling {

    private String id;
    private String name;
    private int image;
    private String goalAmount;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(String goalAmount) {
        this.goalAmount = goalAmount;
    }

    public Recycling(String id, String name, int image, String goalAmount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.goalAmount = goalAmount;
    }
}
