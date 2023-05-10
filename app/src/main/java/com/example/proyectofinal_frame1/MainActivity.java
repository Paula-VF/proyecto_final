package com.example.proyectofinal_frame1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.ProyectoDatabaseHelper;

import com.example.proyectofinal_frame1.database.TablaCategoria;
import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.example.proyectofinal_frame1.database.TablaUsuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectofinal_frame1.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//  implements View.OnClickListener
public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    ActivityResultLauncher<Intent> resultLauncher;
    private ImageView imagen;
    private ActivityMainBinding binding;

    private ImageButton imageButtonArriba;
    private ImageButton imageButtonAbajo;
    private ImageButton imageButtonZapatos;
    private ImageButton imageButtonComplem;
    private ImageButton imageButtonAccesorios;

    FrameLayout frameLayout; // https://abhiandroid.com/programming/camera

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // para que se muestre nuestra barra superior:
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        imagen = (ImageView) findViewById(R.id.imagen);
//        registerResult(); Esto creo que se tiene que borrar

        // categorías
        imageButtonArriba = (ImageButton) findViewById(R.id.imageButtonArriba);
        imageButtonAbajo = (ImageButton) findViewById(R.id.imageButtonAbajo);
        imageButtonZapatos = (ImageButton) findViewById(R.id.imageButtonZapatos);
        imageButtonComplem = (ImageButton) findViewById(R.id.imageButtonComplem);
        imageButtonAccesorios = (ImageButton) findViewById(R.id.imageButtonAccesorios);
        // funcionalidad botones categorias
        imageButtonArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RopaSuperiorActivity.class);
                startActivity(intent);
            }
        });
        imageButtonAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RopaInferiorActivity.class);
                startActivity(intent);
            }
        });
        imageButtonZapatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZapatosActivity.class);
                startActivity(intent);
            }
        });
        imageButtonComplem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComplementosActivity.class);
                startActivity(intent);
            }
        });
        imageButtonAccesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccesoriosActivity.class);
                startActivity(intent);
            }
        });

        //Creación de la base de datos
        ProyectoDatabaseHelper proyectoDBHelper = new ProyectoDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = proyectoDBHelper.getWritableDatabase();
        if(db!=null){
            Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
        }

        //Pruebas de la base de datos
//        TablaPrenda prenda = new TablaPrenda(this);
//        TablaCategoria categoria = new TablaCategoria(this);
//        TablaUsuario user = new TablaUsuario(this);
//
//        String rutaImagen = "https://www.trajesguzman.com/media/1624/camisa-basica-blanca.jpg";
//        long idUser = user.insertarUsuario("emely", "mijij", "okey");
//        long id= prenda.insertarPrenda("Camisa", rutaImagen, "verano", 1, idUser);
//        long id2= prenda.insertarPrenda("Camisa", "https://hmperu.vtexassets.com/arquivos/ids/3024520-483-725/Pantalon-de-buzo---Rosado---H-M-PE.jpg?v=637983998116170000", "verano", 1, idUser);



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.armarConjuntosFragment, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public static final int PICK_IMAGE = 2; // para saber cuando el usuario elige una foto
    static final int REQUEST_IMAGE_CAPTURE = 1; // para saber cuando el usuario toma una foto
    Bitmap bitmap;
    // para que aparezcan los iconos de toolbar_prendas en el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_prendas, menu);
        return true;
    }


    String currentPhotoPath;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //Añadir foto

        if(id == R.id.AddBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View dView = getLayoutInflater().inflate(R.layout.seleccionar_imagen, null);
            Button camara = (Button) dView.findViewById(R.id.btnCamara);
            Button galeria = (Button) dView.findViewById(R.id.btnGaleria);

            camara.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                }
            });
            galeria.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    galeriaLauncher.launch("image/*");
                }
            });

            builder.setView(dView);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if(id == R.id.FilterBtn){
            Toast.makeText(this, "Filtrar", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.SearchBtn){
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Bundle extras = result.getData().getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");
                imagen.setImageBitmap(imgBitmap);
            }
        }
    });

    ActivityResultLauncher<String> galeriaLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            imagen.setImageURI(result);
        }
    });

//    private boolean checkCameraPermission(){
//        boolean res1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED;
//        boolean res2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES)== PackageManager.PERMISSION_GRANTED;
//        return res1 && res2;
//    }
//
//    private boolean checkStoragePermission(){
//        boolean res2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES)== PackageManager.PERMISSION_GRANTED;
//        return res2;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void requestCameraPermission(){
//        requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_MEDIA_IMAGES}, 100);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void requestStoragePermission(){
//        requestPermissions(new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, 100);
//        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES)== PackageManager.PERMISSION_GRANTED){
//            pickImage();
//        }
//    }

}