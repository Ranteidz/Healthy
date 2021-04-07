package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.healthy.activities.CreateAccount;
import com.example.healthy.activities.Home;
import com.example.healthy.activities.LoginP;
import com.example.healthy.activities.Meditation;
import com.example.healthy.activities.settings.About;
import com.example.healthy.activities.settings.Help;
import com.example.healthy.activities.settings.Notifications;
import com.example.healthy.activities.settings.Profile;
import com.example.healthy.activities.settings.Settings;
import com.example.healthy.navigation.BaseNav;

public class MainActivity extends BaseNav {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.topNavBaras);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void LoginPressed(View view) {

        startActivity(new Intent(this, Home.class));
    }

    public void SignUpPressed(View view) {
        startActivity(new Intent(this, CreateAccount.class));
    }
}
