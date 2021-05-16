package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthy.activities.CreateAccount;
import com.example.healthy.activities.Home;
import com.example.healthy.activities.LoginP;
import com.example.healthy.navigation.BaseNav;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends BaseNav {
    private EditText email;
    private EditText password;
    private Button loginButton;
    private String aEmail;
    private String aPassword;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "LoginPActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                if ( email.getText().toString().isEmpty() ||  password.getText().toString().isEmpty()){
                    Context context =getApplicationContext();
                    Toast toast = Toast.makeText(context,"Email or Password fields are empty",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                aEmail = email.getText().toString();
                aPassword= password.getText().toString();


                firebaseAuth.signInWithEmailAndPassword(aEmail, aPassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
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

    }

    public void SignUpPressed(View view) {
        startActivity(new Intent(this,CreateAccount.class));
    }
}