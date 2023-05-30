package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Conjunto extends AppCompatActivity {

    static Conjunto conjuntoActivity;

    private ImageView btnBack;
    private ImageButton btnDelete;

    private FrameLayout pArriba;
    private FrameLayout pAbajo;
    private FrameLayout zapatos;
    private FrameLayout extra1;
    private FrameLayout extra2;
    private FrameLayout extra3;
    private FrameLayout extra4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conjunto);

        conjuntoActivity = this;

        btnBack = findViewById(R.id.btn_back);
        btnDelete = findViewById(R.id.btn_delete);

        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });



    }

    public static Conjunto getInstance(){
        return conjuntoActivity;
    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}