package com.example.healthy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.WaterProgress;

import com.example.healthy.repositories.WaterRepository;

import java.util.List;

public class WaterIntakeViewModel extends ViewModel {


    private MutableLiveData<List<WaterProgress>> waterProgressesList;
    private MutableLiveData<Integer> waterProgress ;
    private MutableLiveData<Boolean> isFinished = new MutableLiveData<>();


    public WaterIntakeViewModel() {
        waterProgress= new MutableLiveData<>(0);
        waterProgressesList = WaterRepository.getInstance().getAllWaterProgress();
        waterProgressesList.observeForever(list -> {
            waterProgress.setValue(0);
            for (WaterProgress item: list
                 ) {
                if(item.getDate().equals(java.time.LocalDate.now().toString())){
                    waterProgress.setValue(waterProgress.getValue()+ item.getAmmount());
                }
            }

        });
    }

    public LiveData<Integer> getWaterProgressForToday() {

        return waterProgress;


    }


    public void addWater(int ammount) {
        WaterProgress tmp = new WaterProgress(java.time.LocalDate.now().toString(), ammount);
        WaterRepository.getInstance().addWater(tmp);

    }


}
