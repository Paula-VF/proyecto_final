package com.example.proyectofinal_frame1.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal_frame1.AccesoriosActivity;
import com.example.proyectofinal_frame1.CarruselFragment;
import com.example.proyectofinal_frame1.MainActivity;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.databinding.FragmentNotificationsBinding;
import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.example.proyectofinal_frame1.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    Context context;

    private TextView createdBy;
    private ImageView logo;
    private ImageView nombre;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        context = container.getContext();

        createdBy = (TextView) root.findViewById(R.id.created_by);
        logo = (ImageView) root.findViewById(R.id.logo);
        nombre = (ImageView) root.findViewById(R.id.nombre);

        /*
        // funcionalidad bottom_nav_menu
        BottomNavigationView navView = root.findViewById(R.id.nav_view);
        //navView.setSelectedItemId(R.id.nav_host_fragment_activity_main);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(context, MainActivity.class));
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

         */

        /*
        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        */
        return root;
    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.accesoriosLayout, fragment); // creo que el fallo está aquí
        // si se pone R.id.accesoriosLayout se ven los activity pero sin el toolbar
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}