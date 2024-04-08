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
                if (!validateEmail() | validatePassword()) {

            }else{
                    checkuser();
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

    public Boolean validateEmail(){
        String val = loginEmail.getText().toString();
        if(val.isEmpty()) {
            loginEmail.setError("Access denied:Email can't be empty)");
            return false;
        }else {
            loginEmail.setError(null);
            return true;
        }
        }


    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if(val.isEmpty()) {
            loginPassword.setError("Privacy is important!password cannot be empty!");
            return false;
        }else {
            loginPassword.setError(null);
            return true;
        }
    }


    public void checkuser(){
    String userEmail = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginEmail.setError(null);
                    String passwordfromDB = snapshot.child(userEmail).child("Password").getValue(String.class);
                    if (!Objects.equals(passwordfromDB, userPassword)) {
                        loginEmail.setError(null);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                    } else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginEmail.setError("User does not Exist");
                    loginEmail.requestFocus();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });

    }
}





