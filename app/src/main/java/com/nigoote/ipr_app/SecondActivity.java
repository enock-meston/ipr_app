package com.nigoote.ipr_app;

import static com.nigoote.ipr_app.MainActivity.SHARED_PREFERENCES_NAME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView saTitle;
    Button sendBtn;
    String URL = Constant.host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        saTitle = (TextView) findViewById(R.id.saTitle);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        saTitle.setText(getIntent().getExtras().getString("name"));

        sharedPreferences = SecondActivity.this.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String str_name = sharedPreferences.getString("names", "");
        String title = getIntent().getExtras().getString("name");

//        send
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMethod(str_name,title);
            }
        });

    }



    private void sendMethod(String str_name, String title) {
        ProgressDialog progressDialog = new ProgressDialog(this );
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Apply For Hajj");
        progressDialog.show();
        String url1 = URL+"/apply_zan.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Constant constant = new Constant(SecondActivity.this,"Message");
        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully_Sent")){
                    Log.d("enock",response+"working");
                    progressDialog.dismiss();
                    constant.openDialog(response);
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
                param.put("names",str_name);
                param.put("title",title);

                return param;
            }
        };
        requestQueue.add(request);
    }
}