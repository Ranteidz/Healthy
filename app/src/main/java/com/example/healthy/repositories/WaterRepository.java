package com.example.healthy.repositories;

import androidx.lifecycle.LiveData;

import com.example.healthy.dao.WaterDAO;
import com.example.healthy.models.WaterProgress;

import java.util.ArrayList;
import java.util.List;

public class WaterRepository {

    private static WaterRepository instance;
    private static Object lock = new Object();
    private WaterDAO waterDAO;

    private WaterRepository(){

        waterDAO = WaterDAO.getInstance();
    }

    public static WaterRepository getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new WaterRepository();
                }
            }
        }
        return instance;
    }


    public void addWater(WaterProgress waterProgress){

        waterDAO.addWater(waterProgress);
    }

    public LiveData<Integer> getWaterProgressForToday(){
       return waterDAO.getWaterProgressForToday();
    }

    public LiveData<List<WaterProgress>> getAllWaterProgress(){
        return waterDAO.getAllWaterProgress();
    }

    public LiveData<Integer> getTotalDaysHydrated(){
        return waterDAO.getTotalDaysHydrated();
    }
}
