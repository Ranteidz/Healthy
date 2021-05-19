package com.example.healthy.dao;

import androidx.lifecycle.LiveData;

import com.example.healthy.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserDAO {

    private DatabaseReference reference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static UserDAO instance;
    private static Object lock = new Object();

    public UserDAO() {

    }

    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }

    public LiveData<FirebaseUser> getUser() {
        return null;
    }

    public void registerUser(User user) {

    }


    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}
