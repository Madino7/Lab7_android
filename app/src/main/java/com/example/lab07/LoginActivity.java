package com.example.lab07;

import android.content.Context;
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

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
                String savedUsername = credentials.getString("Username", "");
                String savedPassword = credentials.getString("Password", "");

                String inputUsername = edUsername.getText().toString().trim();
                String inputPassword = edPassword.getText().toString().trim();

                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Введите имя и пароль!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (savedUsername.isEmpty() || savedPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Пользователь не зарегистрирован!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (savedUsername.equals(inputUsername) && savedPassword.equals(inputPassword)) {
                    Toast.makeText(LoginActivity.this, "Вход успешен!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Неверный логин или пароль!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
