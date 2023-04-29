package com.example.proyectofinal_frame1.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_frame1.Prenda;
import com.example.proyectofinal_frame1.PrendaAdapter;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.databinding.FragmentDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
         */
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}