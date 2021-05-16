package com.example.healthy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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

    //To be deleted
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;

    private String aName, aPassword, aEmail;
    private ArrayList<WaterProgress> waterProgresses;
    private ArrayList<ExerciseProgress> exerciseProgresses;
    private ArrayList<MeditationProgress> meditationProgresses;
    private ArrayList<FastingProgress> fastingProgresses;
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

        //To be deleted
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("users");
        mAuth = FirebaseAuth.getInstance();

        //Testing progresses
        WaterProgress waterProgress = new WaterProgress(java.time.LocalDate.now().toString(),2500);
        MeditationProgress meditationProgress = new MeditationProgress(java.time.LocalDate.now().toString(),5);
        ExerciseProgress exerciseProgress = new ExerciseProgress(java.time.LocalDate.now().toString(),"back workout");
        FastingProgress fastingProgress = new FastingProgress(java.time.Clock.systemDefaultZone().millis(),java.time.Clock.systemDefaultZone().millis());

        //only for testing firebase
        waterProgresses = new ArrayList<>();
        exerciseProgresses = new ArrayList<>();
        meditationProgresses = new ArrayList<>();
        fastingProgresses = new ArrayList<>();

        waterProgresses.add(waterProgress);
        exerciseProgresses.add(exerciseProgress);
        meditationProgresses.add(meditationProgress);
        fastingProgresses.add(fastingProgress);

        createAccountButton.setOnClickListener((v -> {
            if(name.getText().toString() !=null && password.getText().toString() !=null && confirmPassword.getText().toString() !=null && email.getText().toString() !=null){

                aName = name.getText().toString();
                aEmail = email.getText().toString();
                aPassword = password.getText().toString();
                user = new User(aEmail,aPassword,aName,waterProgresses,exerciseProgresses,meditationProgresses,fastingProgresses);
                registerUser();
            }
        }));
    }

    public void Login(View view) {
        startActivity(new Intent(this,LoginP.class));
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