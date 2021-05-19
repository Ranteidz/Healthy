package com.example.healthy.model;

import com.example.healthy.repositories.ExcerciseRepository;
import com.example.healthy.repositories.FastRepository;
import com.example.healthy.repositories.MeditationRepository;
import com.example.healthy.repositories.UserRepository;
import com.example.healthy.repositories.WaterRepository;

public class Model {

    private UserRepository userRepository;
    private ExcerciseRepository excerciseRepository;
    private FastRepository fastRepository;
    private MeditationRepository meditationRepository;
    private WaterRepository waterRepository;

    private static Model instance;
    private static Object threadLock = new Object();

    public static Model getInstance() {
        if (instance == null) {
            synchronized (threadLock) {
                if (instance == null) {
                    instance = new Model();
                }
            }
        }
        return instance;
    }

    public Model() {
        userRepository = UserRepository.getInstance();
        fastRepository = FastRepository.getInstance();
        meditationRepository = MeditationRepository.getInstance();
        waterRepository = WaterRepository.getInstance();
        excerciseRepository = ExcerciseRepository.getInstance();
    }

    public void init() {
        excerciseRepository.init();
        userRepository.init();
        fastRepository.init();
        meditationRepository.init();
        waterRepository.init();
    }

    public void removeListeners() {
        excerciseRepository.removeListener();
        fastRepository.removeListener();
        meditationRepository.removeListener();
        waterRepository.removeListener();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
