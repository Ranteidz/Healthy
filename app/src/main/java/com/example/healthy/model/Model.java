package com.example.healthy.model;

import com.example.healthy.repositories.UserRepository;

public class Model {

    private UserRepository userRepository;

    private static Model instance;
    private static Object threadLock = new Object();

    public static Model getInstance() {
        if(instance == null) {
            synchronized (threadLock) {
                if(instance == null) {
                    instance = new Model();
                }
            }
        }
        return instance;
    }

    public Model(){

    }

    public void signOut(){
        userRepository.signOut();
    }
}
