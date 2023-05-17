package com.example.proyectofinal_frame1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.example.proyectofinal_frame1.database.TablaPrenda;

import java.util.List;

public class CarruselFragment extends Fragment {
    private RecyclerView recyclerView;
    private TablaPrenda tablaPrenda;
    private List<String> rutasImagenes;
    private int idCategoría;

    public CarruselFragment() {
        // Constructor vacío requerido
    }

    public CarruselFragment(int idCategoría){
        this.idCategoría = idCategoría;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carrusel, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCarruselFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        tablaPrenda = new TablaPrenda(getActivity());
        rutasImagenes = tablaPrenda.obtenerRutasImagenes(idCategoría);

        CarruselAdapter adapter = new CarruselAdapter(getActivity(), rutasImagenes);
        recyclerView.setAdapter(adapter);


        return view;
    }

}