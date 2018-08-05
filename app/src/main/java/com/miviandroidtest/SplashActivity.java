package com.miviandroidtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showSplashScreenAndNavigate();
    }

    private void showSplashScreenAndNavigate() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToLoginScreen();
            }
        }, 1000);
    }

    private void navigateToLoginScreen() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
