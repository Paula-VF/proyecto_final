package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesoriosPulseras extends AppCompatActivity {
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios_pulseras);

        recyclerViewPrendas = findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = new ArrayList<>();
        listaPrendas.add(new Prenda("Brazalete dorado", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Formal", "Blanco")));
        listaPrendas.add(new Prenda("Multicolor", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Informal", "Azul")));
        listaPrendas.add(new Prenda("Piedras moradas", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Formal", "Rojo", "Verano")));
        listaPrendas.add(new Prenda("Pulsera de la suerte", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Informal", "Azul")));

        prendaAdapter = new PrendaAdapter(listaPrendas);
        recyclerViewPrendas.setAdapter(prendaAdapter);
    }
}