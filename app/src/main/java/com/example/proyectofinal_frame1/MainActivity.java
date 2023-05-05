package com.example.proyectofinal_frame1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.ProyectoDatabaseHelper;

//import com.denzcoskun.imageslider.ImageSlider;
//import com.denzcoskun.imageslider.constants.AnimationTypes;
//import com.denzcoskun.imageslider.constants.ScaleTypes;
//import com.denzcoskun.imageslider.interfaces.ItemClickListener;
//import com.denzcoskun.imageslider.models.SlideModel;

import com.example.proyectofinal_frame1.database.TablaCategoria;
import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.example.proyectofinal_frame1.database.TablaUsuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectofinal_frame1.databinding.ActivityMainBinding;

//  implements View.OnClickListener
public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private ActivityMainBinding binding;

    private ImageButton imageButtonArriba;
    private ImageButton imageButtonAbajo;
    private ImageButton imageButtonZapatos;
    private ImageButton imageButtonComplem;
    private ImageButton imageButtonAccesorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // para que se muestre nuestra barra superior:
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
//        long idCategoria = categoria.insertarCategoria("superior");
//        long idUser = user.insertarUsuario("emely", "mijij", "okey");
//        long id= prenda.insertarPrenda("Camisa", rutaImagen, "verano", idCategoria, idUser);
//        long id2= prenda.insertarPrenda("Camisa", "https://hmperu.vtexassets.com/arquivos/ids/3024520-483-725/Pantalon-de-buzo---Rosado---H-M-PE.jpg?v=637983998116170000", "verano", idCategoria, idUser);

        //

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

    // para que aparezcan los iconos de toolbar_prendas en el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_prendas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.AddBtn){
            Toast.makeText(this, "Añadir", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.FilterBtn){
            Toast.makeText(this, "Filtrar", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.SearchBtn){
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
        }
        return true;
    }




}