package com.example.healthy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.healthy.R;
import com.example.healthy.activities.settings.About;
import com.example.healthy.activities.settings.Help;
import com.example.healthy.activities.settings.Notifications;
import com.example.healthy.activities.settings.Profile;
import com.example.healthy.activities.settings.Settings;
import com.example.healthy.navigation.BaseNav;

public class LoginP extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LoginPressed(View view) {

        startActivity(new Intent(this,Home.class));
    }

    public void SignUpPressed(View view) {
        startActivity(new Intent(this,CreateAccount.class));
    }
}