package com.example.proyectofinal_frame1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;

import java.util.List;

public class CarruselFragment extends Fragment {

    private static CarruselFragment carruselFragment;
    private RecyclerView recyclerView;
    private TablaPrenda tablaPrenda;
    private List<String> rutasImagenes;
    private int idCategoría;

    private long idUsuario;

    private ImageView btnAleatorio;
    private ImageView btnGuardar;
    private Context context;
    private ConjuntoItem nuevoConjunto;
    private AlertDialog dialogo;


    public CarruselFragment() {
        // Constructor vacío requerido
    }

    public CarruselFragment(int idCategoría, long idUsuario){
        this.idCategoría = idCategoría;
        this.idUsuario = idUsuario;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carrusel, container, false);

        context = container.getContext();
        carruselFragment = this;
        recyclerView = view.findViewById(R.id.recyclerViewCarruselFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        tablaPrenda = new TablaPrenda(getActivity());
        rutasImagenes = tablaPrenda.obtenerRutasImagenes(idCategoría);

        CarruselAdapter adapter = new CarruselAdapter(getActivity(), rutasImagenes);
        recyclerView.setAdapter(adapter);
        return view;
    }


    public static CarruselFragment getInstance(){
        return carruselFragment;
    }

    public void guardarConjunto() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View dView = getLayoutInflater().inflate(R.layout.dialogo_subcategoria, null);
        TextView titulo = dView.findViewById(R.id.titulo);
        EditText nombre = dView.findViewById(R.id.nombre);
        Button addBtn = dView.findViewById(R.id.btn_add);
        Button cancelBtn = dView.findViewById(R.id.btn_cancel);
        titulo.setText("Nombre del conjunto:");
        addBtn.setText("CREAR");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre != null) {
                    String conjunto = nombre.getText().toString();
                    nuevoConjunto = new ConjuntoItem(conjunto, R.drawable.ic_baseline_photo_camera_back_24);
                    DashboardFragment.getInstance().getListaConjuntos().add(nuevoConjunto);
                    //arrayAdapter.notifyDataSetChanged();
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
}