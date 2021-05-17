package com.example.healthy.dao;

import android.app.UiAutomation;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.models.MeditationProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeditationDAO {

    private MutableLiveData<List<MeditationProgress>> meditationProgressList;
    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;

    private static MeditationDAO instance;
    private static Object lock = new Object();


    public MeditationDAO(){
        reference = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        meditationProgressList = new MutableLiveData<>();

        reference.child("users").child(firebaseAuth.getUid()).child("meditationProgresses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<MeditationProgress> tmpList = new ArrayList<>();

                try{
                    DataSnapshot snapshot1;
                    Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();

                    while (null != (snapshot1 = iterator.next())){
                        MeditationProgress meditationProgress = snapshot1.getValue(MeditationProgress.class);
                        tmpList.add(meditationProgress);
                    }



                }catch (Exception e){

                }

                Log.println(Log.INFO,"test",String.valueOf(tmpList.size()));
                meditationProgressList.setValue(tmpList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
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

    public MutableLiveData<List<MeditationProgress>> getAllMeditationProgress() {
        return meditationProgressList;
    }

    public LiveData<Integer> getTotalMinutesMeditated() {
        return null;
    }
}
