package com.example.proyectofinal_frame1;

import java.io.Serializable;
import java.util.List;

public class Prenda implements Serializable {
    // atributos
    private long id;
    private String nombre;
    private String urlImagen;

    private long categoria;
    private long usuario;

    // constructor vacío
    public Prenda() {
    }

    // constructor con parámetros
    public Prenda(String nombre, String imagen) {
        this.nombre = nombre;
        this.urlImagen = imagen;
    }


    // getters
    public String getNombre() {
        return nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public long getCategoria() {
        return categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setCategoria(long categoria) {
        this.categoria = categoria;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
