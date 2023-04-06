package com.example.proyectofinal_frame1;

import java.util.List;

public class Prenda {
    private String nombre;
    private int urlImagen;
    private List<String> etiquetas;

    public Prenda(String nombre, int imagen, List<String> etiquetas) {
        this.nombre = nombre;
        this.urlImagen = imagen;
        this.etiquetas = etiquetas;
    }

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
