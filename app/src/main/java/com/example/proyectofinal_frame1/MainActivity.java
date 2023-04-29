package com.example.proyectofinal_frame1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

        imageButtonArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPArriba();
            }
        });
        imageButtonAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPArriba();
            }
        });
        imageButtonZapatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPAbajo();
            }
        });
        imageButtonComplem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toZapatos();
            }
        });
        imageButtonAccesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAccesorios();
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.armarConjuntosFragment, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //Creación de la base de datos
        ProyectoDatabaseHelper proyectoDBHelper = new ProyectoDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = proyectoDBHelper.getWritableDatabase();

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

    // funcionalidad botones categorias
    /*
    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.imageButtonArriba:
                intent = new Intent(this, RopaSuperiorActivity.class);
                break;
            case R.id.imageButtonAbajo:
                // hacer cuando se haga clic en el imageButtonAbajo
                intent = new Intent(this, RopaInferiorActivity.class);
                break;
            case R.id.imageButtonZapatos:
                intent = new Intent(this, ZapatosActivity.class);
                break;
            case R.id.imageButtonComplem:
                intent = new Intent(this, ComplementosActivity.class);
                break;
            case R.id.imageButtonAccesorios:
                intent = new Intent(this, AccesoriosActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
    */

    public void toPArriba() {
        Intent intent = new Intent(this, RopaSuperiorActivity.class);
        startActivity(intent);
    }

    public void toPAbajo() {
        Intent intent = new Intent(this, RopaInferiorActivity.class);
        startActivity(intent);
    }

    public void toZapatos() {
        Intent intent = new Intent(this, ZapatosActivity.class);
        startActivity(intent);
    }

    public void toAccesorios() {
        Intent intent = new Intent(this, AccesoriosActivity.class);
        startActivity(intent);
    }

}