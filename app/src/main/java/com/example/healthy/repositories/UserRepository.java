package com.example.healthy.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.healthy.dao.UserDAO;
import com.example.healthy.models.User;
import com.google.firebase.auth.FirebaseUser;
public class UserRepository {

    private static UserRepository instance;
    private static Object lock = new Object();
    private UserDAO userDAO;

    private UserRepository(){
        userDAO = UserDAO.getInstance();
    }


    public static UserRepository getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<FirebaseUser> getUser(){
  return  userDAO.getUser();
    }

    public void registerUser(User user){

         userDAO.registerUser( user);
    }

    public void signOut(){
        userDAO.signOut();

    }

    public void init() {
    }
}
