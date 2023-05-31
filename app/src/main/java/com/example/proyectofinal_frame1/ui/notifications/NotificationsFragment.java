package com.example.proyectofinal_frame1.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.databinding.FragmentNotificationsBinding;
import com.google.android.material.snackbar.Snackbar;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    Context context;

    private TextView cerrarSesion;
    private TextView acercaDe;
    private ImageView logo;
    private ImageView nombre;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        context = container.getContext();

        cerrarSesion = (TextView) root.findViewById(R.id.cerrar_sesion);
        acercaDe = (TextView) root.findViewById(R.id.acerca_de);
        logo = (ImageView) root.findViewById(R.id.logo);
        nombre = (ImageView) root.findViewById(R.id.nombre);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // funcionalidad al clicar en Cerrar sesión
            }
        });

        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Aplicación creada por Emely Aguilar y Paula Vallespín", Snackbar.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.accesoriosLayout, fragment); // creo que el fallo está aquí
        // si se pone R.id.accesoriosLayout se ven los activity pero sin el toolbar
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}