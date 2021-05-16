package com.example.healthy.models;

import java.util.Date;

public class WaterProgress {
   private String date;
   private int ammount;

    public WaterProgress(String date, int ammount) {
        this.date = date;
        this.ammount = ammount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
}
