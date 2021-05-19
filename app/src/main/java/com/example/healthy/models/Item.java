package com.example.healthy.models;

public class Item {

    private String activityType;
    private String date;
    private String information;

    public Item(String activityType, String date, String information) {
        this.activityType = activityType;
        this.date = date;
        this.information = information;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
