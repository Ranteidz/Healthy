package com.example.healthy.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.healthy.models.ExerciseProgress;
import com.example.healthy.repositories.ExcerciseRepository;

public class ExcerciseViewModel extends ViewModel {

    private ExcerciseRepository excerciseRepository;

    public ExcerciseViewModel(){
excerciseRepository = ExcerciseRepository.getInstance();
    }

    public void addExercise(String workoutName) {
        ExerciseProgress exerciseProgress = new ExerciseProgress(java.time.LocalDate.now().toString(),workoutName);
        excerciseRepository.addExercise(exerciseProgress);
    }
}
