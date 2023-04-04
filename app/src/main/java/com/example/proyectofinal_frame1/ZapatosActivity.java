package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZapatosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_superior);

        recyclerViewPrendas = findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = new ArrayList<>();
        listaPrendas.add(new Prenda("Sandalias", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Formal", "Blanco", "Verano")));
        listaPrendas.add(new Prenda("Chanclas", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Informal", "Azul")));
        listaPrendas.add(new Prenda("Botas", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Formal", "Negro", "Invierno")));
        listaPrendas.add(new Prenda("Zapatillas", R.drawable.ic_baseline_photo_camera_back_24, Arrays.asList("Informal", "Azul")));

        prendaAdapter = new PrendaAdapter(listaPrendas);
        recyclerViewPrendas.setAdapter(prendaAdapter);
    }
}