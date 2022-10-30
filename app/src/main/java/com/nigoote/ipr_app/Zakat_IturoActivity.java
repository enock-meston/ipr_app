package com.nigoote.ipr_app;

import static com.nigoote.ipr_app.MainActivity.SHARED_PREFERENCES_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Zakat_IturoActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button PayZakatBtn;
    EditText zakatAmount,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_ituro);


        phone = (EditText) findViewById(R.id.PhonezakatTxt);
        zakatAmount = (EditText) findViewById(R.id.zakatTxt);
        PayZakatBtn = (Button) findViewById(R.id.PayZakatBtn);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //display back icon

        sharedPreferences = this.getApplication().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String str_phone = sharedPreferences.getString("phone", "");

        phone.setText(str_phone);
        PayZakatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtAmount = zakatAmount.getText().toString();
                String txtPhone = phone.getText().toString();
                 payZakatMethod(txtAmount,txtPhone);
            }
        });
    }

    private void payZakatMethod(String txtAmount, String txtPhone) {

    }
}