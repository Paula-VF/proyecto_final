package com.example.proyectofinal_frame1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class TablaUsuario extends ProyectoDatabaseHelper{

    Context context;
    public TablaUsuario(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarUsuario(String nombre, String email, String contraseña){
        long id = 0;

        try{
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("email", email);
            //Modificar código. Contraseña segura
            values.put("contraseña", contraseña);
            id = db.insert(TABLA_USUARIO, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public boolean existeUsuario(String email) {
        boolean existe = false;

        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {"email"};
            String selection = "email = ?";
            String[] selectionArgs = {email};

            Cursor cursor = db.query(TABLA_USUARIO, projection, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                existe = true;
            }
            cursor.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return existe;
    }

}
