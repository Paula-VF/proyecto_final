package com.example.proyectofinal_frame1.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal_frame1.AccesoriosActivity;
import com.example.proyectofinal_frame1.CarruselFragment;
import com.example.proyectofinal_frame1.ComplementosActivity;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.RopaInferiorActivity;
import com.example.proyectofinal_frame1.RopaSuperiorActivity;
import com.example.proyectofinal_frame1.ZapatosActivity;
import com.example.proyectofinal_frame1.databinding.FragmentHomeBinding;
import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;

    Context context;

    private ImageButton imageButtonArriba;
    private ImageButton imageButtonAbajo;
    private ImageButton imageButtonZapatos;
    private ImageButton imageButtonComplem;
    private ImageButton imageButtonAccesorios;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        context = container.getContext();


        // categorías
        imageButtonArriba = (ImageButton) root.findViewById(R.id.imageButtonArriba);
        imageButtonAbajo = (ImageButton) root.findViewById(R.id.imageButtonAbajo);
        imageButtonZapatos = (ImageButton) root.findViewById(R.id.imageButtonZapatos);
        imageButtonComplem = (ImageButton) root.findViewById(R.id.imageButtonComplem);
        imageButtonAccesorios = (ImageButton) root.findViewById(R.id.imageButtonAccesorios);

        // funcionalidad botones categorias
        imageButtonArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RopaSuperiorActivity.class);
                startActivity(intent);
            }
        });
        imageButtonAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RopaInferiorActivity.class);
                startActivity(intent);
            }
        });
        imageButtonZapatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZapatosActivity.class);
                startActivity(intent);
            }
        });
        imageButtonComplem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComplementosActivity.class);
                startActivity(intent);
            }
        });
        imageButtonAccesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccesoriosActivity.class);
                startActivity(intent);
            }
        });

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


    @Override
    public void onClick(View v) {

    }
}
