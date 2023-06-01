package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.example.proyectofinal_frame1.database.TablaPrendasXConjuntos;

import java.util.List;

public class ConjuntoActivity extends AppCompatActivity implements PrendaAdapter.OnPrendaListener {

    static ConjuntoActivity conjuntoActivity;

    private ImageView btnBack;
    private ImageButton btnDelete;

    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private long idConjunto;
    TablaPrenda tablaPrenda = new TablaPrenda(ConjuntoActivity.this);
    private TablaPrendasXConjuntos prendasXConjuntos = new TablaPrendasXConjuntos(ConjuntoActivity.this, tablaPrenda);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conjunto);
        idConjunto = getIntent().getLongExtra("idConjunto", -1);

        conjuntoActivity = this;

        btnBack = findViewById(R.id.btn_back);
        btnDelete = findViewById(R.id.btn_delete);

        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });
        recyclerViewPrendas = findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = prendasXConjuntos.obtenerPrendasDelConjunto(idConjunto);

        prendaAdapter = new PrendaAdapter(listaPrendas, this);
        recyclerViewPrendas.setAdapter(prendaAdapter);
    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onPrendaClick(int position) {

    }

    @Override
    public void onPrendaLongClick(int position) {

    }
}