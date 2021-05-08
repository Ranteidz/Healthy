package com.example.healthy.models;

import java.util.Date;

public class WaterProgress {
    private String type;
    private Date date;
    private int waterIntake;

    public WaterProgress(String type, Date date, int waterIntake) {
        this.type = type;
        this.date = date;
        this.waterIntake = waterIntake;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(int waterIntake) {
        this.waterIntake = waterIntake;
    }
}
