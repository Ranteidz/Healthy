package com.example.healthy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.healthy.MainActivity;
import com.example.healthy.R;
import com.example.healthy.fragments.ExerciseFragment;
import com.example.healthy.fragments.FastingFragment;
import com.example.healthy.fragments.HomeFragment;
import com.example.healthy.fragments.MeditationFragment;
import com.example.healthy.fragments.WaterFragment;
import com.example.healthy.model.Model;
import com.example.healthy.navigation.BaseNav;
import com.example.healthy.repositories.WaterRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends BaseNav {
    private Toolbar toolbar;
    Fragment homeFragment = new HomeFragment();
    Fragment excerciseFragment = new ExerciseFragment();
    Fragment fastingFragment = new FastingFragment();
    Fragment meditationFragment = new MeditationFragment();
    Fragment waterFragment = new WaterFragment();
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.topNavBaras);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment_container,homeFragment).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        Model.getInstance().init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WaterRepository.getInstance().removeListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment selectedFragment = null;

           switch (item.getItemId()){
               case R.id.exercise_bot_menu:
                   selectedFragment = excerciseFragment;
                   break;
               case R.id.meditation_bot_menu:
                   selectedFragment = meditationFragment;
                   break;
               case R.id.home_bot_menu:
                   selectedFragment = homeFragment;
                   break;
               case R.id.water_bot_menu:
                   selectedFragment = waterFragment;
                   break;
               case R.id.fasting_bot_menu:
                   selectedFragment = fastingFragment;
                   break;
           }
           getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment_container,selectedFragment).commit();
           return true;
        }
    };


}
