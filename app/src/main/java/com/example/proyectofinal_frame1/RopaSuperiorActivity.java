package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.TablaConjunto;
import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class RopaSuperiorActivity extends AppCompatActivity implements PrendaAdapter.OnPrendaListener{

    private static RopaSuperiorActivity ropaSuperior;
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private ImageView btnBack;
    private TablaPrenda tablaPrenda = new TablaPrenda(RopaSuperiorActivity.this);
    private AlertDialog dialogo;

    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_superior);

        ropaSuperior = this;

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


    public static RopaSuperiorActivity getInstance(){
        return ropaSuperior;
    }

    public List<Prenda> getListaPrendas() {
        return listaPrendas;
    }

    public void dialogoEliminar(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("ELIMINAR");
        dialog.setMessage("Si aceptas eliminar este artículo no podrás volver a recuperarlo. ¿Estás seguro?");
        dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Prenda prendaABorrar = listaPrendas.get(position);
                long idPrenda = prendaABorrar.getId();
                listaPrendas.remove(position);
                if(tablaPrenda.eliminarPrenda(idPrenda)){
                    Toast.makeText(getApplicationContext(), "prenda borrada de la bd", Toast.LENGTH_SHORT).show();
                }
                prendaAdapter.notifyItemChanged(position);
                prendaAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), prendaABorrar.getNombre() + " eliminado/a." + idPrenda,
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

//    public void aniadiraConjunto(int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View dView = getLayoutInflater().inflate(R.layout.dialogo_prenda_conjunto, null);
//        TextView titulo = dView.findViewById(R.id.titulo_aniadir);
//        linear = findViewById(R.id.lista);
//        Button addBtn = dView.findViewById(R.id.btn_aniadir);
//        Button cancelBtn = dView.findViewById(R.id.btn_cancelar);
//        for(ConjuntoItem conj : DashboardFragment.getInstance().getListaConjuntos()) {
//            // ConjuntoItem conj = DashboardFragment.getInstance().getListaConjuntos().get(i);
//            CheckBox check = new CheckBox(this);
//            check.setText(conj.toString());
//            linear.addView(check);
//            dView.refreshDrawableState();
//        }
//
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // funcionalidad al seleccionar los checkboxes
//                dialogo.cancel();
//                Toast.makeText(getApplicationContext(), "Conjunto creado", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialogo.cancel();
//            }
//        });
//
//        builder.setView(dView);
//        dialogo = builder.create();
//        dialogo.show();
//    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPrendaClick(int position) {
//        aniadiraConjunto(position);
    }

    @Override
    public void onPrendaLongClick(int position) {
        dialogoEliminar(position);
    }
}