package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesoriosPulseras extends AppCompatActivity {
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios_pulseras);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        recyclerViewPrendas = findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = new ArrayList<>();
        listaPrendas.add(new Prenda("Brazalete dorado", R.drawable.ic_baseline_photo_camera_back_24));
        listaPrendas.add(new Prenda("Multicolor", R.drawable.ic_baseline_photo_camera_back_24));
        listaPrendas.add(new Prenda("Piedras moradas", R.drawable.ic_baseline_photo_camera_back_24));
        listaPrendas.add(new Prenda("Pulsera de la suerte", R.drawable.ic_baseline_photo_camera_back_24));

        prendaAdapter = new PrendaAdapter(listaPrendas);
        recyclerViewPrendas.setAdapter(prendaAdapter);
    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}