package com.example.healthy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthy.R;
import com.example.healthy.viewmodels.WaterIntakeViewModel;

public class WaterFragment extends Fragment implements View.OnClickListener {

    View view;
    WaterIntakeViewModel waterViewModel;
    ProgressBar progressBar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button addButton;
    TextView glassInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_water, container, false);

        progressBar = view.findViewById(R.id.progress_bar_water);
        radioGroup = view.findViewById(R.id.water_radioGroup);

        addButton = view.findViewById(R.id.water_add_button);
        glassInfo = view.findViewById(R.id.tv_glass_remaining);
        addButton = (Button) view.findViewById(R.id.water_add_button);

        addButton.setOnClickListener(this);
        waterViewModel = new ViewModelProvider(this).get(WaterIntakeViewModel.class);

        waterViewModel.getWaterProgressForToday().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                progressBar.setProgress((integer * 100) / 2000);
                if (integer == 2000) {
                    Context context = getActivity().getApplicationContext();
                    Toast toast = Toast.makeText(context, "Good job staying hydrated! Come back tomorrow to track your water progress", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return view;

    }

    @Override
    public void onClick(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = view.findViewById(radioId);

        if (v.getId() == R.id.water_add_button) {
            if (radioButton == view.findViewById(R.id.water_rb_250ml)) {
                waterViewModel.addWater(250);
            } else if (radioButton == view.findViewById(R.id.water_rb_500ml)) {
                waterViewModel.addWater(500);
            }
        }

    }


}
