package com.example.proyectofinal_frame1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.ViewHolder> {
    private Context context;
    private List<String> rutasImagenes;

    public CarruselAdapter(Context context, List<String> rutasImagenes) {
        this.context=context;
        this.rutasImagenes = rutasImagenes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrusel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String rutaImagen = rutasImagenes.get(position);

        Glide.with(context)
                .load(rutaImagen)
                .centerCrop()
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return rutasImagenes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView);
        }
    }
}
