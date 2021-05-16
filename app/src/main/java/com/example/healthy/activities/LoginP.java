package com.example.healthy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.healthy.MainActivity;
import com.example.healthy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginP extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button loginButton;
    private String aEmail, aPassword;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "LoginPActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loginButton = findViewById(R.id.loginButton);


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            updateUI(firebaseAuth.getCurrentUser());

        }

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT);
                Log.println(Log.INFO,"test","works");
                aEmail = email.getText().toString();
                aPassword= password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(aEmail, aPassword)
                        .addOnCompleteListener(LoginP.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });
    }


    public void updateUI(FirebaseUser firebaseUser){

        startActivity(new Intent(this,Home.class));
        Log.println(Log.INFO,"Test",firebaseUser.getDisplayName());
    }

    public void SignUpPressed(View view) {
        startActivity(new Intent(this,CreateAccount.class));
    }
}