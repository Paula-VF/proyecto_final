package com.example.proyectofinal_frame1;

import android.widget.Button;
import android.widget.EditText;

import com.example.proyectofinal_frame1.R;

public class Subcategoria {
    private String btnAdded;

    // constructor con parámetros
    public Subcategoria(String btnAdded) {
        this.btnAdded = btnAdded;
    }

    // constructor vacío
    public Subcategoria() {
    }


    // getters and setters
    public String getBtnAdded() {
        return btnAdded;
    }

    public void setBtnAdded(String btnAdded) {
        this.btnAdded = btnAdded;
    }
}
