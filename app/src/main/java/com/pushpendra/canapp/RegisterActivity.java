package com.pushpendra.canapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {
    private EditText registerEmail, registerPassword;
    private TextView registerButton, loginButton, cnt_with_google;
    private FirebaseAuth mAuth;
    private static final String TAG = "GOOGLEAUTH";
    private static final int RC_SIGN_IN = 1;
    GoogleSignInClient nGoogleSingInClient;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = findViewById(R.id.register_email_input);
        registerPassword = findViewById(R.id.register_password_input);
        registerButton = findViewById(R.id.register_regist_textview);
        loginButton = findViewById(R.id.register_login_textview);
        cnt_with_google = findViewById(R.id.register_continue_with_google);

        mAuth = FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        //GOOGLE BUTTON CLICK(continue with google)
        GoogleSignInOptions option = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        nGoogleSingInClient = GoogleSignIn.getClient(this,option);

        //Getting the button Class
        cnt_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = nGoogleSingInClient.getSignInIntent();
                startActivityForResult(i,1234);
            }
        });
    }

    private void createUser(){
        String remail = registerEmail.getText().toString();
        String rpassword = registerPassword.getText().toString();

        if(TextUtils.isEmpty(remail)){
            registerEmail.setError("Email cannot be empty");
            registerEmail.requestFocus();
        }else if(TextUtils.isEmpty(rpassword)){
            registerPassword.setError("Password cannot be empty");
            registerPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User registered succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registeration Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error",task.getException().getMessage());
                    }
                }
            });
        }
    }

    //GMAIL LOGIN---------------------------------------------------------------------------------------
    private void signIn() {
        Intent signInIntent = nGoogleSingInClient.getSignInIntent();
        startActivityIfNeeded(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Signed in succesfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

}