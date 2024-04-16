package com.example.q_less_java.Domain;

import java.io.Serializable;

public class RestaurantDomain implements Serializable {

    private String title;
    private String pic;
    private String description;
    private String pickup_in;

    public RestaurantDomain(String title, String pic, String description, String pickup_in) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.pickup_in = pickup_in;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPickup_in() {
        return pickup_in;
    }

    public void setPickup_in(String pickup_in) {
        this.pickup_in = pickup_in;
    }
}
