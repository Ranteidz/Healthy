package com.example.healthy.viewmodels;


import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.healthy.models.MeditationProgress;
import com.example.healthy.repositories.MeditationRepository;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MeditationViewModel extends ViewModel {




    private MutableLiveData<String> timer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isSwitched = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);
    private CountDownTimer countDownTimer;
    private Boolean isRunning;
    private static long time;

    public MeditationViewModel() {
        this.isRunning = false;
    }

    public LiveData<Boolean> getIsSwitched() {
        return isSwitched;
    }

    public LiveData<Boolean> getIsFinishedBell() {
        return isFinished;
    }


    public void startTimer(long timeInMillis, boolean isBellOn,String UID) {


        isFinished.setValue(false);
        time = timeInMillis;
        if (!isRunning) {
            countDownTimer = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time = millisUntilFinished;
                    updateTimer();
                    isRunning = true;
                }

                @Override
                public void onFinish() {


                    isRunning = false;
                    if (isBellOn) {
                        isFinished.setValue(true);
                    }
                    MeditationProgress tmp = new MeditationProgress(java.time.LocalDate.now().toString(), TimeUnit.MILLISECONDS.toMinutes(timeInMillis));
                    MeditationRepository.getInstance().addMeditation(tmp,UID);


                }
            }.start();
        }
    }


    public void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            isRunning = false;
        }
    }

    public LiveData<String> updateTimer() {

        int minutes = (int) (time / 1000) / 60;
        int seconds = (int) (time / 1000) % 60;
        timer.postValue(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));

        return timer;
    }


    public void flipSwitch() {
        isSwitched.postValue(true);
        /*
        if (isSwitched.getValue()==false) {
            isSwitched.postValue(true);
        } else isSwitched.postValue(false);*/
    }


}
