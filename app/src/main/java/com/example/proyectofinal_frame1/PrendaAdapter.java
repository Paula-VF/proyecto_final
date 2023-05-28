package com.example.proyectofinal_frame1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PrendaAdapter extends RecyclerView.Adapter<PrendaAdapter.PrendaViewHolder>{
    private List<Prenda> prendas;

    public PrendaAdapter(List<Prenda> prendas){
        this.prendas= prendas;
    }

    @NonNull
    @Override
    public PrendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prenda, parent, false);
        return new PrendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrendaViewHolder holder, int position) {
        Prenda prenda = prendas.get(position);
        holder.bind(prenda);
    }

    @Override
    public int getItemCount() {
        return prendas.size();
    }

    // ViewHolder
    public static class PrendaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nombreTextView;

        public PrendaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPrenda);
            nombreTextView = itemView.findViewById(R.id.textViewNombrePrenda);
        }

        public void bind(Prenda prenda) {
            Glide.with(itemView)
                    .load(prenda.getUrlImagen())
                    .into(imageView);
            nombreTextView.setText(prenda.getNombre());
        }
    }
}
