package com.example.lab07;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText edUsername, edPassword, edConfirmPassword;
    private Button btnCreateUser;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        edConfirmPassword = findViewById(R.id.ed_confirm_pwd);
        btnCreateUser = findViewById(R.id.btn_create_user);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = edUsername.getText().toString().trim();
                String strPassword = edPassword.getText().toString().trim();
                String strConfirmPassword = edConfirmPassword.getText().toString().trim();

                if (strUsername.isEmpty() || strPassword.isEmpty() || strConfirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!strPassword.equals(strConfirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = credentials.edit();
                editor.putString("Username", strUsername);
                editor.putString("Password", strPassword);
                editor.apply(); // Сохраняем данные

                Toast.makeText(SignUpActivity.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                finish(); // Закрываем активити
            }
        });
    }
}
