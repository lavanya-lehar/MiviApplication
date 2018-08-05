package com.miviandroidtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SubscriptionActivity extends AppCompatActivity {

    private TextView txtDataBalance, txtExpiryDate, txtAutoRenewal, txtPrimarySubscription;
    private String dataBalance, expiryDate;
    private boolean autoRenewal, primarySubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        getBundleData();
        initComponents();
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dataBalance = bundle.getString("dataBalance");
            expiryDate = bundle.getString("expiryDate");
            autoRenewal = bundle.getBoolean("autoRenewal");
            primarySubscription = bundle.getBoolean("primarySubscription");
        }
    }

    private void initComponents() {
        txtDataBalance = findViewById(R.id.txtDataBalance);
        txtExpiryDate = findViewById(R.id.txtExpiryDate);
        txtAutoRenewal = findViewById(R.id.txtAutoRenewal);
        txtPrimarySubscription = findViewById(R.id.txtPrimarySubscription);
        setData();
    }

    private void setData() {
        txtDataBalance.setText(dataBalance);
        txtExpiryDate.setText(expiryDate);
        txtAutoRenewal.setText(autoRenewal ? "true" : "false");
        txtPrimarySubscription.setText(primarySubscription ? "true" : "false");
    }
}
