package com.example.PapeleriaPaRFiles.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Controller.Conexion;
import com.example.PapeleriaPaRFiles.R;

public class Menu extends AppCompatActivity {

    //private Spinner spinner;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_menu);
        actividadFacturar();
        actividadInventarios();
        actividadClientes();
        actividadCompras();
    }

    private void actividadFacturar() {
        TableRow rowFacturar = findViewById(R.id.rowFacturar);
        rowFacturar.setOnClickListener(r7 -> {
            switch (Conexion.getBD()){
                case "dayPapeleria2PaR":
                    startActivity(new Intent(Menu.this, FacturarPapeleriaPaR.class));
                    break;
                case "dayRestaurante":
                    startActivity(new Intent(Menu.this, FacturarRestaurante.class));
                    break;
                default:
                    break;
            }
        });
    }

    private void actividadClientes() {
        TableRow rowClientes = findViewById(R.id.rowCliente);
        rowClientes.setOnClickListener(view -> startActivity(new Intent(Menu.this, Cliente.class)));
    }

    private void actividadCompras() {
        TableRow rowCompras = findViewById(R.id.rowCompras);
        rowCompras.setOnClickListener(view -> startActivity(new Intent(Menu.this, Compras.class)));
        rowCompras.setVisibility(View.VISIBLE);
    }

    private void actividadInventarios() {
        //spinner = findViewById(R.id.seleccion);
        //spinner.setAdapter(new ArrayAdapter(this, R.layout.dialog_opciones_desplegables, R.id.opciones, new String[]{"Unidades", "Compuestos"}));
        TableRow rowInventarios = findViewById(R.id.rowInventarios);
        rowInventarios.setOnClickListener(view -> {
            Intent intent = new Intent(Menu.this, Inventarios.class);
            //intent.putExtra("TipoInventario", spinner.getSelectedItem().toString());
            startActivity(intent);
        });
        rowInventarios.setVisibility(View.VISIBLE);
    }
}
