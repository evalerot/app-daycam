package com.example.PapeleriaPaRFiles.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Presentacion.Cliente;
import com.example.PapeleriaPaRFiles.Presentacion.FacturarPapeleriaPaR;
import com.example.PapeleriaPaRFiles.Presentacion.Menu;
import com.example.PapeleriaPaRFiles.Presentacion.Reporte;
import com.example.PapeleriaPaRFiles.Presentacion.Sesion;
import com.example.PapeleriaPaRFiles.R;

public class SplashScreens extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash_screens);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreens.this, Sesion.class));
            //finish();
        },3000);
    }
}
