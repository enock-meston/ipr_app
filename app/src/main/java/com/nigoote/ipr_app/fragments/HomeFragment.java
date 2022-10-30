package com.nigoote.ipr_app.fragments;

import static com.nigoote.ipr_app.MainActivity.SHARED_PREFERENCES_NAME;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nigoote.ipr_app.Constant;
import com.nigoote.ipr_app.HomeActivity;
import com.nigoote.ipr_app.MainActivity;
import com.nigoote.ipr_app.R;
import com.nigoote.ipr_app.ViewQuranActivity;
import com.nigoote.ipr_app.ViewSawmActivity;
import com.nigoote.ipr_app.Zakat_IturoActivity;

import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    SharedPreferences sharedPreferences;
    Button hajjBtn,zakatBtn,shadahBtn,sawmBnt;
    Constant constant;
    String URL = Constant.host;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        hajjBtn = (Button) view.findViewById(R.id.hajjBtn);
        zakatBtn = (Button) view.findViewById(R.id.zakatBtn);
        shadahBtn = (Button) view.findViewById(R.id.shadahBtn);
        sawmBnt = (Button) view.findViewById(R.id.sawmBnt);




        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String str_id = sharedPreferences.getString("id", "");
//        go to sawmBnt
        sawmBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewSawmActivity.class);
                startActivity(intent);
            }
        });
//        go to ready shadahBtn
        shadahBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewQuranActivity.class);
                startActivity(intent);
            }
        });
//        go to zakat(ituro) Activity
        zakatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Zakat_IturoActivity.class);
                startActivity(intent);
            }
        });
//       apply or register for hajjBtn
        hajjBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyButton(str_id);
            }
        });

        return  view;
    }

    private void applyButton(String str_id) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Apply For Hajj");
        progressDialog.show();
        String url1 = URL+"/apply.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Constant constant = new Constant(getActivity(),"Message");
        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully_Registered")){
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
                param.put("id",str_id);

                return param;
            }
        };
        requestQueue.add(request);
    }
}