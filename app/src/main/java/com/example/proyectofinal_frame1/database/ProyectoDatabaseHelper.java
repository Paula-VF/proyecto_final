package com.example.proyectofinal_frame1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProyectoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOMBRE = "proyectoFinal";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLA_CATEGORIA = "categoria";
    public static final String TABLA_PRENDA = "prenda";
    public static final String TABLA_USUARIO = "usuario";
    public static final String TABLA_PRENDASXCONJUNTOS = "prendasXConjuntos";
    public static final String TABLA_CONJUNTO = "conjunto";

    public ProyectoDatabaseHelper(Context context){
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Código para crear las tablas de la base de datos
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLA_CATEGORIA +"(\n" +
                "id	INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
                "nombre	TEXT NOT NULL UNIQUE)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_PRENDA+ "(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "nombre TEXT NOT NULL,\n" +
                "imagen BLOB NOT NULL,\n" +
                "etiquetas TEXT,\n" +
                "categoria INTEGER,\n" +
                "usuario INTEGER NOT NULL,\n" +
                "FOREIGN KEY(categoria) REFERENCES categoria(id) ON DELETE SET NULL,\n" +
                "FOREIGN KEY(usuario) REFERENCES usuario(user_id) ON DELETE CASCADE)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_USUARIO+ "(\n" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "nombre TEXT NOT NULL,\n" +
                "emailTEXT NOT NULL UNIQUE,\n" +
                "contraseña TEXT NOT NULL UNIQUE)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_PRENDASXCONJUNTOS+ "(\n" +
                "id_prenda INTEGER NOT NULL,\n" +
                "id_conjunto INTEGER NOT NULL,\n" +
                "PRIMARY KEY(id_prenda,id_conjunto),\n" +
                "FOREIGN KEY(id_conjunto) REFERENCES conjunto(id) ON DELETE CASCADE,\n" +
                "FOREIGN KEY(id_prenda) REFERENCES prenda(id) ON DELETE CASCADE)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_CONJUNTO+ "(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "nombre TEXT NOT NULL UNIQUE,\n" +
                "usuario INTEGER NOT NULL,\n" +
                "FOREIGN KEY(usuario) REFERENCES usuario(user_id) ON DELETE CASCADE)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Código para actualizar las tablas de la base de datos
    }
}
