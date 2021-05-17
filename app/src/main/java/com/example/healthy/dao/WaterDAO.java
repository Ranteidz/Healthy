package com.example.healthy.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.models.WaterProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WaterDAO {

    private MutableLiveData<List<WaterProgress>> waterProgressList;
    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static WaterDAO instance;
    private static Object lock = new Object();
    private FirebaseAuth firebaseAuth;

    private WaterDAO() {
        reference = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        waterProgressList = new MutableLiveData<>();
/*
        reference.child("users").child(firebaseAuth.getUid()).child("waterProgresses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                waterProgressList
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        })*/
    }

    public static WaterDAO getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new WaterDAO();
                }
            }
        }
        return instance;
    }

    public void addWater(WaterProgress waterProgress) {
        reference.child("users").child(firebaseAuth.getUid()).child("waterProgresses").push().setValue(waterProgress);

    }

    public LiveData<Integer> getWaterProgressForToday() {
        return null;
    }

    public LiveData<List<WaterProgress>> getAllWaterProgress() {
        return null;
    }

    public LiveData<Integer> getTotalDaysHydrated() {
        return null;
    }
}
