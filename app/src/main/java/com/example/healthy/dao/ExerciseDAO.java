package com.example.healthy.dao;

import androidx.lifecycle.LiveData;

import com.example.healthy.models.ExerciseProgress;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExerciseDAO {

    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static ExerciseDAO instance;
    private static Object lock = new Object();

    public static ExerciseDAO getInstance() {
        if(instance ==null){
            synchronized (lock){
                if(instance ==null){
                    instance = new ExerciseDAO();
                }
            }
        }
        return instance;
    }

    public void addExercise(ExerciseProgress exerciseProgress) {
    }

    public LiveData<ArrayList<ExerciseProgress>> getAllExerciseProgress() {
        return null;
    }

    public LiveData<Integer> getTotalExercisesCompleted() {
        return null;
    }
}
