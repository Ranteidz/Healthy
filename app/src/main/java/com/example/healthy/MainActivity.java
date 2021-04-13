package com.example.healthy;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.healthy.activities.CreateAccount;
import com.example.healthy.activities.Home;
import com.example.healthy.navigation.BaseNav;

public class MainActivity extends BaseNav {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginPressed(View view) {

        startActivity(new Intent(this, Home.class));
    }

    public void SignUpPressed(View view) {
        startActivity(new Intent(this, CreateAccount.class));
    }
}
