package com.example.proyectofinal_frame1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AniadirActivity extends AppCompatActivity {

    private Prenda prenda;
    private EditText nombrePrendaField;
    private Button btnCamara, btnGaleria;

    private ChipGroup categoriaChipGroup;
    private String rutaImagen;
    private ImageView imagenViewPrenda;
    private Bitmap imgBitmap;
    private ImageView btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);
        prenda = new Prenda();



        categoriaChipGroup = findViewById(R.id.chipGroupCat);
        int id = categoriaChipGroup.getCheckedChipId();

        btnBack = findViewById(R.id.btn_back);
        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        nombrePrendaField = findViewById(R.id.nombrePrenda);
        btnCamara= findViewById(R.id.btnCamara);
        btnGaleria= findViewById(R.id.btnGaleria);
        imagenViewPrenda = findViewById(R.id.imagenPrenda);

        btnCamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                }
            });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pickMedia.launch(new PickVisualMediaRequest());
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                galeriaLauncher.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Bundle extras = result.getData().getExtras();
                imgBitmap = (Bitmap) extras.get("data");
                rutaImagen = guardarImagenEnAlmacenamientoInterno(imgBitmap);
                imagenViewPrenda.setImageBitmap(imgBitmap);
            }
        }
    });

    ActivityResultLauncher<Intent> galeriaLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                Uri imageUri = data.getData();
                imagenViewPrenda.setImageURI(imageUri);
                prenda.setUrlImagen(obtenerRutaDeImagen(imageUri);

            }else {
                Toast.makeText(AniadirActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    });

    private String guardarImagenEnAlmacenamientoInterno(Bitmap bitmap) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imagenes", Context.MODE_PRIVATE);
        String fileName = "imagen_" + System.currentTimeMillis() + ".jpg";
        File myPath = new File(directory, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myPath.getAbsolutePath();
    }

    private String obtenerRutaDeImagen(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        cursor.close();
        return filePath;
    }




    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}