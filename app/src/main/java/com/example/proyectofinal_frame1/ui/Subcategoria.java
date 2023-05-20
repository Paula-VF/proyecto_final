package com.example.proyectofinal_frame1.ui;

import android.widget.Button;
import android.widget.EditText;

import com.example.proyectofinal_frame1.R;

public class Subcategoria {
    // atributos
    private String writeName;
    private String btnAdded;

    // constructor con parámetros
    public Subcategoria(String writeName, String btnAdded) {
        this.writeName = writeName;
        this.btnAdded = btnAdded;
    }

    // constructor vacío
    public Subcategoria() {
    }


    // getters
    public String getWriteName() {
        return writeName;
    }

    public String getBtnAdded() {
        return btnAdded;
    }
}
