package com.example.healthy.activities.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthy.R;
import com.example.healthy.navigation.BaseNav;

public class Help extends BaseNav {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
}