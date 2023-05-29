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
    private OnPrendaListener mOnPrendaListener;
    public PrendaAdapter(List<Prenda> prendas, OnPrendaListener onPrendaListener){
        this.prendas= prendas;
        this.mOnPrendaListener = onPrendaListener;
    }

    @NonNull
    @Override
    public PrendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prenda, parent, false);
        return new PrendaViewHolder(view, mOnPrendaListener);
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
    public static class PrendaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imageView;
        private TextView nombreTextView;
        private OnPrendaListener onPrendaListener;

        public PrendaViewHolder(@NonNull View itemView, OnPrendaListener onPrendaListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPrenda);
            nombreTextView = itemView.findViewById(R.id.textViewNombrePrenda);
            this.onPrendaListener = onPrendaListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind(Prenda prenda) {
            Glide.with(itemView)
                    .load(prenda.getUrlImagen())
                    .into(imageView);
            nombreTextView.setText(prenda.getNombre());
        }

        @Override
        public void onClick(View v) {
            onPrendaListener.onPrendaClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onPrendaListener.onPrendaLongClick(getAdapterPosition());
            return false;
        }
    }

    public interface OnPrendaListener {
        void onPrendaClick(int position);
        void onPrendaLongClick(int position);
    }
}
