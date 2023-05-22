package com.example.proyectofinal_frame1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.example.proyectofinal_frame1.ui.home.HomeFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplementosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSubcategorias;
    private SubcategoriaAdapter subcategoriaAdapter;
    private List<Subcategoria> subcategorias;

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complementos);

        btnBack = findViewById(R.id.btn_back);
        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        recyclerViewSubcategorias = findViewById(R.id.recycler_subcategorias);
        recyclerViewSubcategorias.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        subcategorias = new ArrayList<>();
        subcategorias.add(new Subcategoria("", "BOLSOS"));
        subcategorias.add(new Subcategoria("", "FULARES"));
        subcategorias.add(new Subcategoria("", "GAFAS DE SOL"));

        subcategoriaAdapter = new SubcategoriaAdapter(subcategorias);
        recyclerViewSubcategorias.setAdapter(subcategoriaAdapter);

    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}