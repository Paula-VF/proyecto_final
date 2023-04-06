package com.example.proyectofinal_frame1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrendaAdapter extends RecyclerView.Adapter<PrendaAdapter.PrendaViewHolder>{
    private List<Prenda> prendas;

    public PrendaAdapter(List<Prenda> prendas) {
        this.prendas = prendas;
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

    public static class PrendaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nombreTextView;
        private TextView textViewEtiqueta1;
        private TextView textViewEtiqueta2;
        private TextView textViewEtiqueta3;

        public PrendaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPrenda);
            nombreTextView = itemView.findViewById(R.id.textViewNombrePrenda);
            textViewEtiqueta1 = itemView.findViewById(R.id.textViewEtiqueta1);
            textViewEtiqueta2 = itemView.findViewById(R.id.textViewEtiqueta2);
            textViewEtiqueta3 = itemView.findViewById(R.id.textViewEtiqueta3);
        }

        public void bind(Prenda prenda) {
            imageView.setImageResource(prenda.getUrlImagen());
            nombreTextView.setText(prenda.getNombre());
            List<String> etiquetas = prenda.getEtiquetas();
            if (etiquetas.size() > 0) {
                textViewEtiqueta1.setText(etiquetas.get(0));
            }
            if (etiquetas.size() > 1) {
                textViewEtiqueta2.setText(etiquetas.get(1));
            }
            if (etiquetas.size() > 2) {
                textViewEtiqueta3.setText(etiquetas.get(2));
            }
        }
    }
}
