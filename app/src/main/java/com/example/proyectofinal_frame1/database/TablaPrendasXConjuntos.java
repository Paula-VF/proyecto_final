package com.example.proyectofinal_frame1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class TablaPrendasXConjuntos extends ProyectoDatabaseHelper{

    Context context;
    public TablaPrendasXConjuntos(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarDatos(int prenda, int conjunto){
        long id = 0;

        try{
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //Modificar código. Hay relaciones, sacar info de las tablas relacionadas
            values.put("id_prenda", prenda);
            values.put("id_conjunto", conjunto);
            id = db.insert(TABLA_PRENDASXCONJUNTOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
