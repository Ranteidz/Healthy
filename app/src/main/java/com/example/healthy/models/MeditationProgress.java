package com.example.healthy.models;

import java.util.Date;

public class MeditationProgress {
    private String date;
    private long duration;

    public MeditationProgress() {
    }

    public MeditationProgress(String date, long duration) {
        this.date = date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
