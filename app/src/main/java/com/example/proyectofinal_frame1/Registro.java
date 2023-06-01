package com.example.proyectofinal_frame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_frame1.database.TablaSalt;
import com.example.proyectofinal_frame1.database.TablaUsuario;

public class Registro extends AppCompatActivity {

    private TextView txtLinkCuenta;
    private EditText nombreField, correoField, contrasenaField, repiteContrasenaField;
    private Button btnCrearCuenta;
    TablaUsuario tablaUsuario = new TablaUsuario(this);
    TablaSalt tablaSalt;
    private long idUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtLinkCuenta = (TextView) findViewById(R.id.txtLinkCuenta);
        nombreField = findViewById(R.id.nombreField);
        correoField = findViewById(R.id.correoField);
        contrasenaField = findViewById(R.id.contrasenaField);
        repiteContrasenaField = findViewById(R.id.repiteContrasenaField);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        txtLinkCuenta.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 Intent intent = new Intent(Registro.this, Acceso.class);
                 txtLinkCuenta.setTextColor(Color.parseColor("#7E7E7E"));
                 startActivity(intent);
             }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreField.getText().toString().trim();
                String correo = correoField.getText().toString().trim();
                String contrasena = contrasenaField.getText().toString().trim();
                String repiteContrasena = repiteContrasenaField.getText().toString().trim();

                // Verificar si todos los campos están completos
                if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || repiteContrasena.isEmpty()) {
                    Toast.makeText(Registro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Validar la dirección de correo electrónico
                    if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                        correoField.setError("Por favor, ingrese una dirección de correo electrónico válida");
                        correoField.requestFocus();
                    }else if (!contrasena.equals(repiteContrasena)) { // Validar que las contraseñas coincidan
                        repiteContrasenaField.setError("Las contraseñas no coinciden");
                        repiteContrasenaField.requestFocus();
                    }else {// Si todos los campos son válidos, crear la cuenta del usuario
                        // crear la cuenta del usuario
                        crearCuenta(nombre, correo, contrasena);
                    }
                }
            }
        });
    }

    public void toMain() {
        Intent intent = new Intent(Registro.this, MainActivity.class);
        startActivity(intent);
    }

    private void crearCuenta(String nombre, String correo, String contrasena) {
        if (tablaUsuario.existeUsuario(correo)) {
            correoField.setError("Este correo electrónico ya está registrado");
            correoField.requestFocus();
        }else{
            //Salt. El valor generado puede ser almacenado en DB.
            String salt = ContrasenaUtils.getSalt(30);
            Log.d("---->>>SALT: " , salt);

            String contrasenaSegura = ContrasenaUtils.generateSecurePassword(contrasena, salt);
            TablaSalt tablaSalt = new TablaSalt(this);

            idUser = tablaUsuario.insertarUsuario(nombre, correo, contrasenaSegura);
            long idSalt= tablaSalt.insertarSalt(idUser, salt);
            if (idUser != 0 && idUser > 0) {
                Toast.makeText(Registro.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                ArmarConjuntosFragment.setIdUsuario(idUser);
                // redirigir al usuario a la página de inicio de sesión
                toMain();
            } else {
                Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }
}