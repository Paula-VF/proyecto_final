package com.example.proyectofinal_frame1.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectofinal_frame1.Prenda;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class TablaPrendasXConjuntos extends ProyectoDatabaseHelper{

    Context context;
    private TablaPrenda tablaPrenda;
    public TablaPrendasXConjuntos(@Nullable Context context, TablaPrenda tablaPrenda){
        super(context);
        this.context=context;
        this.tablaPrenda = tablaPrenda;
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

    public List<Prenda> obtenerPrendasDelConjunto(long idConjunto) {
        List<Prenda> prendas = new ArrayList<>();

        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] columnas = {"id_prenda"};

            String selection = "id_conjunto = ?";
            String[] selectionArgs = {String.valueOf(idConjunto)};

            Cursor cursor = db.query(TABLA_PRENDASXCONJUNTOS, columnas, selection, selectionArgs, null, null, null);

            while (cursor.moveToNext()) {
                @SuppressLint("Range") long idPrenda = cursor.getLong(cursor.getColumnIndex("id_prenda"));

                // Obtener la prenda por su id
                Prenda prenda = tablaPrenda.obtenerPrendaPorId(idPrenda);

                if (prenda != null) {
                    prendas.add(prenda);
                }
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return prendas;
    }

}
