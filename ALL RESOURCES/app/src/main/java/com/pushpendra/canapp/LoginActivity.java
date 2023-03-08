package com.pushpendra.canapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private TextView registerButton, loginButton;

    private static final String TAG = "GOOGLEAUTH";
    private static final int RC_SIGN_IN = 1;
    GoogleSignInClient nGoogleSingInClient;
    private FirebaseAuth mAuth;

    private boolean oneTapClient = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.login_email_input);
        loginPassword = findViewById(R.id.login_password_input);
        registerButton = findViewById(R.id.login_register_textview);
        loginButton = findViewById(R.id.login_login_textview);
        mAuth = FirebaseAuth.getInstance();

        //LOGIN Button Click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        //---------------REGISTER TEXTVIW CLICK------------------------->
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }


    //--------------------------------------------------------EMAIL PASSWORD LOGIN--------------------------------------------------->
    private void loginUser() {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            loginEmail.setError("Email cannot be empty");
            loginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Log in Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
    }
}