package com.nigoote.ipr_app.fragments;

import static com.nigoote.ipr_app.MainActivity.SHARED_PREFERENCES_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nigoote.ipr_app.R;

public class ProfileFragment extends Fragment {
    SharedPreferences sharedPreferences;
    TextView names,phone,location;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String str_name = sharedPreferences.getString("names", "");
        String str_phone = sharedPreferences.getString("phone", "");
        String str_location = sharedPreferences.getString("location", "");
        names = view.findViewById(R.id.txt_names);
        phone = view.findViewById(R.id.txt_phone1);
        location = view.findViewById(R.id.txt_location);


        names.setText("Names : "+str_name);
        phone.setText("Phone Number : "+str_phone);
        location.setText("Location : "+str_location);


        return view;
    }
}