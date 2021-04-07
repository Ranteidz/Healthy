package com.example.healthy.navigation;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthy.MainActivity;
import com.example.healthy.R;
import com.example.healthy.activities.LoginP;
import com.example.healthy.activities.Meditation;
import com.example.healthy.activities.settings.About;
import com.example.healthy.activities.settings.Help;
import com.example.healthy.activities.settings.Notifications;
import com.example.healthy.activities.settings.Profile;
import com.example.healthy.activities.settings.Settings;

public class BaseNav extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Context context = getApplicationContext();

        switch (item.getItemId()) {
            case R.id.profileMenu:
                startActivity(new Intent(context, Profile.class));
                return true;

            case R.id.notificationsMenu:
                startActivity(new Intent(context, Notifications.class));
                return true;
            case R.id.settingsMenu:
                startActivity(new Intent(context, Settings.class));
                return true;
            case R.id.helpMenu:
                startActivity(new Intent(context, Help.class));
                return true;
            case R.id.aboutMenu:
                startActivity(new Intent(context, About.class));
                return true;
            case R.id.logoutMenu:
                startActivity(new Intent(context, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
