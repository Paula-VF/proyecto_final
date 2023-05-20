package com.example.proyectofinal_frame1;

import java.util.List;

public class Prenda {
    // atributos
    private String nombre;
    private int urlImagen;
    private List<String> etiquetas;

    // constructor con parámetros
    public Prenda(String nombre, int imagen, List<String> etiquetas) {
        this.nombre = nombre;
        this.urlImagen = imagen;
        this.etiquetas = etiquetas;
    }

    // constructor vacío
    public Prenda() {
    }


    // getters
    public String getNombre() {
        return nombre;
    }

    public int getUrlImagen() {
        return urlImagen;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }
}
