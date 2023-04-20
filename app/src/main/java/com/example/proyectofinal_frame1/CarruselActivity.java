package com.example.proyectofinal_frame1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class CarruselActivity extends AppCompatActivity {
    private ViewPager2 viewPager;

    private int[] imagenes = {R.drawable.prenda1, R.drawable.prenda2, R.drawable.prenda3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrusel);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new CarruselAdapter(this, imagenes));
    }
}
