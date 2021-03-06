package com.example.healthy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.healthy.MainActivity;
import com.example.healthy.R;
import com.example.healthy.models.ExerciseProgress;
import com.example.healthy.models.FastingProgress;
import com.example.healthy.models.MeditationProgress;
import com.example.healthy.models.User;
import com.example.healthy.models.WaterProgress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateAccount extends AppCompatActivity {
    EditText name;
    EditText password;
    EditText confirmPassword;
    EditText email;
    Button createAccountButton;


    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;

    private String aName, aPassword, aEmail;
    private User user;
    private String TAG = "RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        email = findViewById(R.id.emailCreate);
        createAccountButton = findViewById(R.id.create_account_button);


        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("users");
        mAuth = FirebaseAuth.getInstance();


        createAccountButton.setOnClickListener((v -> {
            if (name.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Fill all the fields", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT);
                toast.show();
                return;
            } else {

                aName = name.getText().toString();
                aEmail = email.getText().toString();
                aPassword = password.getText().toString();
                user = new User(aName);
                registerUser();
            }
        }));
    }

    public void Login(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(aEmail, aPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void updateUI(FirebaseUser currentUser) {
        String keyid = mDatabase.push().getKey();
        mDatabase.child(currentUser.getUid()).setValue(user); //adding user info to database
        Intent loginIntent = new Intent(this, Home.class);
        startActivity(loginIntent);

    }
}