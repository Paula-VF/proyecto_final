package com.example.proyectofinal_frame1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class ImagenSeleccionada extends AppCompatActivity {

    ImageView imagen;
    String imageRetrieved = getIntent().getData().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_seleccionada);

        imagen = (ImageView) findViewById(R.id.imagen);

        /*
        if(getIntent()!= null){
            Uri uri = getIntent().getData();
            imagen.setImageURI(uri);

        }


        Bundle extras = getIntent().getExtras();
        Uri myUri = Uri.parse(extras.getString("imageUri"));
        imagen.setImageURI(myUri);
        */


    }


}