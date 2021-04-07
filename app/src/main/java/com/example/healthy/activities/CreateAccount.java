package com.example.healthy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


import com.example.healthy.R;

public class CreateAccount extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void Login(View view) {
        startActivity(new Intent(this,LoginP.class));
    }

    public void CreateAccountPressed(View view) {
    }
}