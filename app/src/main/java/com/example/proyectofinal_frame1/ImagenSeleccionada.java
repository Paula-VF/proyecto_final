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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_seleccionada);

        imagen = (ImageView) findViewById(R.id.imagen);
        imagen.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view){
                boolean pick = true;
                if(pick==true){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else PickImage();
                }else{
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }else PickImage();
                }
            }
        });
    }

    private boolean checkCameraPermission(){
        boolean res1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED;
        boolean res2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
        return res1 && res2;
    }

    private boolean checkStoragePermission(){
        boolean res2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
        return res2;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission(){
        requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission(){
        requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    private void PickImage(){
        // Aquí es donde se haría la imagen png sin fondo
    }

    /*

    Dialog with 2 options (https://stackoverflow.com/questions/10165302/dialog-to-pick-image-from-gallery-or-from-camera)

    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(takePicture, 0);//zero can be replaced with any action code (called requestCode)

    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(pickPhoto , 1);//one can be replaced with any action code

     */

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageview.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageview.setImageURI(selectedImage);
                }
                break;
        }


        if(requestCode == PngImage.PNG_IMAGE_ACTIVITY_REQUEST_CODE){
            PngImage.ActivityResult result = PngImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                // para que se vea el resultado de la imagen en la pantalla
                try{
                    InputStream stream = getContentResolver().openInputStream(resultUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(stream);
                    imagen.setImageBitmap(bitmap);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(resultCode == PngImage.PNG_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }

        }
        */


}