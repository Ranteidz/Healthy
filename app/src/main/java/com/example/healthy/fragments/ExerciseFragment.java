package com.example.healthy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthy.R;
import com.example.healthy.viewmodels.ExcerciseViewModel;

public class ExerciseFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ExcerciseViewModel excerciseViewModel;
    private Button addWarmButton, addBoxWButton, addAbsButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_excercise, container, false);
        addAbsButton = view.findViewById(R.id.addAbsWorkout);
        addBoxWButton = view.findViewById(R.id.addBoxWorkout);
        addWarmButton = view.findViewById(R.id.addWarmUpWorkout);

        addAbsButton.setOnClickListener(this);
        addBoxWButton.setOnClickListener(this);
        addWarmButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        excerciseViewModel = new ViewModelProvider(this).get(ExcerciseViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAbsWorkout:
                excerciseViewModel.addExercise("Abs Workout");
                break;
            case R.id.addBoxWorkout:
                excerciseViewModel.addExercise("Boxing Workout");
                break;
            case R.id.addWarmUpWorkout:
                excerciseViewModel.addExercise("Warmup Workout");
                break;
        }
    }
}
