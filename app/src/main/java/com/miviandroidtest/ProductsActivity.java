package com.miviandroidtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ProductsActivity extends AppCompatActivity {

    private TextView txtProductName, txtProductPrice, txtUnLtdText, txtUnLtdTalk, txtUnLtdIntText, txtUnLtdIntTalk;
    private String productName, productPrice;
    private boolean unlimitedText, unlimitedTalk, unlimitedInternationalText, unlimitedInternationalTalk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getBundleData();
        initComponents();
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            productName = bundle.getString("productName");
            productPrice = bundle.getString("productPrice");
            unlimitedText = bundle.getBoolean("unlimitedText");
            unlimitedTalk = bundle.getBoolean("unlimitedTalk");
            unlimitedInternationalText = bundle.getBoolean("unlimitedInternationalText");
            unlimitedInternationalTalk = bundle.getBoolean("unlimitedInternationalTalk");
        }
    }

    private void initComponents() {
        txtProductName = findViewById(R.id.txtProductName);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        txtUnLtdText = findViewById(R.id.txtUnLtdText);
        txtUnLtdTalk = findViewById(R.id.txtUnLtdTalk);
        txtUnLtdIntText = findViewById(R.id.txtUnLtdIntText);
        txtUnLtdIntTalk = findViewById(R.id.txtUnLtdIntTalk);
        setData();
    }

    private void setData() {
        txtProductName.setText(productName);
        txtProductPrice.setText(productPrice);
        txtUnLtdText.setText(unlimitedText ? "true" : "false");
        txtUnLtdTalk.setText(unlimitedTalk ? "true" : "false");
        txtUnLtdIntText.setText(unlimitedInternationalText ? "true" : "false");
        txtUnLtdIntTalk.setText(unlimitedInternationalTalk ? "true" : "false");
    }
}
