package com.example.healthy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.ExerciseProgress;
import com.example.healthy.models.FastingProgress;
import com.example.healthy.models.MeditationProgress;
import com.example.healthy.models.WaterProgress;
import com.example.healthy.repositories.ExcerciseRepository;
import com.example.healthy.repositories.FastRepository;
import com.example.healthy.repositories.MeditationRepository;
import com.example.healthy.repositories.WaterRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<MeditationProgress>> meditationProgressesList;
    private MutableLiveData<List<ExerciseProgress>> exerciseProgressesList;
    private MutableLiveData<List<WaterProgress>> waterProgressesList;
    private MutableLiveData<List<FastingProgress>> fastingProgressesList;
    private MutableLiveData<Long> minutesMeditated;
    private MutableLiveData<Integer> exercisesCompleted;
    private MutableLiveData<Integer> daysHydrated;
    private Map<String, Integer> hashMap;

    public HomeViewModel() {


        hashMap = new HashMap<>();
        //Meditation
        minutesMeditated = new MutableLiveData<Long>((long) 0);
        meditationProgressesList = MeditationRepository.getInstance().getAllMeditationProgress();

        //Exercise
        exercisesCompleted = new MutableLiveData<>(0);
        exerciseProgressesList = ExcerciseRepository.getInstance().getAllExerciseProgress();

        //Water
        waterProgressesList = WaterRepository.getInstance().getAllWaterProgress();
        daysHydrated = new MutableLiveData<>(0);

        //Fasting
        fastingProgressesList = FastRepository.getInstance().getAllFasts();

        meditationProgressesList.observeForever(list -> {
            minutesMeditated.setValue((long) 0);
            for (MeditationProgress item : list
            ) {
                minutesMeditated.setValue(minutesMeditated.getValue() + item.getDuration());
            }
        });

        exerciseProgressesList.observeForever(list -> {
            exercisesCompleted.setValue(0);
            for (ExerciseProgress item : list
            ) {
                exercisesCompleted.setValue(exercisesCompleted.getValue() + 1);
            }
        });

        waterProgressesList.observeForever(list -> {
            daysHydrated.setValue(0);
            hashMap.clear();
            for (WaterProgress item : list
            ) {
                if (hashMap.get(item.getDate()) == null)
                    hashMap.put(item.getDate(), item.getAmmount());
                else hashMap.put(item.getDate(), hashMap.get(item.getDate()) + item.getAmmount());

            }
            for (Integer values : hashMap.values()
            ) {
                if (values >= 2000) {

                    daysHydrated.setValue(daysHydrated.getValue() + 1);
                }
            }

        });


    }

    public LiveData<Integer> getDaysHydrated() {
        return daysHydrated;
    }

    public LiveData<Integer> getExercisesCompleted() {
        return exercisesCompleted;
    }

    public MutableLiveData<List<MeditationProgress>> getMeditationProgressesList() {
        return meditationProgressesList;
    }

    public LiveData<Long> getMinutesMeditated() {
        return minutesMeditated;
    }

}
