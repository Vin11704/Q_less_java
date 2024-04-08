package com.example.q_less_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

EditText textEmailAddress ;
EditText textPassword ;

Button loginButton ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEmailAddress =  findViewById(R.id. registration_email);
        textPassword  = findViewById(R.id.registration_password) ;
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if( textEmailAddress.getText().toString().equals("user")&& textPassword.getText().toString().equals("1234")) {
                    Toast.makeText(MainActivity.this,"login successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"login failed", Toast.LENGTH_SHORT).show();
                }
                }



        });
    }
}

