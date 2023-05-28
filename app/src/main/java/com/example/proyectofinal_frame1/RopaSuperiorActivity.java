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

import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RopaSuperiorActivity extends AppCompatActivity {

    private FloatingActionButton floatBtn;
    private RecyclerView recyclerViewPrendas;
    private PrendaAdapter prendaAdapter;
    private List<Prenda> listaPrendas;
    private ImageView btnBack;
    private TablaPrenda tablaPrenda = new TablaPrenda(RopaSuperiorActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_superior);
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

        prendaAdapter = new PrendaAdapter(listaPrendas);
        recyclerViewPrendas.setAdapter(prendaAdapter);
    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}