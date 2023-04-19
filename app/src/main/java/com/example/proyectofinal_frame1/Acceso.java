package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Acceso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
    }

    public void toMain(View view) {
        Intent intent = new Intent(Acceso.this, MainActivity.class);
        startActivity(intent);
    }
}