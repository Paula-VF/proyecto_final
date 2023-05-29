package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.TablaPrenda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RopaInferiorActivity extends AppCompatActivity implements PrendaAdapter.OnPrendaListener{

    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private ImageView btnBack;
    private TablaPrenda tablaPrenda = new TablaPrenda(RopaInferiorActivity.this);
    private AlertDialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_inferior);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        recyclerViewPrendas = findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = tablaPrenda.obtenerPrendas(2);

        prendaAdapter = new PrendaAdapter(listaPrendas, this);
        recyclerViewPrendas.setAdapter(prendaAdapter);
    }

    public void dialogoEliminar(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("ELIMINAR");
        dialog.setMessage("Si aceptas eliminar este artículo no podrás volver a recuperarlo. ¿Estás seguro?");
        dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaPrendas.remove(position);
                prendaAdapter.notifyItemChanged(position);
                prendaAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), listaPrendas.get(position) + " eliminado/a.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogo.cancel();
            }
        });

        dialogo = dialog.create();
        dialogo.show();
    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPrendaClick(int position) {
        Toast.makeText(getApplicationContext(), "Hola",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPrendaLongClick(int position) {
        dialogoEliminar(position);
    }
}