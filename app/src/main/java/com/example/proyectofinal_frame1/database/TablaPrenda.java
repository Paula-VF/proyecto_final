package com.example.proyectofinal_frame1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablaPrenda extends ProyectoDatabaseHelper{
    Context context;
    public TablaPrenda(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarPrenda(String nombre, String rutaImagen, String etiquetas, long categoria, long usuario) {
        long id = 0;

        try {
            // Obtener la base de datos en modo escritura
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Crear los valores a insertar
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("imagen", rutaImagen);
            values.put("etiquetas", etiquetas);
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

    public List<String> obtenerRutasImagenes() {
        List<String> rutasImagenes = new ArrayList<>();
        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT imagen FROM " + TABLA_PRENDA, null);
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
}
