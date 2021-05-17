package com.example.healthy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.ExerciseProgress;
import com.example.healthy.models.MeditationProgress;
import com.example.healthy.repositories.ExcerciseRepository;
import com.example.healthy.repositories.MeditationRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<MeditationProgress>> meditationProgressesList;
    private MutableLiveData<List<ExerciseProgress>> exerciseProgressesList;
    private MutableLiveData<Long> minutesMeditated;
    private MutableLiveData<Integer> exercisesCompleted;

    public HomeViewModel() {

        //Meditation
        minutesMeditated = new MutableLiveData<Long>((long) 0);
        meditationProgressesList = MeditationRepository.getInstance().getAllMeditationProgress();

        //Exercise
        exercisesCompleted = new MutableLiveData<>(0);
        exerciseProgressesList = ExcerciseRepository.getInstance().getAllExerciseProgress();

        //Water

        //Fasting

        meditationProgressesList.observeForever(list -> {
            minutesMeditated.setValue((long) 0);
            for (MeditationProgress item : list
            ) {
                minutesMeditated.setValue(minutesMeditated.getValue() + item.getDuration());
            }
        });

        exerciseProgressesList.observeForever(list->{
            exercisesCompleted.setValue(0);
            for (ExerciseProgress item: list
                 ) {
                exercisesCompleted.setValue(exercisesCompleted.getValue()+1);
            }
        });

    }

    public LiveData<Integer> getExercisesCompleted(){
        return exercisesCompleted;
    }

    public MutableLiveData<List<MeditationProgress>> getMeditationProgressesList() {
        return meditationProgressesList;
    }

    public LiveData<Long> getMinutesMeditated() {
        return minutesMeditated;
    }

}
