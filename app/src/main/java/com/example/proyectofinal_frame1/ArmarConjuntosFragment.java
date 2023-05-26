package com.example.proyectofinal_frame1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.ui.dashboard.DashboardFragment;

public class ArmarConjuntosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnAleatorio;
    private Button btnGuardar;
    private AlertDialog dialogo;
    private DashboardFragment conjuntos;
    private Context context;

    public ArmarConjuntosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArmarConjuntosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArmarConjuntosFragment newInstance(String param1, String param2) {
        ArmarConjuntosFragment fragment = new ArmarConjuntosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            CarruselFragment carrusel1, carrusel2, carrusel3;
            FragmentManager fragmentManager = getChildFragmentManager();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_armar_conjuntos, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CarruselFragment carrusel1, carrusel2, carrusel3;
        FragmentManager fragmentManager = getChildFragmentManager();

        carrusel1 = new CarruselFragment(1);
        carrusel2 = new CarruselFragment(2);
        carrusel3 = new CarruselFragment(3);

        fragmentManager.beginTransaction()
                .replace(R.id.contenedorCarrusel1, carrusel1)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.contenedorCarrusel2, carrusel2)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.contenedorCarrusel3, carrusel3)
                .commit();

        /*
        btnAleatorio = view.findViewById(R.id.btn_random);
        btnGuardar = view.findViewById(R.id.btn_save);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //guardarConjunto();
            }
        });

         */
    }



    // funcionalidad boton Guardar
    private void guardarConjunto() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View dView = getLayoutInflater().inflate(R.layout.dialogo_subcategoria, null);
        TextView titulo = dView.findViewById(R.id.titulo);
        EditText nombre = dView.findViewById(R.id.nombre);
        Button addBtn = dView.findViewById(R.id.btn_add);
        Button cancelBtn = dView.findViewById(R.id.btn_cancel);
        titulo.setText("Nombre del conjunto:");
        addBtn.setText("GUARDAR");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre != null) {
                    String conjunto = nombre.getText().toString();
                    conjuntos = new DashboardFragment();
                    //conjuntos.getListaPrendas().add(new Prenda(conjunto, R.drawable.ic_baseline_photo_camera_back_24));
                    //arrayAdapter.notifyDataSetChanged();
                    dialogo.cancel();
                    Toast.makeText(context, "Conjunto " + conjunto + " creado.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Introduce un nombre para guardar.", Toast.LENGTH_SHORT).show();
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