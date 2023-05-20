package com.example.proyectofinal_frame1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RopaSuperiorActivity extends AppCompatActivity {

    private FloatingActionButton floatBtn;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_superior);

        context = getApplicationContext();

        floatBtn = (FloatingActionButton) findViewById(R.id.float_btn);

        floatBtn.setOnClickListener(new View.OnClickListener() {
            // @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                EditText writeName = findViewById(R.id.write_name);
                writeName.setVisibility(View.VISIBLE);
                writeName.requestFocus();
                showKeyboard(); // abrir teclado

                writeName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            // al pinchar fuera del teclado o de writeName
                            closeKeyboard();
                            Button btnAdded = findViewById(R.id.btn_added);
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
                            Button btnAdded = findViewById(R.id.btn_added);
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

            }
        });

    }


    public void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }


}