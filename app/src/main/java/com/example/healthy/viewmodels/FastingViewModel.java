package com.example.healthy.viewmodels;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.FastingProgress;
import com.example.healthy.repositories.FastRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FastingViewModel extends ViewModel {

    private MutableLiveData<String> timer = new MutableLiveData<>();
    private CountDownTimer countDownTimer;
    private static long time;
    private MutableLiveData<Boolean> isRunning = new MutableLiveData<>(false);

    public FastingViewModel(){

    }

    public void startTimer(){



        countDownTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time= millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                isRunning.setValue(false);
            }
        }.start();
    }

    public void addFast(int hours) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());

        Calendar futureTime = Calendar.getInstance();
        futureTime.setTime(new Date());
        futureTime.add(Calendar.HOUR_OF_DAY,hours);

        FastingProgress fastingProgress = new FastingProgress(currentTime.getTimeInMillis(),futureTime.getTimeInMillis());

        FastRepository.getInstance().addFast(fastingProgress);
        isRunning.setValue(true);
    }

    public void checkIfFasting(){
//TODO check if current time in millis < expected finish time in firebase
        //TODO set boolean to true
        //TODO if true get time remaining in millis and start timer
    }

    public LiveData<String> updateTimer(){

       timer.postValue(String.format("%02d:%02d:%02d", TimeUnit.DAYS.toHours(time),TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
               TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(
                       TimeUnit.MILLISECONDS.toMinutes(time))));
       return timer;
    }
}
