package com.example.healthy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthy.persistence.Persistence;

public class WaterIntakeViewModel extends ViewModel {
Persistence persistence;

private MutableLiveData<Integer> waterProgress = new MutableLiveData<>();
private MutableLiveData<Boolean> isFinished = new MutableLiveData<>();

public LiveData<Integer> getWaterProgress(){
   waterProgress.setValue(1500);
   return waterProgress;

   /*return persistence.getWaterProgressForToday();*/
}

public void retrieveData(){

}

   public void addWater(int ammount){

   persistence.addWater(ammount);
   }
}
