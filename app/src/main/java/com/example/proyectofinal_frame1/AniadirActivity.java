package com.example.proyectofinal_frame1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
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
    private EditText nombrePrenda;
    private Button btnCamara, btnGaleria, btnGuardar;

    private ChipGroup categoriaChipGroup;
    private ChipGroup subcategoriaChipGroup;
    private String rutaImagen;
    private ImageView imagenViewPrenda;
    private Bitmap imgBitmap;
    private ImageView btnBack;
    private long categoria;

    private static final int REQUEST_CAMERA_PERMISSION_CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);
        prenda = new Prenda();


        btnBack = findViewById(R.id.btn_back);
        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });

        nombrePrenda = findViewById(R.id.nombrePrenda);
        btnCamara= findViewById(R.id.btnCamara);
        btnGaleria= findViewById(R.id.btnGaleria);
        imagenViewPrenda = findViewById(R.id.imagenPrenda);
        categoriaChipGroup = findViewById(R.id.chipGroupCategorias);
        btnGuardar = findViewById(R.id.btn_guardar);

        int chipId = categoriaChipGroup.getCheckedChipId();

        btnCamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    onActivityResult(REQUEST_IMAGE_CAPTURE, RESULT_OK, intent);

                }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    permisoGaleria();
                }
            }
        });


        categoriaChipGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subcategoriaChipGroup.setVisibility(v.VISIBLE);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagenViewPrenda.getDrawable() == null){
                    Toast.makeText(getApplicationContext(), "Ninguna imagen seleccionada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        imagenViewPrenda.setImageBitmap(imageBitmap);
        */

        /*
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagenViewPrenda.setImageBitmap(imageBitmap);
        }
         */

        /*
        try {
            String mPath = getExternalStorageDirectory().toString() + "/" + fecha + ".png";
            direccion_imagen = "/" + fecha + ".png";
            View u = findViewById(R.id.constrain_screen);
            u.setDrawingCacheEnabled(true);
            u.buildDrawingCache(true);
            Bitmap bitmap = Bitmap.createBitmap(u.getDrawingCache());
            u.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Throwable e) {
            Toast.makeText(this, "ERROR al intentar generar imagen .png", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
         */
    }


    ActivityResultLauncher<String> camaraPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result){
                        //camaraLauncher.launch(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Aceptar los permisos para introducir imágenes", Toast.LENGTH_SHORT).show();
                    }
                }
            });




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
            //ActivityCompat.shouldShowRequestPermissionRationale(this, com.example.proyectofinal_frame1.Manifest.permission.CAMERA)
            new ActivityResultContracts.RequestPermission();
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                Uri imageUri = data.getData();
                imagenViewPrenda.setImageURI(imageUri);
                prenda.setUrlImagen(obtenerRutaDeImagen(imageUri));

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


    // permisos cámara y galería
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void permisoCamara(){
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permiso == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.setType("image/*");
            camaraLauncher.launch(intent);
        }else{
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void permisoGaleria(){
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permiso == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galeriaLauncher.launch(intent);
        }else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    private long obtenerCategoria(int chipId){
        switch (chipId) {
            case R.id.chipParteSuperior:
                categoria = 1;
                break;
            case R.id.chipParteInferior:
                categoria = 2;
                break;
            case R.id.chipZapatos:
                categoria = 3;
                break;
            case R.id.chipComplementos:
                categoria = 4;
                break;
            case R.id.chipAccesorios:
                categoria = 5;
                break;
            default:
                Toast.makeText(AniadirActivity.this, "No se ha seleccionado categoria", Toast.LENGTH_SHORT).show();
        }
        return categoria;
    }


    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}