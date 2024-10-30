package com.example.PapeleriaPaRFiles.Presentacion;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Controller.AdaptadorProductosInventarios;
import com.example.PapeleriaPaRFiles.Controller.InventariosController;
import com.example.PapeleriaPaRFiles.Controller.ProductosFacturar;
import com.example.PapeleriaPaRFiles.Controller.ProductosInventarios;
import com.example.PapeleriaPaRFiles.R;

import java.util.ArrayList;

public class Inventarios extends AppCompatActivity {

    InventariosController ic = new InventariosController();
    ArrayList<String[]> completedata;
    ArrayList<ProductosInventarios> filterdata;
    SearchView busqueda;
    ListView lista;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView( R.layout.activity_inventarios);
        //Bundle extras = getIntent().getExtras();
        //tipoBusqueda = extras.getString("TipoInventario", "");
        completedata = ic.reporte1();
        busqueda = findViewById(R.id.busqueda);
        lista = findViewById(R.id.lista);
        busqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                actualizarProductos(str);
                return false;
            }

            public boolean onQueryTextChange(String str) {
                actualizarProductos(str);
                return false;
            }
        });
        actualizarProductos("");
    }

    public void actualizarProductos(String str) {
        filterdata = new ArrayList<>();
        for (String[] strings : completedata) {
            if (strings[0].contains(str) || strings[1].contains(str)) {
                Log.e("test", filterdata.size()+1+"");
                filterdata.add(new ProductosInventarios(strings[0], strings[1], strings[2], strings[3], strings[4], filterdata.size()+1+""));
            }
        }
        AdaptadorProductosInventarios adaptadorProductosFacturar2 = new AdaptadorProductosInventarios(getApplicationContext(), filterdata);
        lista.setAdapter(adaptadorProductosFacturar2);
    }
}
