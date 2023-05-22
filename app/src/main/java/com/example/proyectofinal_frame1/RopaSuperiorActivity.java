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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RopaSuperiorActivity<newCheckbox, mCheckboxes> extends AppCompatActivity {

    private FloatingActionButton floatBtn;

    private EditText writeName;
    private Button btnAdded;
    private ImageView btnBack;
    private ImageButton btnDelete;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_superior);

        context = getApplicationContext();

        writeName = findViewById(R.id.write_name);
        btnAdded = findViewById(R.id.btn_added);
        btnDelete = findViewById(R.id.btn_delete);
        btnBack = findViewById(R.id.btn_back);

        floatBtn = (FloatingActionButton) findViewById(R.id.float_btn);

        floatBtn.setOnClickListener(new View.OnClickListener() {
            // @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                writeName.setVisibility(View.VISIBLE);
                writeName.requestFocus();
                showKeyboard(); // abrir teclado

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
                            btnAdded.setText(writeName.getText().toString().toUpperCase());// cambio texto de botón
                            btnAdded.setVisibility(View.VISIBLE);
                            writeName.setVisibility(View.GONE);
                            writeName.setText(null);
                            closeKeyboard();
                            // añadir nueva subcategoría a la bd
                            return true;
                        }
                        return false;
                    }
                });

            }
        });


        // al mantener un botón de subcategoría pulsado
        btnAdded.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                btnAdded.setVisibility(View.INVISIBLE);
                writeName.setVisibility(View.VISIBLE);
                writeName.requestFocus();
                showKeyboard(); // abrir teclado

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


                // icono delete
                btnDelete.setVisibility(View.VISIBLE);


                return true;
            }
        });

        // funcionalidad al clicar en btnDelete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeName.setVisibility(View.GONE);
                btnAdded.setVisibility(View.GONE);
                btnDelete.setVisibility(View.INVISIBLE);
            }
        });

        // funcionalidad al clicar en btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });


    }

    // mostrar teclado
    public void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    // cerrar teclado
    public void closeKeyboard(){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    // para volver a la pantalla anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}