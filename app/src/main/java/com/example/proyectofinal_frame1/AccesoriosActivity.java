package com.example.proyectofinal_frame1;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.proyectofinal_frame1.databinding.ActivityAccesoriosBinding;
import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.example.proyectofinal_frame1.ui.home.HomeFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class AccesoriosActivity extends AppCompatActivity {

    private ActivityAccesoriosBinding binding;
    MainActivity main = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios);

        binding = ActivityAccesoriosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // funcionalidad bottom_nav_menu
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // main.getNavView().setSelectedItemId(R.id.nav_host_fragment_activity_main);
        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
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


        /*
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_home, R.id.navigation_dashboard, R.id.armarConjuntosFragment,
        R.id.accesoriosLayout)
        .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(main.getBinding().navView, navController);
         */

    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.accesoriosLayout, fragment); // creo que el fallo está aquí
        // si se pone R.id.accesoriosLayout se ven los activity pero sin el toolbar
        fragmentTransaction.commit();
    }


    public void toPulseras(View view) {
        Intent intent = new Intent(AccesoriosActivity.this, AccesoriosPulseras.class);
        startActivity(intent);
    }

}