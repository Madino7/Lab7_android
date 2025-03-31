package com.example.lab07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button btnLogout, btnHistory;
    private static final String PREF_NAME = "user_credentials";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeText = findViewById(R.id.welcome_text);
        btnLogout = findViewById(R.id.btn_logout);
        btnHistory = findViewById(R.id.btn_history);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String username = prefs.getString("Username", "User");
        welcomeText.setText("Welcome, " + username + "!");

        btnLogout.setOnClickListener(view -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // Очищаем данные пользователя
            editor.apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnHistory.setOnClickListener(view -> 
            startActivity(new Intent(this, LoginHistoryActivity.class))
        );
    }
}
