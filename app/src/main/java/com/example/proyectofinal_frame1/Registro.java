package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void toMain(View view) {
        Intent intent = new Intent(Registro.this, MainActivity.class);
        startActivity(intent);
    }
}