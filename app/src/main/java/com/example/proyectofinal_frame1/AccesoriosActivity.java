package com.example.proyectofinal_frame1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.example.proyectofinal_frame1.ui.home.HomeFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AccesoriosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios);

        // funcionalidad bottom nav menu
        /*
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(AccesoriosActivity.this, HomeFragment.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_dashboard:
                        Intent intent2 = new Intent(AccesoriosActivity.this, DashboardFragment.class);
                        startActivity(intent2);
                        return true;
                    case R.id.carruselFragment:
                        Intent intent3 = new Intent(AccesoriosActivity.this, CarruselFragment.class);
                        startActivity(intent3);
                        return true;
                    case R.id.navigation_notifications:
                        Intent intent4 = new Intent(AccesoriosActivity.this, NotificationsFragment.class);
                        startActivity(intent4);
                        return true;
                }
                return false;
            }
        });

         */

    }


    public void toPulseras(View view) {
        Intent intent = new Intent(AccesoriosActivity.this, AccesoriosPulseras.class);
        startActivity(intent);
    }

}