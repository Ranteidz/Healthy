package com.example.healthy.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.dao.ExerciseDAO;
import com.example.healthy.dao.UserDAO;
import com.example.healthy.models.ExerciseProgress;

import java.util.ArrayList;
import java.util.List;

public class ExcerciseRepository {

    private static ExcerciseRepository instance;
    private static Object lock = new Object();
    private ExerciseDAO exerciseDAO;


    private ExcerciseRepository(){
        exerciseDAO = ExerciseDAO.getInstance();
    }


    public static ExcerciseRepository getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new ExcerciseRepository();
                }
            }
        }
        return instance;
    }

    public void addExercise(ExerciseProgress exerciseProgress){

        exerciseDAO.addExercise(exerciseProgress);
    }
    public MutableLiveData<List<ExerciseProgress>> getAllExerciseProgress(){
       return exerciseDAO.getAllExerciseProgress();
    }

    public LiveData<Integer> getTotalExercisesCompleted(){
        return exerciseDAO.getTotalExercisesCompleted();
    }

    public void removeListener(){
        exerciseDAO.removeListener();
    }
}
