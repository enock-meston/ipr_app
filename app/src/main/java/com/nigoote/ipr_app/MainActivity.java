package com.nigoote.ipr_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView newAccount;
    EditText phoneNumber,password;
    Button loginBtn;
    String URL = Constant.host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constant constant = new Constant(MainActivity.this,"Message");
        newAccount = (TextView) findViewById(R.id.txtNew);
        loginBtn = (Button) findViewById(R.id.txtLogin);
        phoneNumber = (EditText) findViewById(R.id.edt_phone);
        password = (EditText) findViewById(R.id.edt_passward);

//        new account
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewAccountActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneN = phoneNumber.getText().toString();
                String passW = password.getText().toString();

                if (TextUtils.isEmpty(phoneN) || TextUtils.isEmpty(passW)){
                    constant.openDialog("Please All Fields Are Required !");
                }else{

                    loginMethod(phoneN,passW);
                }
            }
        });
    }

    private void loginMethod(String phoneN, String passW) {

    }
}