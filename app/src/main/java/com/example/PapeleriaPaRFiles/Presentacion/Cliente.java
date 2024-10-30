package com.example.PapeleriaPaRFiles.Presentacion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Controller.ClienteController;
import com.example.PapeleriaPaRFiles.R;

import java.util.ArrayList;
import java.util.Iterator;

public class Cliente extends AppCompatActivity {

    ImageButton btnAnadir;
    ImageButton btnBorrar;
    ImageButton btnModificar;
    ClienteController cc;
    EditText celularCliente;
    ArrayList<String[]> clientes;
    EditText correoCliente;
    EditText direccionCliente;
    Spinner estadoCliente;
    TextView idCliente;
    EditText nitCliente;
    EditText nombreCliente;
    EditText telefonoCliente;
    Spinner textoCliente;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cliente);
        cc = new ClienteController();
        idCliente = findViewById(R.id.idCliente);
        textoCliente = findViewById(R.id.seleccionCliente);
        estadoCliente = findViewById(R.id.estadoCliente);
        nombreCliente = findViewById(R.id.nombreCliente);
        nitCliente = findViewById(R.id.nitCliente);
        direccionCliente = findViewById(R.id.direccionCliente);
        celularCliente = findViewById(R.id.celularCliente);
        correoCliente = findViewById(R.id.correoCliente);
        telefonoCliente = findViewById(R.id.telefonoCliente);
        btnAnadir = findViewById(R.id.btnAñadir);
        btnModificar = findViewById(R.id.btnModificar);
        btnBorrar = findViewById(R.id.btnEliminar);
        clientes = cc.getClientes();
        seleccionarCliente();
        textoCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                asignarValores();
            }
        });
        btnAnadir.setOnClickListener(view -> {
            cc.setNombreCliente(nombreCliente.getText().toString());
            cc.setNitCliente(nitCliente.getText().toString());
            cc.setDireccionCliente(direccionCliente.getText().toString());
            cc.setTelefonoCliente(telefonoCliente.getText().toString());
            cc.setCelularCliente(celularCliente.getText().toString());
            cc.setCorreoCliente(correoCliente.getText().toString());
            cc.addCliente();
            Toast.makeText(getApplicationContext(), "CLIENTE INSERTADO", Toast.LENGTH_SHORT).show();
            clientes = cc.getClientes();
            seleccionarCliente();
        });
        btnModificar.setOnClickListener(view -> {
            for (String[] cliente : clientes) {
                if (cliente[1].equals(textoCliente.getSelectedItem().toString())) {
                    cc.setIdCliente(idCliente.getText().toString());
                    cc.setNombreCliente(nombreCliente.getText().toString());
                    cc.setNitCliente(nitCliente.getText().toString());
                    cc.setDireccionCliente(direccionCliente.getText().toString());
                    cc.setTelefonoCliente(telefonoCliente.getText().toString());
                    cc.setCelularCliente(celularCliente.getText().toString());
                    cc.setCorreoCliente(correoCliente.getText().toString());
                    cc.modifyCliente();
                    Toast.makeText(getApplicationContext(), "CLIENTE MODIFICADO", Toast.LENGTH_SHORT).show();
                }
            }
            clientes = cc.getClientes();
            seleccionarCliente();
        });
        btnBorrar.setOnClickListener(view -> {
            cc.setIdCliente(idCliente.getText().toString());
            cc.deleteCliente();
            Toast.makeText(getApplicationContext(), "CLIENTE ELIMINADO", Toast.LENGTH_SHORT).show();
            clientes = cc.getClientes();
            seleccionarCliente();
        });
    }

    public void seleccionarCliente() {
        String[] strArr = new String[(cc.getCantidadClientes() + 1)];
        strArr[0] = "Añadir cliente";
        Iterator<String[]> it = clientes.iterator();
        int i = 1;
        while (it.hasNext()) {
            strArr[i] = it.next()[1];
            i++;
        }
        textoCliente.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.dialog_opciones_desplegables, R.id.opciones, strArr));
        estadoCliente.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.dialog_opciones_desplegables, R.id.opciones, new String[]{"activo", "inactivo"}));
    }

    public void asignarValores() {
        if ("Añadir cliente".equals(this.textoCliente.getSelectedItem().toString())) {
            idCliente.setText("---");
            nombreCliente.setText("");
            nitCliente.setText("");
            direccionCliente.setText("");
            celularCliente.setText("");
            correoCliente.setText("");
            telefonoCliente.setText("");
            estadoCliente.setSelection(0);
            btnAnadir.setVisibility(View.VISIBLE);
            btnModificar.setVisibility(View.INVISIBLE);
            btnBorrar.setVisibility(View.INVISIBLE);
            return;
        }
        for (String[] next : clientes) {
            if (next[1].equals(textoCliente.getSelectedItem().toString())) {
                idCliente.setText(next[0]);
                nombreCliente.setText(next[1]);
                nitCliente.setText(next[2]);
                direccionCliente.setText(next[3]);
                telefonoCliente.setText(next[4]);
                celularCliente.setText(next[5]);
                correoCliente.setText(next[6]);
                if (next[7].equals("activo")) {
                    estadoCliente.setSelection(0);
                } else {
                    estadoCliente.setSelection(1);
                }
                btnAnadir.setVisibility(View.INVISIBLE);
                btnModificar.setVisibility(View.VISIBLE);
                btnBorrar.setVisibility(View.VISIBLE);
            }
        }
    }
}
