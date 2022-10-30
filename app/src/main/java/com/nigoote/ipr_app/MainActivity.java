package com.nigoote.ipr_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView newAccount;
    EditText phoneNumber,password;
    Button loginBtn;
    String URL = Constant.host;
    public static final String SHARED_PREFERENCES_NAME = "login_portal";
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
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Entering...");
        progressDialog.show();

        Constant constant = new Constant(MainActivity.this,"Message");
        String url2 = URL+"login.php";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resp = new JSONObject(response);
                    String res = resp.getString("status");
                    String user_id = resp.optString("id");
                    String names = resp.optString("names");
                    String phone = resp.optString("phone");
                    String location = resp.optString("location");
                    if(res.contains("Login_Success")){
                        progressDialog.dismiss();

                        if (res.equals("Login_Success")){
                            progressDialog.dismiss();
                            Log.d("LogResp", names);
                            //shared pref
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id", user_id);
                            editor.putString("names", names);
                            editor.putString("phone", phone);
                            editor.putString("location", location);
                            editor.commit();

                            Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            finish();
                            startActivity(intent);
                            // clean data in EditText
                            constant.cleanData(phoneNumber,password);
                        }
                    }else if(res.contains("Wrong_phone")){
                        progressDialog.dismiss();
                        constant.openDialog("Wrong phone");
                    }else if(res.contains("Wrong_Password")){
                        progressDialog.dismiss();
                        constant.openDialog("Wrong Password...");
                    }else{
                        progressDialog.dismiss();
                        constant.openDialog("Connection_Error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    constant.openDialog("error 1"+e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Constant constant = new Constant(MainActivity.this,"Error from Volley");
                constant.openDialog("error 1"+ error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("phone",phoneN);
                param.put("password",passW);
                return param;
            }
        };
        //        add request to requestQueue
        requestQueue.add(request);
    }
}