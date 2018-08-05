package com.miviandroidtest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class UserDashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtUserName, txtDataBalance, txtExpiryDate, txtProductName, txtProductPrice,
            txtSubscription, txtSubDataBalance, txtSubExpiryDate, txtProducts, txtProName, txtProPrice;
    private ImageView imgSubscription, imgProducts;
    private String title, firstName, lastName, dataBalance, expiryDate, productName, productPrice;
    private boolean autoRenewal, primarySubscription, unlimitedText, unlimitedTalk,
            unlimitedInternationalText, unlimitedInternationalTalk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentTransitions();
        setContentView(R.layout.activity_user_dashboard);
        initComponents();
    }

    private void setContentTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
    }

    private void initComponents() {
        txtUserName = findViewById(R.id.txtUserName);
        txtDataBalance = findViewById(R.id.txtDataBalance);
        txtExpiryDate = findViewById(R.id.txtExpiryDate);
        txtProductName = findViewById(R.id.txtProductName);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        imgSubscription = findViewById(R.id.imgSubscription);
        imgProducts = findViewById(R.id.imgProducts);

        txtSubscription = findViewById(R.id.txtSubscription);
        txtSubDataBalance = findViewById(R.id.txtSubDataBalance);
        txtSubExpiryDate = findViewById(R.id.txtSubExpiryDate);
        txtProducts = findViewById(R.id.txtProducts);
        txtProName = findViewById(R.id.txtProName);
        txtProPrice = findViewById(R.id.txtProPrice);

        setClickListeners();
        parseCollectionsData();
    }

    private void setClickListeners() {
        imgSubscription.setOnClickListener(this);
        imgProducts.setOnClickListener(this);
    }

    private void parseCollectionsData() {
        String response = loadCollection();
        try {
            JSONObject jsonObject = new JSONObject(response);
            title = jsonObject.getJSONObject("data").getJSONObject("attributes").getString("title");
            firstName = jsonObject.getJSONObject("data").getJSONObject("attributes").getString("first-name");
            lastName = jsonObject.getJSONObject("data").getJSONObject("attributes").getString("last-name");
            JSONArray jsonArray = jsonObject.getJSONArray("included");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonIncObj = jsonArray.getJSONObject(i);
                if (jsonIncObj.getString("type").equalsIgnoreCase(AppConstants.SUBSCRIPTIONS)) {
                    dataBalance = jsonIncObj.getJSONObject("attributes").getString("included-data-balance");
                    expiryDate = jsonIncObj.getJSONObject("attributes").getString("expiry-date");
                    autoRenewal = jsonIncObj.getJSONObject("attributes").getBoolean("auto-renewal");
                    primarySubscription = jsonIncObj.getJSONObject("attributes").getBoolean("primary-subscription");
                } else if (jsonIncObj.getString("type").equalsIgnoreCase(AppConstants.PRODUCTS)) {
                    productName = jsonIncObj.getJSONObject("attributes").getString("name");
                    productPrice = jsonIncObj.getJSONObject("attributes").getString("price");
                    unlimitedText = jsonIncObj.getJSONObject("attributes").getBoolean("unlimited-text");
                    unlimitedTalk = jsonIncObj.getJSONObject("attributes").getBoolean("unlimited-talk");
                    unlimitedInternationalText = jsonIncObj.getJSONObject("attributes").getBoolean("unlimited-international-text");
                    unlimitedInternationalTalk = jsonIncObj.getJSONObject("attributes").getBoolean("unlimited-international-talk");
                }
            }
            setData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        txtUserName.setText(title.concat(" ").concat(firstName).concat(" ").concat(lastName));
        txtDataBalance.setText(dataBalance);
        txtExpiryDate.setText(expiryDate);
        txtProductName.setText(productName);
        txtProductPrice.setText(productPrice);
    }

    private String loadCollection() {
        String json = null;
        try {
            InputStream is = getAssets().open("collections.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSubscription:
                navigateToSubscriptionActivity();
                break;
            case R.id.imgProducts:
                navigateToProductsActivity();
                break;
            default:
                break;
        }
    }

    private void navigateToSubscriptionActivity() {
        Intent intent = new Intent(this, SubscriptionActivity.class);
        intent.putExtra("dataBalance", dataBalance);
        intent.putExtra("expiryDate", expiryDate);
        intent.putExtra("autoRenewal", autoRenewal);
        intent.putExtra("primarySubscription", primarySubscription);
        Pair<View, String> p1 = Pair.create((View) txtDataBalance, getString(R.string.trans_data_balance));
        Pair<View, String> p2 = Pair.create((View) txtExpiryDate, getString(R.string.trans_expiry_date));
        Pair<View, String> p3 = Pair.create((View) txtSubscription, getString(R.string.trans_subscription));
        Pair<View, String> p4 = Pair.create((View) txtSubDataBalance, getString(R.string.trans_txt_data_balance));
        Pair<View, String> p5 = Pair.create((View) txtSubExpiryDate, getString(R.string.trans_txt_expiry_date));
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p2, p3, p4, p5);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    private void navigateToProductsActivity() {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra("productName", productName);
        intent.putExtra("productPrice", productPrice);
        intent.putExtra("unlimitedText", unlimitedText);
        intent.putExtra("unlimitedTalk", unlimitedTalk);
        intent.putExtra("unlimitedInternationalText", unlimitedInternationalText);
        intent.putExtra("unlimitedInternationalTalk", unlimitedInternationalTalk);
        Pair<View, String> p1 = Pair.create((View) txtProductName, getString(R.string.trans_pro_name));
        Pair<View, String> p2 = Pair.create((View) txtProductPrice, getString(R.string.trans_pro_price));
        Pair<View, String> p3 = Pair.create((View) txtProducts, getString(R.string.trans_products));
        Pair<View, String> p4 = Pair.create((View) txtProName, getString(R.string.trans_txt_pro_name));
        Pair<View, String> p5 = Pair.create((View) txtProPrice, getString(R.string.trans_txt_pro_price));
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p2, p3, p4, p5);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
