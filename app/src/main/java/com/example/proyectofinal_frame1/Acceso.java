package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Acceso extends AppCompatActivity {

    private TextView txtLinkCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        txtLinkCuenta = (TextView) findViewById(R.id.txtLinkCuenta);

        txtLinkCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acceso.this, Registro.class);
                txtLinkCuenta.setTextColor(Color.parseColor("#7E7E7E"));
                startActivity(intent);
            }
        });

    }

    public void toMain(View view) {
        Intent intent = new Intent(Acceso.this, MainActivity.class);
        startActivity(intent);
    }
}