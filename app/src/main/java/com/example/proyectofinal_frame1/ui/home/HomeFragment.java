package com.example.proyectofinal_frame1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal_frame1.AccesoriosActivity;
import com.example.proyectofinal_frame1.ComplementosActivity;
import com.example.proyectofinal_frame1.R;
import com.example.proyectofinal_frame1.RopaInferiorActivity;
import com.example.proyectofinal_frame1.RopaSuperiorActivity;
import com.example.proyectofinal_frame1.ZapatosActivity;
import com.example.proyectofinal_frame1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.imageButtonArriba.setOnClickListener(this);
        binding.imageButtonAbajo.setOnClickListener(this);
        binding.imageButtonZapatos.setOnClickListener(this);
        binding.imageButtonComplem.setOnClickListener(this);
        binding.imageButtonAccesorios.setOnClickListener(this);

        View root = binding.getRoot();
        /*
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
         */

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.imageButtonArriba:
                intent = new Intent(getActivity(), RopaSuperiorActivity.class);
                break;
            case R.id.imageButtonAbajo:
                // hacer cuando se haga clic en el imageButtonAbajo
                intent = new Intent(getActivity(), RopaInferiorActivity.class);
                break;
            case R.id.imageButtonZapatos:
                intent = new Intent(getActivity(), ZapatosActivity.class);
                break;
            case R.id.imageButtonComplem:
                intent = new Intent(getActivity(), ComplementosActivity.class);
                break;
            case R.id.imageButtonAccesorios:
                intent = new Intent(getActivity(), AccesoriosActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
