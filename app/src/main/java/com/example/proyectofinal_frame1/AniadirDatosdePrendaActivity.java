package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AniadirDatosdePrendaActivity extends AppCompatActivity {
    EditText nombrePrenda;
    ImageView imagenPrenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_datosde_prenda);
        nombrePrenda = findViewById(R.id.nombrePrenda);
        imagenPrenda = findViewById(R.id.imagenPrenda);
    }

    private void insertarDatos(Bitmap imgBitmap){

        imagenPrenda.setImageBitmap(imgBitmap);

        String nombre = nombrePrenda.getText().toString();
        //chip con subcategorias

        //prenda.insertarPrenda(nombre, rutaImagen, 1, 1);

    }
}