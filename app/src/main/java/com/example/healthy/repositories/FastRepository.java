package com.example.healthy.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.dao.FastingDAO;
import com.example.healthy.dao.UserDAO;
import com.example.healthy.models.FastingProgress;

import java.util.ArrayList;
import java.util.List;

public class FastRepository {

    private static FastRepository instance;
    private static Object lock = new Object();
    private FastingDAO fastingDAO;

    private FastRepository(){
        fastingDAO = FastingDAO.getInstance();
    }


    public static FastRepository getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new FastRepository();
                }
            }
        }
        return instance;
    }

    public void addFast(FastingProgress fastingProgress,String UID){
        fastingDAO.addFast(fastingProgress,UID);
    }
    public MutableLiveData<List<FastingProgress>> getAllFasts(){
       return fastingDAO.getAllFasts();
    }
    public LiveData<FastingProgress> getCurrentFasting(){
        return fastingDAO.getCurrentFasting();
    }
    public LiveData<Integer> getFastsCompleted(){
        return fastingDAO.getFastsCompleted();
    }
}
