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

    public long insertarUsuario(String nombre, String email, String contrasena){
        long id = 0;

        try{
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("email", email);
            //Modificar código. Contraseña segura
            values.put("contrasena", contrasena);
            id = db.insert(TABLA_USUARIO, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public long obtenerUsuario(String email){
        long usuarioId = 0;
        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] params = {email};
            Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_USUARIO+ " WHERE email = ?", params);

            if(cursor.moveToFirst()){
                int columnIndex = cursor.getColumnIndex("user_id");
                usuarioId = cursor.getLong(columnIndex);
            }
            cursor.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return usuarioId;
    }

    public boolean existeUsuario(String email) {
        boolean existe = false;

        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] params = {email};
            Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_USUARIO+ " WHERE email= ?", params);

            existe = cursor.moveToNext();
            cursor.close();
        } catch (Exception ex) {
            ex.toString();
            existe = false;
        }
        return existe;
    }

    public String obtenerContrasena(long idUsuario){
        String contrasena = "";
        try {
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] params = {String.valueOf(idUsuario)};
            Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_USUARIO+ " WHERE user_id = ?", params);

            if(cursor.moveToFirst()){
                int columnIndex = cursor.getColumnIndex("contrasena");
                contrasena = cursor.getString(columnIndex);
            }
            cursor.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return contrasena;
    }

}