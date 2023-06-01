package com.example.proyectofinal_frame1;

import java.io.Serializable;

public class ConjuntoItem implements Serializable {

    private long idConjunto;
    private String nombre;
    private int urlImagen;

    // constructor vacío
    public ConjuntoItem() {
    }

    // constructor con parámetros
    public ConjuntoItem(String nombre, int imagen) {
            this.nombre = nombre;
            this.urlImagen = imagen;
    }
    public ConjuntoItem(long id, String nombre, int imagen){
        this.idConjunto=id;
        this.nombre=nombre;
        this.urlImagen=imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(int urlImagen) {
        this.urlImagen = urlImagen;
    }

    public long getIdConjunto() {
        return idConjunto;
    }

    public void setIdConjunto(long idConjunto) {
        this.idConjunto = idConjunto;
    }
}
