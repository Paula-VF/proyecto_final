package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PArribaCamisetas extends AppCompatActivity {
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parriba_camisetas);

        recyclerViewPrendas = findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = new ArrayList<>();


        prendaAdapter = new PrendaAdapter(listaPrendas);
        recyclerViewPrendas.setAdapter(prendaAdapter);
    }
}