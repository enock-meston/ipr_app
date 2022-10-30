package com.nigoote.ipr_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NewAccountActivity extends AppCompatActivity {

    EditText names,phone,location,password;
    Button saveBtn;
    Constant constant;
    String URL = Constant.host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        names = (EditText) findViewById(R.id.new_edt_names);
        phone = (EditText) findViewById(R.id.new_edt_phone);
        location = (EditText) findViewById(R.id.new_edt_location);
        password = (EditText) findViewById(R.id.new_edt_passward);
        saveBtn = (Button) findViewById(R.id.new_txtLogin);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtNames = names.getText().toString();
                String txtPhone = phone.getText().toString();
                String txtLocation = location.getText().toString();
                String txtPassword = password.getText().toString();

                constant = new Constant(NewAccountActivity.this,"Message");
                if (TextUtils.isEmpty(txtNames) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtLocation) ||
                        TextUtils.isEmpty(txtPassword)){
                    constant.openDialog("All Fields are Required!");
                }else{
                    createNewAccountMethod(txtNames,txtPhone,txtLocation,txtPassword);
                }
            }
        });
    }

    private void createNewAccountMethod(String txtNames, String txtPhone, String txtLocation, String txtPassword) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("New Registration");
        progressDialog.show();
        String url1 = URL+"/newAccount.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully_Registered")){
                    Log.d("enock",response+"working");
                    progressDialog.dismiss();
                    constant.openDialog(response);
                    clearEditText();
                }else{
                    progressDialog.dismiss();
                    constant.openDialog(response);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                constant.openDialog(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("names",txtNames);
                param.put("phone",txtPhone);
                param.put("location",txtLocation);
                param.put("password",txtPassword);
                return param;
            }
        };
        requestQueue.add(request);
    }

    public  void clearEditText(){
        names.setText("");
        phone.setText("");
        location.setText("");
        password.setText("");
    }

}