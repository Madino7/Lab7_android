package com.example.lab07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername, edPassword;
    private Button btnLogin, btnSignUp;
    private static final String PREF_NAME = "user_credentials";
    private static final String FILE_NAME = "logins.txt";

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

            String enteredUser = edUsername.getText().toString().trim();
            String enteredPass = edPassword.getText().toString().trim();

            if (enteredUser.isEmpty() || enteredPass.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!savedUser.equals(enteredUser) || !savedPass.equals(enteredPass)) {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                return;
            }

            saveLoginToFile(enteredUser);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        });

        btnSignUp.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }

    private void saveLoginToFile(String username) {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
            String logEntry = username + " logged in\n";
            fos.write(logEntry.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
