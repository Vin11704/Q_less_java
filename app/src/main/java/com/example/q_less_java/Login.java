package com.example.q_less_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.registration_email);
        loginPassword = findViewById(R.id.registration_password);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() || !validatePassword()) {
                    // Do nothing if validation fails
                } else {
                    loginUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateEmail() {
        String val = loginEmail.getText().toString();
        if (val.isEmpty()) {
            loginEmail.setError("Access denied: Email can't be empty");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Privacy is important! Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void loginUser() {
        String userEmail = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // Login successful, navigate to welcome page
                        Intent intent = new Intent(Login.this, welcome_page.class);
                        startActivity(intent);
                        finish(); // Close the login activity to prevent user from navigating back
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Login failed, display error message
                        Toast.makeText(Login.this, "Login failed. " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
