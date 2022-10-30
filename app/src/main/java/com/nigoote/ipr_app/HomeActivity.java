package com.nigoote.ipr_app;

import static com.nigoote.ipr_app.MainActivity.SHARED_PREFERENCES_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nigoote.ipr_app.fragments.ActionFragment;
import com.nigoote.ipr_app.fragments.HomeFragment;
import com.nigoote.ipr_app.fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).addToBackStack(null).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        shared pref data
                    sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                    String str_name = sharedPreferences.getString("names", "");
                    String str_userid = sharedPreferences.getString("id", "");
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new HomeFragment().newInstance(str_name,str_userid)).addToBackStack(null).commit();
                            break;

                        case R.id.nav_Action:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new ActionFragment().newInstance(str_name,str_userid)).addToBackStack(null).commit();
                            break;
                    }
                    return true;
                }

            };

    //    optionMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu,menu);
        return true;
    }

//    onOptionClicks

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

//    end of OptionMenu

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0){
            super.onBackPressed();
        }else {
            getSupportFragmentManager().popBackStack();
        }
    }
}