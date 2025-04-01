package com.example.lab07;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;

public class LoginHistoryActivity extends AppCompatActivity {
    private TextView historyText;
    private static final String PREF_NAME = "user_credentials";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);

        historyText = findViewById(R.id.history_text);
        historyText.setText(readLoginHistory());
    }

    private String readLoginHistory() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        long lastLoginTime = prefs.getLong("LastLoginTime", -1);
        String lastLoginUser = prefs.getString("LastLoginUser", "No user");

        if (lastLoginTime == -1) {
            return "No login history found.";
        } else {
            return "Last login:\nUser: " + lastLoginUser + "\nTime: " + lastLoginTime;
        }
    }
}
