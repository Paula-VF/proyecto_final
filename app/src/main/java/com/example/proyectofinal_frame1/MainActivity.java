package com.example.proyectofinal_frame1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectofinal_frame1.databinding.ActivityMainBinding;

//  implements View.OnClickListener
public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    TablaPrenda prenda;
    TablaCategoria categoria;
    TablaUsuario user;



    private ImageView imagen;
    private ActivityMainBinding binding;

    private BottomNavigationView navView;

    public MainActivity(){

    }

    FrameLayout frameLayout; // https://abhiandroid.com/programming/camera

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_dashboard:
                    replaceFragment(new DashboardFragment());
                    break;
                case R.id.armarConjuntosFragment:
                    replaceFragment(new ArmarConjuntosFragment());
                    break;
                case R.id.navigation_notifications:
                    replaceFragment(new NotificationsFragment());
                    break;
            }
            return true;
        });
         */


        // para que se muestre nuestra barra superior:
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        imagen = (ImageView) findViewById(R.id.imagen);
//        registerResult(); Esto creo que se tiene que borrar



        //Creación de la base de datos
        ProyectoDatabaseHelper proyectoDBHelper = new ProyectoDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = proyectoDBHelper.getWritableDatabase();

        prenda = new TablaPrenda(this);
        categoria = new TablaCategoria(this);
        user = new TablaUsuario(this);
//
//        String rutaImagen = "https://www.trajesguzman.com/media/1624/camisa-basica-blanca.jpg";
//        long idUser = user.insertarUsuario("emely", "mijij", "okey");
//        long id= prenda.insertarPrenda("Camisa", rutaImagen, "verano", 1, idUser);
//        long id2= prenda.insertarPrenda("Camisa", "https://hmperu.vtexassets.com/arquivos/ids/3024520-483-725/Pantalon-de-buzo---Rosado---H-M-PE.jpg?v=637983998116170000", "verano", 1, idUser);


        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.armarConjuntosFragment, R.id.navigation_notifications,
                R.id.accesoriosLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public BottomNavigationView getNavView() {
        return navView;
    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        fragmentTransaction.commit();
    }

    // para que aparezcan los iconos de toolbar_prendas en el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_prendas, menu);
        return true;
    }

    //AlertDialogs
    private AlertDialog alertDialogSeleccionCamGal;
    private AlertDialog alertDialogAniadirDatosPrenda;

    //camara
    private static final int CAMERA_REQUEST_CODE = 1;

    // funcionalidad botón toolbar_prendas
    String currentPhotoPath;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //Añadir foto

        if(id == R.id.AddBtn){
            Intent intent = new Intent(MainActivity.this, AniadirActivity.class);
            startActivity(intent);
            return true;
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            View dView = getLayoutInflater().inflate(R.layout.seleccionar_imagen, null);
//            Button camara = (Button) dView.findViewById(R.id.btnCamara);
//            Button galeria = (Button) dView.findViewById(R.id.btnGaleria);
//
//            camara.setOnClickListener(new View.OnClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.O)
//                @Override
//                public void onClick(View v) {
//                    camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
//                    abrirDialogDatos();
//                }
//            });
//
//            galeria.setOnClickListener(new View.OnClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.O)
//                @Override
//                public void onClick(View v) {
//                    galeriaLauncher.launch("image/*");
//                }
//            });
//
//            builder.setView(dView);
//            alertDialogSeleccionCamGal = builder.create();
//            alertDialogSeleccionCamGal.show();
        }
        return super.onOptionsItemSelected(item);
    }

//

//    ActivityResultLauncher<String> galeriaLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//        @Override
//        public void onActivityResult(Uri result) {
//            String rutaImagen = obtenerRutaDeImagen(result);
//            //prenda.insertarPrenda("nombreG", rutaImagen, "etiquetas", 1,1);
//            imagen.setImageURI(result);
//        }
//    });

    private boolean checkCameraPermission(){
        boolean res1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED;
        return res1;
    }

    private boolean checkStoragePermission(){
        boolean res2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES)== PackageManager.PERMISSION_GRANTED;
        return res2;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void requestCameraPermission(){
        requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_MEDIA_IMAGES}, 100);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void requestStoragePermission(){
        requestPermissions(new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, 100);
    }

//    private void abrirDialogDatos(){
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        View dView = getLayoutInflater().inflate(R.layout.insercion_datos_prenda, null);
//        EditText nombrePrenda = dView.findViewById(R.id.nombrePrenda);
//        ImageView imagenPrendaDialog = dView.findViewById(R.id.imagenPrenda);
//        imagenPrendaDialog.setImageBitmap(imgBitmap);
//
//        String nombre = nombrePrenda.getText().toString();
//        //chip con subcategorias
//
//        builder.setView(dView);
//        alertDialogAniadirDatosPrenda = builder.create();
//        alertDialogAniadirDatosPrenda.show();
//        prenda.insertarPrenda(nombre, rutaImagen, 1, 1);
//
//    }

    public void toAniadirImagen(View view){
        Intent intent = new Intent(MainActivity.this, AniadirActivity.class);
        startActivity(intent);
    }



}