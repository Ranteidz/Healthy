package com.example.healthy.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.dao.WaterDAO;
import com.example.healthy.models.WaterProgress;

import java.util.List;

public class WaterRepository {

    private static WaterRepository instance;
    private static Object lock = new Object();
    private WaterDAO waterDAO;

    private WaterRepository() {

        waterDAO = WaterDAO.getInstance();
    }

    public static WaterRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new WaterRepository();
                }
            }
        }
        return instance;
    }


    public void addWater(WaterProgress waterProgress) {

        waterDAO.addWater(waterProgress);
    }

    public LiveData<Integer> getWaterProgressForToday() {
        return waterDAO.getWaterProgressForToday();
    }

    public MutableLiveData<List<WaterProgress>> getAllWaterProgress() {
        return waterDAO.getAllWaterProgress();
    }

    public void removeListener(){
        waterDAO.removeListener();
    }


}
