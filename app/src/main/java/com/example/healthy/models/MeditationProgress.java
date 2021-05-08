package com.example.healthy.models;

import java.util.Date;

public class MeditationProgress {
    private String type;
    private Date date;
    private int duration;

    public MeditationProgress(String type, Date date, int duration) {
        this.type = type;
        this.date = date;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
