package com.example.healthy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.WaterProgress;

import com.example.healthy.repositories.WaterRepository;

public class WaterIntakeViewModel extends ViewModel {


    private MutableLiveData<Integer> waterProgress = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFinished = new MutableLiveData<>();
    private WaterRepository waterRepository;

    public WaterIntakeViewModel() {
    /*    waterRepository = WaterRepository.getInstance();*/
    }

    public LiveData<Integer> getWaterProgressForToday() {
        /*return WaterRepository.getInstance().getWaterProgressForToday();*/
        waterProgress.setValue(1500);
        return waterProgress;


    }


    public void addWater(int ammount) {
        WaterProgress tmp = new WaterProgress(java.time.LocalDate.now().toString(), ammount);
        WaterRepository.getInstance().addWater(tmp);

    }


}
