package com.example.PapeleriaPaRFiles.Presentacion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.PapeleriaPaRFiles.R;
import com.example.PapeleriaPaRFiles.Controller.SesionController;

public class AjustesPublicos extends AppCompatActivity {

    int cancels;
    Button registarCel1;
    Button registarCel2;
    Button registarCel3;
    Button registarCel4;
    Button registarCel5;
    SesionController sc;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ajustes_publicos);
        registarCel1 = findViewById(R.id.registrar1);
        registarCel2 = findViewById(R.id.registrar2);
        registarCel3 = findViewById(R.id.registrar3);
        registarCel4 = findViewById(R.id.registrar4);
        registarCel5 = findViewById(R.id.registrar5);
        sc = new SesionController();
        cancels = sc.cantidadCelulares();
        if (cancels < 5) {
            registarCel5.setVisibility(View.INVISIBLE);
            if (cancels < 4) {
                registarCel4.setVisibility(View.INVISIBLE);
                if (cancels < 3) {
                    registarCel3.setVisibility(View.INVISIBLE);
                    if (cancels < 2) {
                        registarCel2.setVisibility(View.INVISIBLE);
                        if (cancels < 2) {
                            registarCel1.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }
        }
        registarCel1.setOnClickListener(view -> verificarImei(1));
        registarCel2.setOnClickListener(view -> verificarImei(2));
        registarCel3.setOnClickListener(view -> verificarImei(3));
        registarCel4.setOnClickListener(view -> verificarImei(4));
        registarCel5.setOnClickListener(view -> verificarImei(5));
    }

    private void verificarImei(int i) {
        String imei = sc.getIMEI(getApplicationContext());
        if (!sc.verifyIMEI(imei)) {
            sc.setIMEI(imei, i);
            Toast.makeText(getApplicationContext(), "CELULAR REGISTRADO", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "YA REGISTRADO ", Toast.LENGTH_LONG).show();
        finish();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
