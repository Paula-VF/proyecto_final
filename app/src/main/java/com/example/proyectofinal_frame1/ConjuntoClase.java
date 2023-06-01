package com.example.proyectofinal_frame1;

public class ConjuntoClase {

    private long id;
    private String nombre;
    private long usuario;

    public ConjuntoClase(){}

    public ConjuntoClase(String nombre){
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }
}
