package com.example.healthy.dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.models.FastingProgress;
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

public class FastingDAO {

    private MutableLiveData<List<FastingProgress>> fastingProgressesList;
    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;
    private ValueEventListener valueEventListener;
    private static FastingDAO instance;
    private static Object lock = new Object();

    public FastingDAO() {
        reference = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        fastingProgressesList = new MutableLiveData<>();
        init();
    }

    public static FastingDAO getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new FastingDAO();
                }
            }
        }
        return instance;
    }

    public void addFast(FastingProgress fastingProgress, String UID) {
        reference.child("users").child(UID).child("fastingProgresses").push().setValue(fastingProgress);
    }

    public MutableLiveData<List<FastingProgress>> getAllFasts() {
        return fastingProgressesList;
    }

    public void removeListener() {
        reference.removeEventListener(valueEventListener);
    }

    public void init() {
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<FastingProgress> tmpList = new ArrayList<>();
                try {
                    DataSnapshot snapshot1;
                    Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();

                    while (iterator.hasNext()) {
                        snapshot1 = iterator.next();
                        FastingProgress fastingProgress = snapshot1.getValue(FastingProgress.class);
                        tmpList.add(fastingProgress);
                    }
                } catch (Exception e) {
                    Log.println(Log.ERROR, "Firebase", e.getMessage());
                }
                fastingProgressesList.setValue(tmpList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };
        reference.child("users").child(firebaseAuth.getUid()).child("fastingProgresses").addValueEventListener(valueEventListener);
    }
}
