package com.example.proyectofinal_frame1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.example.proyectofinal_frame1.ui.home.HomeFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ComplementosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complementos);

        // funcionalidad bottom_nav_menu
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //navView.setSelectedItemId(R.id.nav_host_fragment_activity_main);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.navigation_dashboard:
                        replaceFragment(new DashboardFragment());
                        break;
                    case R.id.armarConjuntosFragment:
                        replaceFragment(new CarruselFragment());
                        break;
                    case R.id.navigation_notifications:
                        replaceFragment(new NotificationsFragment());
                        break;
                }
                return true;
            }
        });
    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.accesoriosLayout, fragment); // creo que el fallo está aquí
        // si se pone R.id.accesoriosLayout se ven los activity pero sin el toolbar
        fragmentTransaction.commit();
    }


}