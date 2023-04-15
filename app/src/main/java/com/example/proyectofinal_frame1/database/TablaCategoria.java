package com.example.proyectofinal_frame1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class TablaCategoria extends ProyectoDatabaseHelper{
    Context context;
    public TablaCategoria(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarCategoria(String nombre){
        long id = 0;
        try{
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            id = db.insert(TABLA_CATEGORIA, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
