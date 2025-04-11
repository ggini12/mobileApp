package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class CountrySelectActivity extends AppCompatActivity {

    private void setCountryClickListener(int viewId, final String countryName) {
        LinearLayout countryLayout = findViewById(viewId);
        countryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3페이지(여행 팁 화면)으로 이동
                Intent intent = new Intent(CountrySelectActivity.this, CountryTipsActivity.class);
                intent.putExtra("country", countryName);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_select);

        setCountryClickListener(R.id.usa, "USA");
        setCountryClickListener(R.id.japan, "Japan");
        setCountryClickListener(R.id.france, "France");
        setCountryClickListener(R.id.italy, "Italy");
        setCountryClickListener(R.id.thailand, "Thailand");
        setCountryClickListener(R.id.uk, "UK");
        setCountryClickListener(R.id.vietnam, "Vietnam");
        setCountryClickListener(R.id.australia, "Australia");
    }
}
