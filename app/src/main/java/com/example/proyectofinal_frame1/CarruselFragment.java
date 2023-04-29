package com.example.proyectofinal_frame1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CarruselFragment extends Fragment {
    private RecyclerView recyclerView;
    private int[] imagenes = { R.drawable.prenda1, R.drawable.prenda2, R.drawable.prenda3, R.drawable.prenda5};

    public CarruselFragment() {
        // Constructor vacío requerido
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carrusel, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCarruselFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        CarruselAdapter adapter = new CarruselAdapter(getActivity(), imagenes);
        recyclerView.setAdapter(adapter);



        return view;
    }
}