package com.example.proyectofinal_frame1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class TablaPrenda extends ProyectoDatabaseHelper{
    Context context;
    public TablaPrenda(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarPrenda(String nombre, Blob imagenPasada, String etiquetas, int categoria, int usuario){
        long id = 0;

        try{
            byte[] imagen = imagenPasada.getBytes(1, (int)imagenPasada.length());
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("imagen", imagen);
            values.put("etiquetas", etiquetas);

            //modificar código. Hay relaciones, sacar info de las tablas relacionadas
            values.put("categoria", categoria);
            values.put("usuario", usuario);
            id = db.insert(TABLA_PRENDA, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
