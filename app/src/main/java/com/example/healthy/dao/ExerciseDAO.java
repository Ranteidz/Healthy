package com.example.healthy.dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthy.models.ExerciseProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExerciseDAO {

    private MutableLiveData<List<ExerciseProgress>> exerciseProgressesList;
    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;
    private ValueEventListener valueEventListener;


    private static ExerciseDAO instance;
    private static Object lock = new Object();

    public ExerciseDAO() {
        reference = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        exerciseProgressesList = new MutableLiveData<>();

    }


    public static ExerciseDAO getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ExerciseDAO();
                }
            }
        }
        return instance;
    }

    public void addExercise(ExerciseProgress exerciseProgress) {
        reference.child("users").child(firebaseAuth.getUid()).child("exerciseProgresses").push().setValue(exerciseProgress);
    }

    public MutableLiveData<List<ExerciseProgress>> getAllExerciseProgress() {
        return exerciseProgressesList;
    }

    public LiveData<Integer> getTotalExercisesCompleted() {
        return null;
    }

    public void removeListener() {
        reference.removeEventListener(valueEventListener);
    }


    public void init() {
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<ExerciseProgress> tmpList = new ArrayList<>();
                try {
                    DataSnapshot snapshot1;
                    Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();

                    while (iterator.hasNext()) {
                        snapshot1 = iterator.next();
                        ExerciseProgress exerciseProgress = snapshot1.getValue(ExerciseProgress.class);
                        tmpList.add(exerciseProgress);
                    }
                } catch (Exception e) {
                    Log.println(Log.ERROR, "Firebase", e.getMessage());
                }

                exerciseProgressesList.setValue(tmpList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };

        reference.child("users").child(firebaseAuth.getUid()).child("exerciseProgresses").addValueEventListener(valueEventListener);
    }
}
