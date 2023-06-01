package com.example.proyectofinal_frame1.ui.dashboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_frame1.ConjuntoActivity;
import com.example.proyectofinal_frame1.CarruselFragment;
import com.example.proyectofinal_frame1.Conjunto;
import com.example.proyectofinal_frame1.ConjuntoAdapter;
import com.example.proyectofinal_frame1.ConjuntoClase;
import com.example.proyectofinal_frame1.ConjuntoItem;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.database.TablaConjunto;
import com.example.proyectofinal_frame1.databinding.FragmentDashboardBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements ConjuntoAdapter.OnConjuntoListener {

    static DashboardFragment conjuntosFragment;
    private FragmentDashboardBinding binding;
    private Context context;

    private FloatingActionButton floatBtn;
    private RecyclerView recyclerViewPrendas;
    private ConjuntoAdapter conjuntoAdapter;
    private List<ConjuntoItem> listaConjuntos;
    private AlertDialog dialogo;
    private ConjuntoItem nuevoConjunto;
    private TablaConjunto tablaConjunto;
    private View root;


    public DashboardFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        context = container.getContext();
        conjuntosFragment = this;
        tablaConjunto = new TablaConjunto(context);

        recyclerViewPrendas = root.findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(context, 2));

        listaConjuntos = new ArrayList<>();
        for (int i = 0; i<tablaConjunto.obtenerConjuntos().size(); i++){
            ConjuntoClase conjunto = tablaConjunto.obtenerConjuntos().get(i);
            listaConjuntos.add(new ConjuntoItem(conjunto.getId(), conjunto.getNombre(), R.drawable.conjunto));
        }

        conjuntoAdapter = new ConjuntoAdapter(listaConjuntos, this);
        recyclerViewPrendas.setAdapter(conjuntoAdapter);


        floatBtn = (FloatingActionButton) root.findViewById(R.id.float_btn);
        // funcionalidad al clicar en el botón flotante
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombrarConjunto();
            }
        });


        return root;
    }

    public static DashboardFragment getInstance(){
        return conjuntosFragment;
    }

    public List<ConjuntoItem> getListaConjuntos() {
        return listaConjuntos;
    }

    public ConjuntoAdapter getConjuntoAdapter() {
        return conjuntoAdapter;
    }

    public View getRoot() {
        return root;
    }


    private void nombrarConjunto() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View dView = getLayoutInflater().inflate(R.layout.dialogo_conjunto, null);
        TextView titulo = dView.findViewById(R.id.titulo);
        EditText nombre = dView.findViewById(R.id.nombre);
        Button addBtn = dView.findViewById(R.id.btn_add);
        Button cancelBtn = dView.findViewById(R.id.btn_cancel);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre != null) {
                    String conjunto = nombre.getText().toString();
                    nuevoConjunto = new ConjuntoItem(conjunto, R.drawable.conjunto);
                    listaConjuntos.add(nuevoConjunto);
                    conjuntoAdapter.notifyDataSetChanged();
                    tablaConjunto.insertarConjunto(conjunto, 1); // corregir usuario
                    dialogo.cancel();
                    Toast.makeText(context, "Conjunto " + conjunto + " creado.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Introduce un nombre para crear el conjunto.", Toast.LENGTH_SHORT).show();
                }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // dialogo para eliminar un conjunto
    public void abrirDialogo(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("ELIMINAR");
        dialog.setMessage("Si aceptas eliminar este conjunto no podrás volver a recuperarlo. ¿Estás seguro?");
        dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaConjuntos.remove(position);
                conjuntoAdapter.notifyDataSetChanged();
                Toast.makeText(context, "Conjunto " + listaConjuntos.get(position) + " eliminado.",
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

    // al clicar en un conjunto
    @Override
    public void onConjuntoClick(int position) {
        // se abre el activity de Conjunto con los artículos que le correspondan
        //getView().startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_item));
        ConjuntoItem conjunto = listaConjuntos.get(position);
        Intent intent = new Intent(context, ConjuntoActivity.class);
        //solo hace falta que al pinchar sobre el conjunto se le pase el id del conjunto
        //a ConjuntoActivity para que se muestren las prendas que son de ese conjunto
        intent.putExtra("idConjunto", conjunto.getIdConjunto());
        startActivity(intent);
    }

    // al mantener pulsado en un conjunto
    @Override
    public void onConjuntoLongClick(int position) {
        // se abre el dialogo para preguntar si se desea eliminar el conjunto
        abrirDialogo(position);
    }
}