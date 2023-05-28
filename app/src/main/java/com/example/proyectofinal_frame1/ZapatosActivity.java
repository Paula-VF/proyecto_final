package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectofinal_frame1.database.TablaPrenda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZapatosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private ImageView btnBack;
    private TablaPrenda tablaPrenda = new TablaPrenda(ZapatosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapatos);

        btnBack = findViewById(R.id.btn_back);
        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        recyclerViewPrendas = findViewById(R.id.recyclerViewZapatos);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = tablaPrenda.obtenerPrendas(3);

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