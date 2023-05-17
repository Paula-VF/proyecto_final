package com.example.proyectofinal_frame1.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_frame1.AccesoriosActivity;
import com.example.proyectofinal_frame1.CarruselFragment;
import com.example.proyectofinal_frame1.MainActivity;
import com.example.proyectofinal_frame1.Prenda;
import com.example.proyectofinal_frame1.PrendaAdapter;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.databinding.FragmentDashboardBinding;
import com.example.proyectofinal_frame1.ui.home.HomeFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    Context context;

    // aniadido
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        context = container.getContext();

        recyclerViewPrendas = root.findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(context, 2));

        listaPrendas = new ArrayList<>();
        listaPrendas.add(new Prenda("Conjunto día de playa", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Formal", "Blanco")));
        listaPrendas.add(new Prenda("Conjunto boda", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Informal", "Azul")));
        listaPrendas.add(new Prenda("Conjunto miércoles", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Formal", "Rojo", "Verano")));
        listaPrendas.add(new Prenda("Conjunto casual", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Informal", "Azul")));

        prendaAdapter = new PrendaAdapter(listaPrendas);
        recyclerViewPrendas.setAdapter(prendaAdapter);

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
        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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