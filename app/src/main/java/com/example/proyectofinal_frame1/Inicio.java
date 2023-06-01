package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

    }

    public void toRegistro(View view) {
        Intent intent = new Intent(Inicio.this, Registro.class);
        startActivity(intent);
    }

    public void toAcceso(View view) {
        Intent intent = new Intent(Inicio.this, Acceso.class);
        startActivity(intent);
    }
}