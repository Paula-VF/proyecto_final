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
        private EditText writeName;
        private Button btnAdded;

        Context context;

        public SubcategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            writeName = itemView.findViewById(R.id.write_name);
            btnAdded = itemView.findViewById(R.id.btn_added);

            // al mantener un botón de subcategoría pulsado
            btnAdded.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    writeName.setVisibility(View.VISIBLE);
                    writeName.requestFocus();
                    showKeyboard();

                    writeName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                // al pinchar fuera del teclado o de writeName
                                closeKeyboard();
                                btnAdded.setText(writeName.getText().toString().toUpperCase());// cambio texto de botón
                                btnAdded.setVisibility(View.VISIBLE);
                                writeName.setVisibility(View.GONE);
                                writeName.setText(null);
                                // añadir nueva subcategoría a la bd
                            }
                        }
                    });


                    writeName.setOnKeyListener(new View.OnKeyListener() {
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            // If the event is a key-down event on the "enter" button
                            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                // acciones a realizar al presionar enter
                                closeKeyboard();
                                btnAdded.setText(writeName.getText().toString().toUpperCase());// cambio texto de botón
                                btnAdded.setVisibility(View.VISIBLE);
                                writeName.setVisibility(View.GONE);
                                writeName.setText(null);
                                // añadir nueva subcategoría a la bd
                                return true;
                            }
                            return false;
                        }
                    });

                    return true;
                }
            });
        }

        public void bind(Subcategoria subcategoria) {
        }

        // mostrar teclado
        public void showKeyboard(){
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
        // ocultar teclado
        public void closeKeyboard(){
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }
}
