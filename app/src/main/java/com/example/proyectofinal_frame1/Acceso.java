package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.TablaPrenda;
import com.example.proyectofinal_frame1.database.TablaSalt;
import com.example.proyectofinal_frame1.database.TablaUsuario;

public class Acceso extends AppCompatActivity {

    private TextView txtLinkCuenta;
    private TablaUsuario tablaUsuario = new TablaUsuario(Acceso.this);
    private TablaSalt tablaSalt = new TablaSalt(Acceso.this);
    private EditText correoField, contrasenaField;
    private Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        txtLinkCuenta = (TextView) findViewById(R.id.txtLinkCuenta);
        correoField = (EditText) findViewById(R.id.correoField);
        contrasenaField = (EditText) findViewById(R.id.contrasenaField);
        btnAcceder = (Button) findViewById(R.id.btnAccesoCuenta);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
                /*
                String correo = correoField.getText().toString().trim();
                String contrasena = contrasenaField.getText().toString().trim();

                if(correo.isEmpty()||contrasena.isEmpty()){
                    Toast.makeText(Acceso.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                        correoField.setError("Por favor, ingrese una dirección de correo electrónico válida");
                        correoField.requestFocus();
                    }else if (!tablaUsuario.existeUsuario(correo)){
                        correoField.setError("No existe cuenta con este correo electrónico");
                        correoField.requestFocus();
                    }else {
                        long idUsuario = tablaUsuario.obtenerUsuario(correo);
                        String contrasenaSegura = tablaUsuario.obtenerContrasena(idUsuario);
                        String salt = tablaSalt.obtenerSalt(idUsuario);
                        if(ContrasenaUtils.verifyUserPassword(contrasena, contrasenaSegura, salt)==false){
                            contrasenaField.setError("Contraseña incorrecta");
                            contrasenaField.requestFocus();
                        }else{
                            ArmarConjuntosFragment.setIdUsuario(idUsuario);
                            toMain();
                        }
                    }
                }

                 */
            }
        });

        txtLinkCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acceso.this, Registro.class);
                txtLinkCuenta.setTextColor(Color.parseColor("#7E7E7E"));
                startActivity(intent);
            }
        });

    }

    public void toMain() {
        Intent intent = new Intent(Acceso.this, MainActivity.class);
        startActivity(intent);
    }
}