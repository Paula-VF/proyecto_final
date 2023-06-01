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

public class  ConjuntoAdapter extends RecyclerView.Adapter<ConjuntoAdapter.ConjuntoViewHolder>{
    private List<ConjuntoItem> conjuntos;
    private OnConjuntoListener mOnConjuntoListener;

    public ConjuntoAdapter(List<ConjuntoItem> conjuntos, OnConjuntoListener onConjuntoListener) {
        this.conjuntos = conjuntos;
        this.mOnConjuntoListener = onConjuntoListener;
    }

    @NonNull
    @Override
    public ConjuntoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prenda, parent, false);
        return new ConjuntoViewHolder(view, mOnConjuntoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConjuntoViewHolder holder, int position) {
        ConjuntoItem conjuntoItem = conjuntos.get(position);
        holder.bind(conjuntoItem);
    }

    @Override
    public int getItemCount() {
        return conjuntos.size();
    }

    // ViewHolder
    public static class ConjuntoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ImageView imageView;
        private TextView nombreTextView;
        private OnConjuntoListener conjuntoListener;
        public ConjuntoViewHolder(@NonNull View itemView, OnConjuntoListener conjuntoListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPrenda);
            nombreTextView = itemView.findViewById(R.id.textViewNombrePrenda);
            this.conjuntoListener = conjuntoListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind(ConjuntoItem prenda) {
            Glide.with(itemView)
                    .load(prenda.getUrlImagen())
                    .into(imageView);
            nombreTextView.setText(prenda.getNombre());
        }

        @Override
        public void onClick(View v) {
            conjuntoListener.onConjuntoClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            conjuntoListener.onConjuntoLongClick(getAdapterPosition());
            return false;
        }
    }


    public interface OnConjuntoListener {
        void onConjuntoClick(int position);
        void onConjuntoLongClick(int position);
    }



}
