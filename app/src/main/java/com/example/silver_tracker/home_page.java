package com.example.silver_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class home_page extends AppCompatActivity {

    NavigationView nav_view;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerlayout;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
     //   getSupportActionBar().hide();
           toolbar = findViewById(R.id.toolbar);
           setSupportActionBar(toolbar);

            nav_view= findViewById(R.id.nav_bar);
            drawerlayout= findViewById(R.id.main_ui);
            toggle= new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close);
            drawerlayout.addDrawerListener(toggle);
            toggle.syncState();

            nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.home) {
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();

                    } else if (id == R.id.profile) {
                        Toast.makeText(getApplicationContext(),"Your Profile",Toast.LENGTH_SHORT).show();

                    } else if (id == R.id.budget) {
                        Toast.makeText(getApplicationContext(),"Your Budget",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(home_page.this, budget.class);
                        startActivity(intent);

                    } else if (id == R.id.today) {
                        Toast.makeText(getApplicationContext(),"Today",Toast.LENGTH_SHORT).show();

                    } else if (id == R.id.week) {
                        Toast.makeText(getApplicationContext(),"This Week",Toast.LENGTH_SHORT).show();
                    } else if (id == R.id.month){
                        Toast.makeText(getApplicationContext(),"This Month",Toast.LENGTH_SHORT).show();
                    }
                    else if(id== R.id.analytics){
                        Toast.makeText(getApplicationContext(),"Analytics",Toast.LENGTH_SHORT).show();
                    }
                    else if(id== R.id.history){
                        Toast.makeText(getApplicationContext(),"History",Toast.LENGTH_SHORT).show();
                    }
                     else if(id== R.id.about){
                        Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_SHORT).show();
                    }
                      else {
                        Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();
                    }
                      drawerlayout.closeDrawer(GravityCompat.START);
                        return true;


                }
            });


    }
}