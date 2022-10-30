package com.nigoote.ipr_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.nigoote.ipr_app.R;
import com.nigoote.ipr_app.SecondActivity;

public class ActionFragment extends Fragment {

    ListView listView;
    Context context;
    String[] salahTitle = new String[]{"Fajr => 04:28","Sun Rise => 05:39","Dhuhr => 11:44",
            "Asr => 15:03","Maglrib => 17:49","Isha => 18:56"};
//    String[] time = new String[]{"","","","","",""};

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ActionFragment() {
        // Required empty public constructor
    }


    public static ActionFragment newInstance(String param1, String param2) {
        ActionFragment fragment = new ActionFragment();
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
        View view = inflater.inflate(R.layout.fragment_action, container, false);
        listView = view.findViewById(R.id.list_itemId);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,salahTitle);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str= listView.getAdapter().getItem(position).toString();
                Intent intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("name",str);
                startActivity(intent);
            }
        });
        return view;

    }
}