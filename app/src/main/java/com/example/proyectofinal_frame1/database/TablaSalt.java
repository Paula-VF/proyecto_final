package com.example.proyectofinal_frame1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class TablaSalt extends ProyectoDatabaseHelper{
    Context context;
    public TablaSalt(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarSalt(Long user, String salt){
        long id = 0;
        try{
            ProyectoDatabaseHelper dbHelper = new ProyectoDatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("user_id", user);
            values.put("salt", salt);

            id = db.insert(SALT, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
