package com.miviandroidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edtEmail, edtPassword;
    private TextView txtLogin;
    private String email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
    }

    private void initComponents() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        txtLogin = findViewById(R.id.txtLogin);
        setClickListeners();
    }

    private void setClickListeners() {
        txtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        validateLogin();
    }

    private void validateLogin() {
        if (edtEmail.getText() != null)
            email = edtEmail.getText().toString().trim();
        if (edtPassword.getText() != null)
            password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, R.string.please_enter_email_address, Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, R.string.please_enter_valid_email_address, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.please_enter_password, Toast.LENGTH_SHORT).show();
        } else if (password.length() <= 5) {
            Toast.makeText(this, R.string.pwd_should_be_min_6_char, Toast.LENGTH_SHORT).show();
        } else if (!email.equals(AppConstants.email)) {
            Toast.makeText(this, R.string.email_not_registered_with_us, Toast.LENGTH_SHORT).show();
        } else {
            continueWithUserLogin();
        }
    }

    private void continueWithUserLogin() {
        startActivity(new Intent(this, UserDashBoardActivity.class));
        finish();
    }
}
