package com.example.healthy.viewmodels;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.models.FastingProgress;
import com.example.healthy.repositories.FastRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FastingViewModel extends ViewModel {

    private MutableLiveData<String> timer = new MutableLiveData<>();
    private MutableLiveData<List<FastingProgress>> fastingProgressesList;
    private CountDownTimer countDownTimer;
    private MutableLiveData<Boolean> isCurrentlyFasting = new MutableLiveData<>(false);
    private static long time;
    private MutableLiveData<Boolean> isRunning = new MutableLiveData<>(false);
    private FastingProgress fastingProgress;
    private FastRepository fastRepository;

    public FastingViewModel() {
        fastRepository = FastRepository.getInstance();
        fastingProgress = null;
        fastingProgressesList = fastRepository.getAllFasts();
        fastingProgressesList.observeForever(list -> {
            isCurrentlyFasting.setValue(false);

            //Checking if there are any active fasts
            if (!list.isEmpty()) {
                fastingProgress = list.get(list.size() - 1);
                Calendar currentTime = Calendar.getInstance();
                currentTime.setTime(new Date());
                if (currentTime.getTimeInMillis() < fastingProgress.getEndDate()) {
                    isCurrentlyFasting.setValue(true);
                    Calendar timeNow = Calendar.getInstance();
                    timeNow.setTime(new Date());
                    long timeRemaining = Math.abs(fastingProgress.getEndDate() - timeNow.getTimeInMillis());
                    startTimer(timeRemaining);
                }

            }
        });
    }

    public void startTimer(long timeInMillis) {
        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                isRunning.setValue(false);
                isCurrentlyFasting.setValue(false);
            }
        }.start();
    }

    public void addFast(int hours, String UID) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());

        Calendar futureTime = Calendar.getInstance();
        futureTime.setTime(new Date());
        futureTime.add(Calendar.HOUR_OF_DAY, hours);

        FastingProgress fastingProgress = new FastingProgress(currentTime.getTimeInMillis(), futureTime.getTimeInMillis());

        fastRepository.addFast(fastingProgress, UID);
        isRunning.setValue(true);
    }

    public LiveData<Boolean> getIsCurrentlyFasting() {
        return isCurrentlyFasting;
    }


    public LiveData<String> updateTimer() {
        timer.postValue(String.format("Time remaining for your fast: %02d:%02d:%02d", time / (1000 * 60 * 60),
                (time % (1000 * 60 * 60)) / (1000 * 60),
                ((time % (1000 * 60 * 60)) % (1000 * 60)) / 1000));

        return timer;
    }
}
