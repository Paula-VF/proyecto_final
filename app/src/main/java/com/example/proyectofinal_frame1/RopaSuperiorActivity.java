package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class RopaSuperiorActivity extends AppCompatActivity implements PrendaAdapter.OnPrendaListener{
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private ImageView btnBack;
    private TablaPrenda tablaPrenda = new TablaPrenda(RopaSuperiorActivity.this);
    private AlertDialog dialogo;
    private Chip chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_superior);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        recyclerViewPrendas = findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(this, 2));

        listaPrendas = tablaPrenda.obtenerPrendas(1);

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

    public void aniadiraConjunto(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View dView = getLayoutInflater().inflate(R.layout.dialogo_prenda_conjunto, null);
        TextView titulo = dView.findViewById(R.id.titulo_aniadir);
        ChipGroup conjuntosChipGroup = dView.findViewById(R.id.chipGroup_conjuntos);
        Button addBtn = dView.findViewById(R.id.btn_aniadir);
        Button cancelBtn = dView.findViewById(R.id.btn_cancelar);
        for(int i = 0; i < DashboardFragment.getInstance().getListaConjuntos().size(); i++) {
            ConjuntoItem conj = DashboardFragment.getInstance().getListaConjuntos().get(i);
            chip = new Chip(this);
            chip.setText(conj.getNombre());
            conjuntosChipGroup.addView(chip);
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chipId = conjuntosChipGroup.getCheckedChipId();

                dialogo.cancel();
                Toast.makeText(getApplicationContext(), "Conjunto creado.", Toast.LENGTH_SHORT).show();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.cancel();
            }
        });

        dialog.setView(dView);
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
        Intent intent = new Intent(this, DashboardFragment.class);
        //intent.putExtra("Objeto_Conjunto", listaConjuntos.get(position));
        startActivity(intent);
    }

    @Override
    public void onPrendaLongClick(int position) {
        dialogoEliminar(position);
    }
}