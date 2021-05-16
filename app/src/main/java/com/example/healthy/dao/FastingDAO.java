package com.example.healthy.dao;

import androidx.lifecycle.LiveData;

import com.example.healthy.models.FastingProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FastingDAO {

    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private static FastingDAO instance;
    private static Object lock = new Object();

    public FastingDAO() {
        /*        firebaseAuth= FirebaseAuth.getInstance();*/
        reference = database.getReference();
        /* firebaseUser =firebaseAuth.getCurrentUser();*/
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

    public LiveData<ArrayList<FastingProgress>> getAllFasts() {
        return null;
    }

    public LiveData<FastingProgress> getCurrentFasting() {
        return null;
    }

    public LiveData<Integer> getFastsCompleted() {
        return null;
    }
}
