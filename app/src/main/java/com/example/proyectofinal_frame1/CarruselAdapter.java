package com.example.proyectofinal_frame1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private int[] imagenes;

    public CarruselAdapter(Context context, int[] imagenes) {
        this.inflater = LayoutInflater.from(context);
        this.imagenes = imagenes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_carrusel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imagen.setImageResource(imagenes[position]);
    }

    @Override
    public int getItemCount() {
        return imagenes.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView);
        }
    }
}
