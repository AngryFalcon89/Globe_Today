package com.example.globetoday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    TabLayout tabLayout;
    TabItem headlines,tech,entertainment,health,science,sports;
    FragmentAdapter fragmentAdapter;
    Toolbar toolbar;
    String API = "6908bb4b71ae46fc926253f68c890aec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        headlines=findViewById(R.id.headlines);
        tech=findViewById(R.id.tech);
        entertainment=findViewById(R.id.entertainment);
        health=findViewById(R.id.health);
        science=findViewById(R.id.science);
        sports=findViewById(R.id.sports);

        ViewPager viewPager=findViewById(R.id.fragmentcontainer);
        tabLayout = findViewById(R.id.tabBar);

        Log.e(TAG, "onCreate: viewPager created",null );
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(fragmentAdapter);

        Log.e(TAG, "onCreate: viewPager Adapter set", null);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||tab.getPosition()==3||tab.getPosition()==4||tab.getPosition()==5||tab.getPosition()==6){
                    fragmentAdapter.notifyDataSetChanged();
                    Log.e(TAG, "onTabSelected: ended ", null);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}