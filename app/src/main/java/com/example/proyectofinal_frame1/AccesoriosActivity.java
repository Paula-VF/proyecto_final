package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccesoriosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios);
    }

    public void toPulseras(View view) {
        Intent intent = new Intent(AccesoriosActivity.this, AccesoriosPulseras.class);
        startActivity(intent);
    }
}