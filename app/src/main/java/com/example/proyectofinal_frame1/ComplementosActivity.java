package com.example.proyectofinal_frame1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ComplementosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complementos);

        // funcionalidad bottom nav menu
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_home:
                        startActivity(new Intent(ComplementosActivity.this, MainActivity.class));
                        return true;
                    case R.id.navigation_dashboard:
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.add(R.id.complemLayout, new DashboardFragment());
                        ft.commit();
                        return true;
                    case R.id.carruselFragment:
                        finish();
                        startActivity(new Intent(ComplementosActivity.this, CarruselFragment.class));
                        return true;
                    case R.id.navigation_notifications:
                        finish();
                        startActivity(new Intent(ComplementosActivity.this, NotificationsFragment.class));
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}