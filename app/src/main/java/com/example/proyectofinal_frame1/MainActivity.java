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
import android.widget.Button;
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


        // para que se muestre nuestra barra superior:
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        imagen = (ImageView) findViewById(R.id.imagenPrenda);
//        registerResult(); Esto creo que se tiene que borrar



        //Creación de la base de datos
        ProyectoDatabaseHelper proyectoDBHelper = new ProyectoDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = proyectoDBHelper.getWritableDatabase();

        //Ejemplos prendas
        prenda = new TablaPrenda(this);
        user = new TablaUsuario(this);

        long idUser = user.insertarUsuario("emely", "mijij", "okey");
        prenda.insertarPrenda("Camisa", "https://www.trajesguzman.com/media/1624/camisa-basica-blanca.jpg", 1, idUser);
        prenda.insertarPrenda("Camiseta blanca", "https://www.disfracesantifaz.com/2038-large_default/camiseta-chica-blanca.jpg", 1, idUser);
        prenda.insertarPrenda("Blusa verde", "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fcc%2Fdc%2Fccdcffa050547931b6cc535b6f4bef0d270e12ee.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_shirtsblouses_blouses%5D%2Ctype%5BDESCRIPTIVESTILLLIFE%5D%2Cres%5Bm%5D%2Chmver%5B2%5D&call=url[file:/product/main]", 1, idUser);
        prenda.insertarPrenda("Chandal rosa", "https://hmperu.vtexassets.com/arquivos/ids/3024520-483-725/Pantalon-de-buzo---Rosado---H-M-PE.jpg?v=637983998116170000", 2, idUser);
        //prenda.insertarPrenda("vaqueros pitillo", "https://media.vogue.es/photos/5cc7367f92f813c7264e4eb6/master/w_1280,c_limit/pantalones_vaqueros_pitillo_mango_basicos_armario_2019_7371.jpg", 2, idUser);
        prenda.insertarPrenda("pantalones blancos", "https://media.vogue.es/photos/5cc750398f6f6723b65bea04/master/w_1280,c_limit/pantalones_blancos_929.jpg", 2, idUser);
        prenda.insertarPrenda("Converse blancas", "https://media.revistagq.com/photos/6093ca05235a5910299c929c/master/w_1280,c_limit/converse.jpeg", 3, idUser);
        prenda.insertarPrenda("Tacón lunares", "https://www.angari.es/wp-content/uploads/2018/09/zapatos-lunares-negros-con-lazos.jpg", 3, idUser);



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


    // para que aparezcan los iconos de toolbar_prendas en el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_prendas, menu);
        return true;
    }

    // funcionalidad botones toolbar_prendas
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //Añadir prenda
        if(id == R.id.AddBtn){
            Intent intent = new Intent(MainActivity.this, AniadirActivity.class);
            startActivity(intent);
            return true;
        }

        // eliminar prenda/conjunto
        if(id == R.id.btn_delete){
            Button delete = findViewById(R.id.btn_delete);
            delete.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
}