package com.example.proyectofinal_frame1;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubcategoriaAdapter extends RecyclerView.Adapter<SubcategoriaAdapter.SubcategoriaViewHolder>{
    private List<Subcategoria> subcategorias;
    private Context context;

    public SubcategoriaAdapter(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
        this.context = context;
    }

    // view/layout a visualizar
    @NonNull
    @Override
    public SubcategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategoria, parent, false);
        return new SubcategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoriaViewHolder holder, int position) {
        Subcategoria subcategoria = subcategorias.get(position);
        holder.bind(subcategoria);
    }

    @Override
    public int getItemCount() {
        return subcategorias.size();
    }




    // ViewHolder
    public static class SubcategoriaViewHolder extends RecyclerView.ViewHolder {

        private Button btnAdded;

        Context context;

        public SubcategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            btnAdded = itemView.findViewById(R.id.btn_added);

            //btnAdded.setVisibility(View.VISIBLE);

            // al mantener un botón de subcategoría pulsado
            /*
            btnAdded.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    btnAdded.setVisibility(View.INVISIBLE);

                    return true;
                }
            });

             */

        }

        public void bind(Subcategoria subcategoria) {
        }


    }

}
