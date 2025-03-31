package com.example.lab07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername, edPassword;
    private Button btnLogin, btnSignUp;
    private static final String PREF_NAME = "user_credentials";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);

        btnLogin.setOnClickListener(view -> {
            SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            String savedUser = prefs.getString("Username", "");
            String savedPass = prefs.getString("Password", "");

            if (savedUser.equals(edUsername.getText().toString()) &&
                savedPass.equals(edPassword.getText().toString())) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignUp.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }
}
