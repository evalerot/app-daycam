package com.example.PapeleriaPaRFiles.Presentacion;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Controller.Conexion;
import com.example.PapeleriaPaRFiles.R;

public class AjustesPrivados extends AppCompatActivity {
    Button btnFragancia;
    Button btnLibreria;
    Button btnPapeleria;
    Button btnRestaurante;
    Spinner ip;
    SharedPreferences.Editor myEditor;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ajustes_privados);
        myEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        btnPapeleria = findViewById(R.id.papeleria);
        btnLibreria = findViewById(R.id.libreria);
        btnFragancia = findViewById(R.id.fragancia);
        btnRestaurante = findViewById(R.id.restaurante);
        ip = findViewById(R.id.ip);
        ip.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.dialog_opciones_desplegables, R.id.opciones, new String[]{"192.168.10.150", "186.29.194.235", "192.168.0.150", "192.168.0.151", "192.168.1.150", "192.168.20.150", "192.168.1.220", "192.168.1.230", "192.168.1.240", "192.168.1.180"}));
        String val = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("IP", "192.168.10.150");
        for (int i=0; i<ip.getCount(); i++){
            if(ip.getItemAtPosition(i).equals(val)){
                ip.setSelection(i);
            }
        }
        btnPapeleria.setOnClickListener(view -> {
            myEditor.putString("BD", "dayPapeleria2PaR");
            myEditor.putString("IP", ip.getSelectedItem().toString());
            myEditor.apply();
            Conexion.setBD(getApplicationContext());
            finish();
        });
        btnLibreria.setOnClickListener(view -> {
            myEditor.putString("BD", "dayLibreria");
            myEditor.putString("IP", ip.getSelectedItem().toString());
            myEditor.commit();
            Conexion.setBD(getApplicationContext());
            finish();
        });
        btnFragancia.setOnClickListener(view -> {
            myEditor.putString("BD", "dayfragancia");
            myEditor.putString("IP", ip.getSelectedItem().toString());
            myEditor.commit();
            Conexion.setBD(getApplicationContext());
            finish();
        });
        btnRestaurante.setOnClickListener(view -> {
            myEditor.putString("BD", "dayRestaurante");
            myEditor.putString("IP", ip.getSelectedItem().toString());
            myEditor.commit();
            Conexion.setBD(getApplicationContext());
            finish();
        });
    }
}
