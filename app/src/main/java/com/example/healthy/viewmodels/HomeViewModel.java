package com.example.healthy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.ExerciseProgress;
import com.example.healthy.models.FastingProgress;
import com.example.healthy.models.Item;
import com.example.healthy.models.MeditationProgress;
import com.example.healthy.models.WaterProgress;
import com.example.healthy.repositories.ExcerciseRepository;
import com.example.healthy.repositories.FastRepository;
import com.example.healthy.repositories.MeditationRepository;
import com.example.healthy.repositories.WaterRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private MutableLiveData<Integer> fastsCompleted;

    private List<FastingProgress> fastingProgressesHistoryList = new ArrayList<>();
    private List<MeditationProgress> meditationProgressesHistoryList = new ArrayList<>();
    private List<ExerciseProgress> exerciseProgressesHistoryList = new ArrayList<>();
    private List<WaterProgress> waterProgressesHistoryList = new ArrayList<>();

    private Map<String, Integer> hashMap;

    private FastRepository fastRepository;
    private MeditationRepository meditationRepository;
    private ExcerciseRepository excerciseRepository;
    private WaterRepository waterRepository;

    public HomeViewModel() {

        fastRepository = FastRepository.getInstance();
        meditationRepository = MeditationRepository.getInstance();
        excerciseRepository = ExcerciseRepository.getInstance();
        waterRepository = WaterRepository.getInstance();

        hashMap = new HashMap<>();
        //Meditation
        minutesMeditated = new MutableLiveData<Long>((long) 0);
        meditationProgressesList = meditationRepository.getAllMeditationProgress();

        //Exercise
        exercisesCompleted = new MutableLiveData<>(0);
        exerciseProgressesList = excerciseRepository.getAllExerciseProgress();

        //Water
        waterProgressesList = waterRepository.getAllWaterProgress();
        daysHydrated = new MutableLiveData<>(0);

        //Fasting
        fastsCompleted = new MutableLiveData<>(0);
        fastingProgressesList = fastRepository.getAllFasts();
        fastObserve();
        meditationObserve();
        exerciseObserve();
        waterObserve();

    }

    public void fastObserve() {
        fastingProgressesList.observeForever(list -> {
            fastsCompleted.setValue(0);
            fastingProgressesHistoryList.clear();
            for (FastingProgress item : list
            ) {
                Calendar currentTime = Calendar.getInstance();
                currentTime.setTime(new Date());
                if (currentTime.getTimeInMillis() > item.getEndDate()) {
                    fastsCompleted.setValue(fastsCompleted.getValue() + 1);
                    fastingProgressesHistoryList.add(item);
                }
            }
        });
    }

    public void meditationObserve() {
        meditationProgressesList.observeForever(list -> {
            meditationProgressesHistoryList.clear();
            minutesMeditated.setValue((long) 0);
            for (MeditationProgress item : list
            ) {
                minutesMeditated.setValue(minutesMeditated.getValue() + item.getDuration());
                meditationProgressesHistoryList.add(item);
            }
        });

    }

    public void waterObserve() {
        waterProgressesList.observeForever(list -> {
            daysHydrated.setValue(0);
            waterProgressesHistoryList.clear();
            hashMap.clear();
            for (WaterProgress item : list
            ) {
                waterProgressesHistoryList.add(item);
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

    public void exerciseObserve() {
        exerciseProgressesList.observeForever(list -> {
            exercisesCompleted.setValue(0);
            exerciseProgressesHistoryList.clear();
            for (ExerciseProgress item : list
            ) {
                exerciseProgressesHistoryList.add(item);
                exercisesCompleted.setValue(exercisesCompleted.getValue() + 1);
            }
        });
    }

    public LiveData<Integer> getFastsCompleted() {
        return fastsCompleted;
    }

    public LiveData<Integer> getDaysHydrated() {
        return daysHydrated;
    }

    public LiveData<Integer> getExercisesCompleted() {
        return exercisesCompleted;
    }

    public LiveData<Long> getMinutesMeditated() {
        return minutesMeditated;
    }

    public ArrayList<Item> getFastHistory() {
        ArrayList<Item> items = new ArrayList<>();
        for (FastingProgress fastingItem : fastingProgressesHistoryList
        ) {

            items.add(new Item("Fasting", new Date(fastingItem.getStartDate()).toString(), new Date(fastingItem.getEndDate()).toString()));
        }
        return items;
    }


    public ArrayList<Item> getWaterHistory() {
        ArrayList<Item> items = new ArrayList<>();

        for (WaterProgress waterItem : waterProgressesHistoryList
        ) {
            items.add(new Item("Water", waterItem.getDate(), "Ammount: " + String.valueOf(waterItem.getAmmount())));
        }

        return items;
    }

    public ArrayList<Item> getExerciseHistory() {
        ArrayList<Item> items = new ArrayList<>();

        for (ExerciseProgress exerciseItem : exerciseProgressesHistoryList
        ) {
            items.add(new Item("Exercise", exerciseItem.getDate(), exerciseItem.getName()));
        }
        return items;
    }

    public ArrayList<Item> getMeditationHistory() {
        ArrayList<Item> items = new ArrayList<>();
        for (MeditationProgress meditationItem : meditationProgressesHistoryList
        ) {
            items.add(new Item("Meditation", meditationItem.getDate(), "Duration: " + String.valueOf(meditationItem.getDuration())));
        }
        return items;
    }

}
