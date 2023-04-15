package com.example.proyectofinal_frame1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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
}
