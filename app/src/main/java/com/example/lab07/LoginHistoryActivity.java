package com.example.lab07;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoginHistoryActivity extends AppCompatActivity {
    private TextView historyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);

        historyText = findViewById(R.id.history_text);
        historyText.setText(readLoginHistory());
    }

    private String readLoginHistory() {
        StringBuilder history = new StringBuilder();
        try {
            FileInputStream fis = openFileInput("logins.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                history.append(line).append("\n");
            }
            reader.close();
        } catch (Exception e) {
            history.append("No login history found.");
        }
        return history.toString();
    }
}
