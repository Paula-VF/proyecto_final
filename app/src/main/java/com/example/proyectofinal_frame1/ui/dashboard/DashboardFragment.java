package com.example.proyectofinal_frame1.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_frame1.AccesoriosActivity;
import com.example.proyectofinal_frame1.CarruselFragment;
import com.example.proyectofinal_frame1.MainActivity;
import com.example.proyectofinal_frame1.Prenda;
import com.example.proyectofinal_frame1.PrendaAdapter;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.RopaSuperiorActivity;
import com.example.proyectofinal_frame1.Subcategoria;
import com.example.proyectofinal_frame1.databinding.FragmentDashboardBinding;
import com.example.proyectofinal_frame1.ui.home.HomeFragment;
import com.example.proyectofinal_frame1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    Context context;

    // aniadido
    private FloatingActionButton floatBtn;
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private AlertDialog dialogo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        context = container.getContext();

        recyclerViewPrendas = root.findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new GridLayoutManager(context, 2));

        listaPrendas = new ArrayList<>();
        listaPrendas.add(new Prenda("Conjunto día de playa", R.drawable.ic_baseline_photo_camera_back_24));
        listaPrendas.add(new Prenda("Conjunto boda", R.drawable.ic_baseline_photo_camera_back_24));
        listaPrendas.add(new Prenda("Conjunto miércoles", R.drawable.ic_baseline_photo_camera_back_24));
        listaPrendas.add(new Prenda("Conjunto casual", R.drawable.ic_baseline_photo_camera_back_24));

        prendaAdapter = new PrendaAdapter(listaPrendas);
        recyclerViewPrendas.setAdapter(prendaAdapter);


        floatBtn = (FloatingActionButton) root.findViewById(R.id.float_btn);
        // funcionalidad al clicar en el botón flotante
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarNombre();

            }
        });
        /*
        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
         */
        return root;
    }




    private void cambiarNombre() {
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
                    listaPrendas.add(new Prenda(conjunto, R.drawable.ic_baseline_photo_camera_back_24));
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

    public List<Prenda> getListaPrendas() {
        return listaPrendas;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}