package com.example.proyectofinal_frame1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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

import com.example.proyectofinal_frame1.database.TablaConjunto;
import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.google.android.material.chip.ChipGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AniadirActivity extends AppCompatActivity {

    private Prenda prenda;
    private EditText nombrePrenda;
    private Button btnCamara, btnGaleria, btnGuardar, btnPNG;

    private ChipGroup categoriaChipGroup;
    private String rutaImagen;
    private ImageView imagenViewPrenda;
    private Bitmap imgBitmap;
    private ImageView btnBack;
    private TablaPrenda tablaPrenda = new TablaPrenda(this);
    private TablaConjunto tablaConjunto = new TablaConjunto(this);
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private File myPath;

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
        btnCamara = findViewById(R.id.btnCamara);
        btnGaleria = findViewById(R.id.btnGaleria);
        imagenViewPrenda = findViewById(R.id.imagenPrenda);
        categoriaChipGroup = findViewById(R.id.chipGroupCategorias);
        btnGuardar = findViewById(R.id.btn_guardar);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                onActivityResult(REQUEST_IMAGE_CAPTURE, RESULT_OK, intent);

                permisoCamara();
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

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prenda.setNombre(nombrePrenda.getText().toString().trim());
                int chipId = categoriaChipGroup.getCheckedChipId();
                prenda.setCategoria(obtenerCategoria(chipId));
                if (prenda.getNombre().isEmpty()) {
                    nombrePrenda.setError("Por favor introduzca un nombre descriptivo a la prenda");
                    nombrePrenda.requestFocus();
                } else if (prenda.getUrlImagen() == null || prenda.getUrlImagen().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ninguna imagen seleccionada", Toast.LENGTH_SHORT).show();
                } else if (prenda.getCategoria() != 0 && prenda.getCategoria() > 0) {
                    Toast.makeText(AniadirActivity.this, "Todo ok", Toast.LENGTH_SHORT).show();
                    long idPrenda = tablaPrenda.insertarPrenda(prenda.getNombre(), prenda.getUrlImagen(), prenda.getCategoria(), 1);
                    if (idPrenda != 0) {
                        Toast.makeText(AniadirActivity.this, "Prenda guardada", Toast.LENGTH_SHORT).show();
                        nombrePrenda.getText().clear();
                        categoriaChipGroup.clearCheck();
                        imagenViewPrenda.setImageResource(android.R.drawable.ic_menu_gallery);
                        prenda = new Prenda();
                    }

                    //Falta guardar la imagen en la bd. Porque falta terminar de finiquitar la funcionalidad de usuario

                } else {
                    Toast.makeText(AniadirActivity.this, "Selecciona una categoría. Categoria: " + prenda.getCategoria() + "chipID: " + chipId, Toast.LENGTH_SHORT).show();
                    categoriaChipGroup.requestFocus();
                }
            }
        });
    }


    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    new ActivityResultContracts.RequestPermission();
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        imgBitmap = (Bitmap) extras.get("data");
                        rutaImagen = guardarImagenEnAlmacenamientoInterno(imgBitmap);
                        prenda.setUrlImagen(rutaImagen);
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
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri imageUri = data.getData();
                        imagenViewPrenda.setImageURI(imageUri);
                        //prenda.setUrlImagen(obtenerRutaDeImagen(imageUri).toString()+"/remove-bg");
                        prenda.setUrlImagen(obtenerRutaDeImagen(imageUri));
                    } else {
                        Toast.makeText(AniadirActivity.this, "Operación canceleda", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    private String guardarImagenEnAlmacenamientoInterno(Bitmap bitmap) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imagenes", Context.MODE_PRIVATE);
        String fileName = "imagen_" + System.currentTimeMillis() + ".jpg";
        myPath = new File(directory, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.flush();
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
            camaraLauncher.launch(intent);
        }else{
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 200);
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
        long categoria;
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
                categoria = 0;
                break;
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