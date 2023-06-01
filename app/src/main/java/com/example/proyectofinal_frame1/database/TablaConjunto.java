package com.example.proyectofinal_frame1.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectofinal_frame1.ConjuntoClase;

import java.util.ArrayList;
import java.util.List;

public class TablaConjunto extends ProyectoDatabaseHelper{

    Context context;
    public TablaConjunto(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarConjunto(String nombre, int usuario){
        long id = 0;

        try{
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);

            //modificar código. Hay relaciones, sacar info de las tablas relacionadas
            values.put("usuario", usuario);
            id = db.insert(TABLA_CONJUNTO, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public List<ConjuntoClase> obtenerConjuntos() {
        List<ConjuntoClase> conjuntos = new ArrayList<>();

        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] columnas = {"id", "nombre", "usuario"};

            Cursor cursor = db.query(TABLA_CONJUNTO, columnas, null, null, null, null, null);

            while (cursor.moveToNext()) {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") int usuario = cursor.getInt(cursor.getColumnIndex("usuario"));

                ConjuntoClase conjunto = new ConjuntoClase();
                conjunto.setId(id);
                conjunto.setNombre(nombre);
                conjunto.setUsuario(usuario);
                conjuntos.add(conjunto);
            }

            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return conjuntos;
    }

}
