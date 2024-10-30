package com.example.PapeleriaPaRFiles.Presentacion;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Controller.Conexion;
import com.example.PapeleriaPaRFiles.Controller.SesionController;
import com.example.PapeleriaPaRFiles.R;

public class Sesion extends AppCompatActivity {

    public EditText clave;
    ImageButton imgajustes;
    ImageButton imgajustesPriv;
    SesionController sc;
    public EditText usuario;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sesion);
        Conexion.setBD(Sesion.this);
        sc = new SesionController();
        usuario = findViewById(R.id.usuario);
        clave = findViewById(R.id.clave);
        Button btnIngreso = findViewById(R.id.ingresar);
        imgajustes = findViewById(R.id.imgAjustes);
        imgajustesPriv = findViewById(R.id.imgAjustesPriv);
        imgajustes.setOnClickListener(view -> ajustesPublicos());
        imgajustesPriv.setOnClickListener(view -> ajustesPrivados());
        btnIngreso.setOnClickListener(view -> {
            int ConexionInicio = sc.ConexionInicio(usuario.getText().toString(), clave.getText().toString());
            if (ConexionInicio != 0) {
                String imei = sc.getIMEI(getApplicationContext());
                if (sc.verifyIMEI(imei)) {
                    Toast.makeText(getApplicationContext(), "USUARIO CORRECTO", Toast.LENGTH_SHORT).show();
                    sc.actualizarUsuario(imei, ConexionInicio);
                    startActivity(new Intent(Sesion.this, Menu.class));
                    return;
                }
                Toast.makeText(getApplicationContext(), "CELULAR NO REGISTRADO", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "EL USUARIO O CONTRASEÑA SON INCORRECTOS", Toast.LENGTH_SHORT).show();
        });
    }

    public void ajustesPrivados() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_ajustes, null);
        final EditText editText = inflate.findViewById(R.id.pass);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Soporte Privado");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", (dialogInterface, i) -> {
            if (editText.getText().toString().equals("Roman@day")) {
                startActivityForResult(new Intent(Sesion.this, AjustesPrivados.class), 2);
                return;
            }
            Toast.makeText(Sesion.this.getApplicationContext(), "CONTRASEÑA INCORRECTA", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancelar", (dialogInterface, i) -> {
        });
        builder.setView(inflate);
        builder.show();
    }

    public void ajustesPublicos() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_ajustes, null);
        final EditText editText = inflate.findViewById(R.id.pass);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajustes Publicos");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", (dialogInterface, i) -> {
            if (editText.getText().toString().equals("12345")) {
                startActivityForResult(new Intent(Sesion.this, AjustesPublicos.class), 2);
                return;
            }
            Toast.makeText(getApplicationContext(), "CONTRASEÑA INCORRECTA", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancelar", (dialogInterface, i) -> {
        });
        builder.setView(inflate);
        builder.show();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2 && i2 == -1 && intent != null) {
            intent.getStringExtra("base");
        }
    }
}
