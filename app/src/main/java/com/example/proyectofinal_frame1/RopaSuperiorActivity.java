package com.example.proyectofinal_frame1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RopaSuperiorActivity<newCheckbox, mCheckboxes> extends AppCompatActivity {

    private FloatingActionButton floatBtn;

    private ImageView btnBack;
    private ImageButton btnDelete;
    private ImageButton btnEdit;
    private ListView listView;
    private ArrayList<Subcategoria> subcategorias;
    private ArrayAdapter<Subcategoria> arrayAdapter;

    private AlertDialog dialogo;
    private Subcategoria nuevoBtn;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_superior);

        context = getApplicationContext();

        floatBtn = (FloatingActionButton) findViewById(R.id.float_btn);
        btnBack = findViewById(R.id.btn_back);
        btnDelete = findViewById(R.id.btn_delete);
        btnEdit = findViewById(R.id.btn_edit);

        // para mostrar las subcategorías añadidas
        subcategorias = new ArrayList<>();
        listView = findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, subcategorias);
        listView.setAdapter(arrayAdapter);
        // funcionalidad al clicar en una subcategoría
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // funcionalidad al clicar en una subcategoria
            }
        });

        // funcionalidad al mantener pulsado en una subcategoría
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                btnDelete.setVisibility(View.VISIBLE);
                // funcionalidad al clicar btnDelete
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //parent.getItemAtPosition(position);
                        nuevoBtn = null;
                        subcategorias.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        btnDelete.setVisibility(View.INVISIBLE);
                        btnEdit.setVisibility(View.INVISIBLE);
                        //listView.refreshDrawableState();
                        Toast.makeText(getApplicationContext(), "Subcategoría " + subcategorias.get(position).toString().toString() + " eliminada.", Toast.LENGTH_SHORT).show();
                    }
                });


                btnEdit.setVisibility(View.VISIBLE);
                // funcionalidad al clicar btnEdit
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cambiarNombre(position);
                        btnDelete.setVisibility(View.INVISIBLE);
                        btnEdit.setVisibility(View.INVISIBLE);
                    }
                });
                return false;
            }
        });


        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        // funcionalidad al clicar en el botón flotante
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarNombre();
            }
        });

    }


    private void insertarNombre() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(RopaSuperiorActivity.this);
        View dView = getLayoutInflater().inflate(R.layout.dialogo_subcategoria, null);
        TextView titulo = dView.findViewById(R.id.titulo);
        EditText nombre = dView.findViewById(R.id.nombre);
        Button addBtn = dView.findViewById(R.id.btn_add);
        Button cancelBtn = dView.findViewById(R.id.btn_cancel);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre!=null) {
                    String subcategoria = nombre.getText().toString().toUpperCase();
                    nuevoBtn = new Subcategoria(subcategoria);
                    // subcategorias.get(position).setBtnAdded(subcategoria);
                    subcategorias.add(nuevoBtn);
                    arrayAdapter.notifyDataSetChanged();
                    dialogo.cancel();
                    Toast.makeText(getApplicationContext(), "Subcategoría " + subcategoria + " añadida.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Introduce un nombre.", Toast.LENGTH_SHORT).show();
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

    private void cambiarNombre(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(RopaSuperiorActivity.this);
        View dView = getLayoutInflater().inflate(R.layout.dialogo_subcategoria, null);
        TextView titulo = dView.findViewById(R.id.titulo);
        EditText nombre = dView.findViewById(R.id.nombre);
        Button addBtn = dView.findViewById(R.id.btn_add);
        Button cancelBtn = dView.findViewById(R.id.btn_cancel);
        titulo.setText("Nuevo nombre de subcategoría:");
        addBtn.setText("EDITAR");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre != null) {
                    String subcategoria = nombre.getText().toString().toUpperCase();
                    subcategorias.get(position).setBtnAdded(subcategoria);
                    arrayAdapter.notifyDataSetChanged();
                    dialogo.cancel();
                    Toast.makeText(getApplicationContext(), "Nuevo nombre: " + subcategoria, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Introduce un nombre.", Toast.LENGTH_SHORT).show();
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


    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}