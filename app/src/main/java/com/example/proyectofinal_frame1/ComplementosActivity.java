package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
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
        recyclerViewSubcategorias.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        subcategorias = new ArrayList<>();
        subcategorias.add(new Subcategoria("BOLSOS"));
        /*
        subcategorias.add(new Subcategoria("FULARES"));
        subcategorias.add(new Subcategoria("GAFAS DE SOL"));

         */
        subcategoriaAdapter = new SubcategoriaAdapter(subcategorias);
        recyclerViewSubcategorias.setAdapter(subcategoriaAdapter);

        for (int i = 0; subcategorias.size() >i; i++){
            subcategorias.get(i).getBtnAdded();
        }
    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}