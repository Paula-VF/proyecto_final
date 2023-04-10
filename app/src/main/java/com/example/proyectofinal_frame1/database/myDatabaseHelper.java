package com.example.proyectofinal_frame1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "proyectoFinal";
    private static final int DATABASE_VERSION = 1;

    public myDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Código para crear las tablas de la base de datos
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Código para actualizar las tablas de la base de datos


    }

    //En la actividad o fragmento donde se quiera acceder a la base de datos usar:
    /*
    MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
    SQLiteDatabase db = dbHelper.getWritableDatabase();
        * Código para insertar, actualizar o leer datos en la base de datos
    db.close();
    *
    * */
}
