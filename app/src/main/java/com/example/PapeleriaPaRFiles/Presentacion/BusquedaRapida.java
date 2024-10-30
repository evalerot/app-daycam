package com.example.PapeleriaPaRFiles.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Controller.AdaptadorProductosFacturar;
import com.example.PapeleriaPaRFiles.Controller.FacturarController;
import com.example.PapeleriaPaRFiles.Controller.ProductosFacturar;
import com.example.PapeleriaPaRFiles.R;

import java.util.ArrayList;

public class BusquedaRapida extends AppCompatActivity {

    AdaptadorProductosFacturar adaptadorProductosFacturar;
    ArrayList<String[]> clients;
    FacturarController fc;
    ListView listView;
    String productoingresado = "";
    ArrayList<ProductosFacturar> products;
    SearchView searchView;
    String tipo;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_busquedacompuesto);
        fc = new FacturarController();
        searchView = findViewById(R.id.busqueda);
        listView = findViewById(R.id.lista);
        tipo = getIntent().getStringExtra("tipo");
        if (tipo.equals("0")) {
            clients = fc.getDataProductos();
        } else {
            clients = fc.getDataProductosFiltrado(tipo);
        }
        if (clients.isEmpty()) {
            Toast.makeText(this, "No hay compuestos", Toast.LENGTH_SHORT).show();
            finish();
        }
        products = new ArrayList<>();
        searchViewEventos();
        listViewEventos();
        listView.setOnItemClickListener((adapterView, view, i, j) -> lambda$onCreate$0$BusquedaRapida(i));
    }

    public void lambda$onCreate$0$BusquedaRapida(int i) {
        String codigo = products.get(i).getCodigo();
        Intent intent = new Intent();
        intent.putExtra("codigo", codigo);
        intent.putExtra("tipo", tipo);
        setResult(-1, intent);
        finish();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    private void searchViewEventos() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                productoingresado = str;
                actualizarProductos();
                return false;
            }

            public boolean onQueryTextChange(String str) {
                productoingresado = str;
                actualizarProductos();
                return false;
            }
        });
    }

    private void listViewEventos() {
        for (String[] next : clients) {
            products.add(new ProductosFacturar(next[0], next[1], "$", next[2], "", products.size() + 1 + ""));
        }
        actualizarProductos();
    }

    public void actualizarProductos() {
        products = new ArrayList<>();
        for (String[] next : clients) {
            if (next[1].contains(productoingresado)) {
                products.add(new ProductosFacturar(next[0], next[1], "$", next[2], "", products.size() + 1 + ""));
            }
        }
        adaptadorProductosFacturar = new AdaptadorProductosFacturar(this, products);
        listView.setAdapter(adaptadorProductosFacturar);
    }
}
