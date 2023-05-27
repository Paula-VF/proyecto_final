package com.example.proyectofinal_frame1.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_frame1.Conjunto;
import com.example.proyectofinal_frame1.Prenda;
import com.example.proyectofinal_frame1.PrendaAdapter;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.databinding.FragmentDashboardBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements PrendaAdapter.OnPrendaListener {

    private FragmentDashboardBinding binding;
    private Context context;

    private FloatingActionButton floatBtn;
    private RecyclerView recyclerViewPrendas;
    private ListView listView;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private ArrayAdapter<Prenda> arrayAdapter;
    private AlertDialog dialogo;
    private Prenda nuevoConjunto;

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


        prendaAdapter = new PrendaAdapter(listaPrendas, this);
        recyclerViewPrendas.setAdapter(prendaAdapter);

        recyclerViewPrendas.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Conjunto.class);
                startActivity(intent);
            }
        });
        recyclerViewPrendas.setOnLongClickListener(new AdapterView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // abrirDialogo();
                return false;
            }
        });

        /*
        listView = root.findViewById(R.id.list_conjuntos);
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_2, listaPrendas);
        listView.setAdapter(arrayAdapter);

        // funcionalidad al clicar en un conjunto
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(context, Conjunto.class);
                //startActivity(intent);
            }
        });

        // funcionalidad al mantener pulsado en un conjunto
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                abrirDialogo(position);
                return false;
            }
        });
         */


        floatBtn = (FloatingActionButton) root.findViewById(R.id.float_btn);
        // funcionalidad al clicar en el botón flotante
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombrarConjunto();
                //Conjunto.getInstance();
            }
        });


        return root;
    }




    private void nombrarConjunto() {
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
                    nuevoConjunto = new Prenda(conjunto, R.drawable.ic_baseline_photo_camera_back_24);
                    listaPrendas.add(nuevoConjunto);
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


    private void abrirDialogo(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("ELIMINAR");
        dialog.setMessage("Si aceptas elimniar este conjunto no podrás volver a recuperarlo. ¿Estás seguro?");
        dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nuevoConjunto = null;
                listaPrendas.remove(position);
                arrayAdapter.notifyDataSetChanged();
                //listView.refreshDrawableState();
                Toast.makeText(context, "Conjunto " + listaPrendas.get(position).toString().toString() + " eliminado.",
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

    @Override
    public void onPrendaClick(int position) {
        Log.d(TAG, "onPrendaClick: clicked " + position);

        Intent intent = new Intent(context, Conjunto.class);
        intent.putExtra("Objeto_Conjunto", listaPrendas.get(position));
        startActivity(intent);
    }
}