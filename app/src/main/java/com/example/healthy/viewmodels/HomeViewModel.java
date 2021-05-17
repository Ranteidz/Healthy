package com.example.healthy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.MeditationProgress;
import com.example.healthy.repositories.MeditationRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<MeditationProgress>> meditationProgressesList;
    private MutableLiveData<Long> minutesMeditated;

    public HomeViewModel() {
        minutesMeditated = new MutableLiveData<Long>((long) 0);
        meditationProgressesList = MeditationRepository.getInstance().getAllMeditationProgress();
        meditationProgressesList.observeForever(list -> {
            minutesMeditated.setValue((long) 0);
            for (MeditationProgress item : list
            ) {
                minutesMeditated.setValue(minutesMeditated.getValue() + item.getDuration());
            }
        });
    }

    public MutableLiveData<List<MeditationProgress>> getMeditationProgressesList() {
        return meditationProgressesList;
    }

    public LiveData<Long> getMinutesMeditated() {
        return minutesMeditated;
    }

}
