package com.example.proyectofinal_frame1.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.proyectofinal_frame1.Prenda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablaPrenda extends ProyectoDatabaseHelper{
    Context context;
    public TablaPrenda(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarPrenda(String nombre, String rutaImagen, long categoria, long usuario) {
        long id = 0;

        try {
            // Obtener la base de datos en modo escritura
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Crear los valores a insertar
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("imagen", rutaImagen);
            values.put("categoria", categoria);
            values.put("usuario", usuario);

            // Insertar el registro en la tabla
            id = db.insert(TABLA_PRENDA, null, values);

            // Cerrar la base de datos
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public List<String> obtenerRutasImagenes(int numCategoría) {
        List<String> rutasImagenes = new ArrayList<>();
        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT imagen FROM " + TABLA_PRENDA+ " WHERE categoria = ?", new String[]{String.valueOf(numCategoría)});
            if (cursor.moveToFirst()) {
                do {
                    int columnaImagen = cursor.getColumnIndex("imagen");
                    if (columnaImagen >= 0) {
                        String rutaImagen = cursor.getString(columnaImagen);
                        rutasImagenes.add(rutaImagen);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return rutasImagenes;
    }

    public List<Prenda> obtenerPrendas(long categoria) {
        List<Prenda> prendas = new ArrayList<>();

        try {
            // Obtener la base de datos en modo lectura
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Definir las columnas que deseas recuperar
            String[] columnas = {"nombre", "imagen", "categoria", "usuario"};
            String filtrado = "categoria = ?";

            String[] argumentos = {String.valueOf(categoria)};

            // Realizar la consulta a la tabla de prendas
            Cursor cursor = db.query(TABLA_PRENDA, columnas, filtrado, argumentos, null, null, null);

            // Recorrer el cursor y obtener los datos de cada prenda
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Obtener los valores de cada columna
                    @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    @SuppressLint("Range") String rutaImagen = cursor.getString(cursor.getColumnIndex("imagen"));
                    @SuppressLint("Range") long categoriaPrenda = cursor.getLong(cursor.getColumnIndex("categoria"));
                    @SuppressLint("Range") long usuario = cursor.getLong(cursor.getColumnIndex("usuario"));

                    // Crear una instancia de Prenda y agregarla a la lista
                    Prenda prenda = new Prenda();
                    prenda.setNombre(nombre);
                    prenda.setUrlImagen(rutaImagen);
                    prenda.setCategoria(categoriaPrenda);
                    prenda.setUsuario(usuario);
                    prendas.add(prenda);
                } while (cursor.moveToNext());
            }
            // Cerrar el cursor y la base de datos
            cursor.close();
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return prendas;
    }

}
