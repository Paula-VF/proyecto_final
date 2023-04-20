package com.example.proyectofinal_frame1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ImagenFragment extends Fragment {
    private int imagenResource;

    public ImagenFragment(int imagenResource) {
        this.imagenResource = imagenResource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_carrusel, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(imagenResource);
        return view;
    }
}
