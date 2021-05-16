package com.example.healthy.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.healthy.models.ExerciseProgress;
import com.example.healthy.repositories.ExcerciseRepository;

public class ExcerciseViewModel extends ViewModel {

    public ExcerciseViewModel(){

    }

    public void addExercise(String workoutName) {
        ExerciseProgress exerciseProgress = new ExerciseProgress(java.time.LocalDate.now().toString(),workoutName);
        ExcerciseRepository.getInstance().addExercise(exerciseProgress);
    }
}
