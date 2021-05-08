package com.example.healthy.persistence;

import com.example.healthy.models.ExerciseProgress;
import com.example.healthy.models.FastingProgress;
import com.example.healthy.models.MeditationProgress;
import com.example.healthy.models.WaterProgress;

import java.util.ArrayList;

public class Persistence {



    private Persistence(){

    }

    public void addWater(int ammount){

    }

/*
    public LiveData<Integer> getWaterProgressForToday() {

    }*/


    public void addMeditation(long duration){

    }

    public int getTotalMinutesMeditated(){
        return 0;
    }

    public int getTotalExercisesCompleted(){
        return 0;
    }

    public void AddExercise(){

    }

    public int getTotalDaysHydrated(){
        return 0;
    }

    public int getTotalFastsCompleted(){
        return 0;
    }

    public void addFast(){

    }

    public ArrayList<MeditationProgress> getAllMeditationProgress(){
        return null;
    }

    public ArrayList<WaterProgress> getAllWaterProgress(){
    return null;
    }

    public ArrayList<ExerciseProgress> getAllExerciseProgress(){
        return null;
    }

    public ArrayList<FastingProgress> getAllFastingProgress(){
        return null;
    }






}
