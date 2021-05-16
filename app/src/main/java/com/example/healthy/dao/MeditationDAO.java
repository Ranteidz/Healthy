package com.example.healthy.dao;

import android.app.UiAutomation;

import androidx.lifecycle.LiveData;

import com.example.healthy.models.MeditationProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MeditationDAO {

    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static MeditationDAO instance;
    private static Object lock = new Object();


    public MeditationDAO(){
        reference = database.getReference();

    }


    public static MeditationDAO getInstance() {
        if(instance ==null){
            synchronized (lock){
                if(instance ==null){
                    instance = new MeditationDAO();
                }
            }
        }
        return instance;
    }

    public void addMeditation(MeditationProgress meditationProgress,String UID) {


        reference.child("users").child(UID).child("meditationProgresses").push().setValue(meditationProgress);
    }

    public LiveData<ArrayList<MeditationProgress>> getAllMeditationProgress() {
        return null;
    }

    public LiveData<Integer> getTotalMinutesMeditated() {
        return null;
    }
}
