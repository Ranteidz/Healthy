package com.example.healthy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthy.R;
import com.example.healthy.viewmodels.FastingViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FastingFragment extends Fragment implements View.OnClickListener {

    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button startButton;
    private View view;
    private TextView timeRemaining;
    private FastingViewModel fastingViewModel;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fasting, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        radioGroup = view.findViewById(R.id.fasting_radioGroup);
        startButton = view.findViewById(R.id.fasting_add_button);
        timeRemaining = view.findViewById(R.id.fasting_info);
        progressBar.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        startButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fastingViewModel = new ViewModelProvider(this).get(FastingViewModel.class);
        fastingViewModel.getIsCurrentlyFasting().observe(this, value -> {
            if (value) {
                radioGroup.setVisibility(View.GONE);
                startButton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                fastingViewModel.updateTimer().observeForever(new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        timeRemaining.setText(s);
                    }
                });
            }
        });

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int radioID = radioGroup.getCheckedRadioButtonId();


        if (radioID == R.id.fasting_rb_8hours) {
            fastingViewModel.addFast(8, firebaseUser.getUid());
        } else if (radioID == R.id.fasting_rb_16hours) {
            fastingViewModel.addFast(16, firebaseUser.getUid());
        }
    }
}
