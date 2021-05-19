package com.example.healthy.models;

import java.util.ArrayList;

public class User {


    private String username;
    private String email;
    private String password;
    private String test;
    private ArrayList<WaterProgress> waterProgresses;
    private ArrayList<ExerciseProgress> exerciseProgresses;
    private ArrayList<MeditationProgress> meditationProgresses;
    private ArrayList<FastingProgress> fastingProgresses;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String email, String password, String username, String test) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.test = test;
    }

    public User(String email, String password, String username, ArrayList<WaterProgress> waterProgresses, ArrayList<ExerciseProgress> exerciseProgresses, ArrayList<MeditationProgress> meditationProgresses, ArrayList<FastingProgress> fastingProgresses) {
        this.username = username;
        this.waterProgresses = waterProgresses;
        this.exerciseProgresses = exerciseProgresses;
        this.meditationProgresses = meditationProgresses;
        this.fastingProgresses = fastingProgresses;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<WaterProgress> getWaterProgresses() {
        return waterProgresses;
    }

    public void setWaterProgresses(ArrayList<WaterProgress> waterProgresses) {
        this.waterProgresses = waterProgresses;
    }

    public ArrayList<ExerciseProgress> getExerciseProgresses() {
        return exerciseProgresses;
    }

    public void setExerciseProgresses(ArrayList<ExerciseProgress> exerciseProgresses) {
        this.exerciseProgresses = exerciseProgresses;
    }

    public ArrayList<MeditationProgress> getMeditationProgresses() {
        return meditationProgresses;
    }

    public void setMeditationProgresses(ArrayList<MeditationProgress> meditationProgresses) {
        this.meditationProgresses = meditationProgresses;
    }

    public ArrayList<FastingProgress> getFastingProgresses() {
        return fastingProgresses;
    }

    public void setFastingProgresses(ArrayList<FastingProgress> fastingProgresses) {
        this.fastingProgresses = fastingProgresses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
