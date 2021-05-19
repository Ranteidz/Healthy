package com.example.healthy.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.dao.MeditationDAO;
import com.example.healthy.dao.UserDAO;
import com.example.healthy.models.MeditationProgress;

import java.util.ArrayList;
import java.util.List;

public class MeditationRepository {

    private static MeditationRepository instance;
    private static Object lock = new Object();
    private MeditationDAO meditationDAO;


    private MeditationRepository(){
        meditationDAO = MeditationDAO.getInstance();
    }


    public static MeditationRepository getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new MeditationRepository();
                }
            }
        }
        return instance;
    }

    public void addMeditation(MeditationProgress meditationProgress,String UID){

        meditationDAO.addMeditation(meditationProgress,UID);
    }
    public MutableLiveData<List<MeditationProgress>> getAllMeditationProgress(){

        return meditationDAO.getAllMeditationProgress();
    }

    public void removeListener(){
        meditationDAO.removeListener();
    }


    public void init() {
        meditationDAO.init();
    }
}
