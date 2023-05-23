package com.example.proyectofinal_frame1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.Subcategoria;
import com.example.proyectofinal_frame1.SubcategoriaAdapter;

import java.util.ArrayList;

public class SubcategoriasAdapter extends BaseAdapter {

    Context context;
    ArrayList<Subcategoria> subcategorias;
    LayoutInflater inflater;

    public SubcategoriasAdapter(Context context, ArrayList<Subcategoria> subcategorias){
        this.context = context;
        this.subcategorias = subcategorias;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return subcategorias.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.subcategoria, null);
        Button btnAdded = (Button) convertView.findViewById(R.id.btn_added);
        btnAdded.setText((CharSequence) subcategorias.get(position));
        return convertView;
    }
}
