package com.example.q_less_java;

public class Food {
//    Attributes

    private String foodName;
    private String foodDesc;
    private int foodImage;

//    Constructor


    public Food(String foodName, String foodDesc, int foodImage) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodImage = foodImage;
    }

//    Getters and Setters

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

}
