package com.example.healthy.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthy.R;
import com.example.healthy.viewmodels.MeditationViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MeditationFragment extends Fragment implements View.OnClickListener {

    View view;
    Switch aSwitch;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView timer;
    ImageButton startButton;
    ImageButton pauseButton;
    MeditationViewModel meditationViewModel;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    MediaPlayer player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_meditation, container, false);
        aSwitch = view.findViewById(R.id.meditation_switch);
        radioGroup = view.findViewById(R.id.meditation_rgroup);
        timer = view.findViewById(R.id.meditation_timer);
        startButton = view.findViewById(R.id.meditation_start_button);
        pauseButton = view.findViewById(R.id.meditation_pause_button);

        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        aSwitch.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();
        return view;

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        meditationViewModel = new ViewModelProvider(this).get(MeditationViewModel.class);

        meditationViewModel.updateTimer().observeForever( new Observer<String>() {
            @Override
            public void onChanged(String s) {
                timer.setText(s);
            }
        });

        meditationViewModel.getIsSwitched().observeForever( new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                aSwitch.setChecked(aBoolean);
                aSwitch.isChecked();
            }
        });

        meditationViewModel.getIsFinishedBell().observeForever( new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    play(getView());
                }
            }
        });

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = view.findViewById(radioID);

        switch (v.getId()) {

            case R.id.meditation_start_button:
                if (radioID == R.id.meditaion_radio_5min) {
                    meditationViewModel.startTimer(300000, aSwitch.isChecked(),firebaseUser.getUid());
                    return;
                } else if (radioID == R.id.meditaion_radio_10min) {
                    meditationViewModel.startTimer(600000, aSwitch.isChecked(),firebaseUser.getUid());
                    return;
                } else if (radioID == R.id.meditaion_radio_15min) {
                    meditationViewModel.startTimer(900000, aSwitch.isChecked(),firebaseUser.getUid());
                    return;
                }
            case R.id.meditation_pause_button:
                meditationViewModel.pauseTimer();
                timer.setText("00:00");
                Context context1 = getActivity().getApplicationContext();
                Toast toast2 = Toast.makeText(context1, "Timer has been cancelled and progress not saved", Toast.LENGTH_SHORT);
                toast2.show();
                return;
            case R.id.meditation_switch:

                return;
            default:
        }
    }

    public void play(View v) {
        if (player == null) {
            Context context = getActivity().getApplicationContext();
            player = MediaPlayer.create(context, R.raw.bell);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.release();
                    player = null;
                }
            });

        }
    }

}

