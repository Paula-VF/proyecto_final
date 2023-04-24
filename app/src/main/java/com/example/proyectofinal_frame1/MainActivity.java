package com.example.proyectofinal_frame1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.ProyectoDatabaseHelper;

//import com.denzcoskun.imageslider.ImageSlider;
//import com.denzcoskun.imageslider.constants.AnimationTypes;
//import com.denzcoskun.imageslider.constants.ScaleTypes;
//import com.denzcoskun.imageslider.interfaces.ItemClickListener;
//import com.denzcoskun.imageslider.models.SlideModel;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_frame1.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//  implements View.OnClickListener
public class MainActivity extends AppCompatActivity{

    private Toolbar myToolbar;
    private ActivityMainBinding binding;

    /* variables del otro carrusel:
    private ImageSwitcher imageSwitcher;
    private MaterialButton nextBtn;
    private Integer ListImage[] = {R.drawable.prenda1, R.drawable.prenda2, R.drawable.prenda3, R.drawable.prenda4, R.drawable.prenda5,
    R.drawable.prenda6, R.drawable.prenda7, R.drawable.prenda8, R.drawable.prenda9, R.drawable.prenda10};

    private Integer imageLength = ListImage.length;
    private Integer counter = -1;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // para que se muestre nuestra barra superior:
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Carrusel
        //ImageSlider imageslider = findViewById(R.id.slider);
        //List<SlideModel> slideModels = new ArrayList<>();
        //slideModels.add(new SlideModel(R.drawable.prenda1, ScaleTypes.FIT));
        //slideModels.add(new SlideModel(R.drawable.prenda2, ScaleTypes.FIT));

        //imageslider.setImageList(slideModels);

        //imageslider.setSlideAnimation(AnimationTypes.ZOOM_OUT);

        /* Esto es del mismo carrusel, pero estaba en kotlin y no lo llegue a pasar a java
        Es de este repositorio: https://github.com/denzcoskun/ImageSlideshow

        imageslider.setItemClickListener(ItemClickListener() {
            @Override
            public onItemSelected selectedItem(){
                // You can listen here.
                System.out.println("normal");
            }
            @Override
            doubleClick(position: Int) {
                // Do not use onItemSelected if you are using a double click listener at the same time.
                // Its just added for specific cases.
                // Listen for clicks under 250 milliseconds.
                System.out.println("its double");
            }
        });

        imageslider.setItemChangeListener(object : ItemChangeListener {
            @Override
            onItemChanged(position: Int) {
                //println("Pos: " + position);
            }
        });

        imageslider.setTouchListener(object : TouchListener {
            @Override
            onTouched(touched: ActionTypes, position: Int) {
                if (touched == ActionTypes.DOWN){
                    imageslider.stopSliding();
                } else if (touched == ActionTypes.UP ) {
                    imageslider.startSliding(1000);
                }
            }
        });
         */

        /* Otro carrusel

        imageSwitcher = findViewById(R.id.imageSwitcher);
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));

                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageResource(R.drawable.prenda1);

                return imageView;
            }
        });

        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        imageSwitcher.setOutAnimation(out);
        imageSwitcher.setInAnimation(in);
         */

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.carouselFragment, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        //agregar un evento para navegar a la actividad CarruselActivity cuando se seleccione el botón con id carouselFragment
        navView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.carouselFragment) {
                Intent intent = new Intent(this, CarruselActivity.class);
                startActivity(intent);
                return true;
            } else {
                return NavigationUI.onNavDestinationSelected(item, navController);
            }
        });

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

    public void toPArriba(View view) {
        Intent intent = new Intent(this, RopaSuperiorActivity.class);
        startActivity(intent);
    }

    public void toPAbajo(View view) {
        Intent intent = new Intent(this, RopaInferiorActivity.class);
        startActivity(intent);
    }

    public void toZapatos(View view) {
        Intent intent = new Intent(this, ZapatosActivity.class);
        startActivity(intent);
    }

    public void toAccesorios(View view) {
        Intent intent = new Intent(this, AccesoriosActivity.class);
        startActivity(intent);
    }

    /*
    Carousel

    @Override
    public void onClick(View v) {
        counter ++;

        if(counter == imageLength){
            counter = 0;
            imageSwitcher.setImageResource(ListImage[counter]);
        }else {
            imageSwitcher.setImageResource(ListImage[counter]);
        }
    }

    carousel.setAdapter(new Carousel.Adapter() {
        @Override
        public int count() {
            // need to return the number of items we have in the carousel
        }

        @Override
        public void populate(View view, int index) {
            // need to implement this to populate the view at the given index
        }

        @Override
        public void onNewItem(int index) {
            // called when an item is set
        }
    });

     */

}