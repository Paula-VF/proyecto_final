package com.example.proyectofinal_frame1;

import java.util.List;

public class Prenda {
    // atributos
    private String nombre;
    private int urlImagen;

    // constructor vacío
    public Prenda() {
    }

    // constructor con parámetros
    public Prenda(String nombre, int imagen) {
        this.nombre = nombre;
        this.urlImagen = imagen;
    }


    // getters
    public String getNombre() {
        return nombre;
    }

    public int getUrlImagen() {
        return urlImagen;
    }
}
