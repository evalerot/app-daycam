package com.example.PapeleriaPaRFiles.Presentacion;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Controller.ReporteController;
import com.example.PapeleriaPaRFiles.R;
import com.example.PapeleriaPaRFiles.Controller.AdaptadorProductosFacturar;
import com.example.PapeleriaPaRFiles.Controller.FacturarController;
import com.example.PapeleriaPaRFiles.Controller.ProductosFacturar;
import java.util.ArrayList;
import java.util.Iterator;

public class Reporte extends AppCompatActivity {

    Button btnCerrar;
    TextView cliente;
    ReporteController rc;
    TextView fecha;
    ListView listView;
    TextView numfactura;
    TextView totalFactura;
    TextView usuario;
    TextView vendedor;
    TextView numfact;
    TextView clipro;
    TextView totfact;
    LinearLayout vendedorcam;
    ArrayList<String[]> productos;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_reporte_factura);
        rc = new ReporteController();
        btnCerrar = (Button) findViewById(R.id.btnCerrar);
        numfactura = (TextView) findViewById(R.id.numFactura);
        cliente = (TextView) findViewById(R.id.cliente);
        vendedor = (TextView) findViewById(R.id.vendedor);
        fecha = (TextView) findViewById(R.id.fecha);
        usuario = (TextView) findViewById(R.id.usuario);
        totalFactura = (TextView) findViewById(R.id.totalFactura);
        numfact = (TextView) findViewById(R.id.numfact);
        clipro = (TextView) findViewById(R.id.clipro);
        totfact = (TextView) findViewById(R.id.totfact);
        vendedorcam = findViewById(R.id.vendedorcam);
        listView = (ListView) findViewById(R.id.listaFacturacion);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            llenarFactura(extras.getString("Tipo", "Factura"));
        }
        btnCerrar.setOnClickListener(view -> finish());
    }

    private void llenarFactura(String tipo) {
        String[] ultima;
        if(tipo.equals("Factura")){
            ultima = rc.ultimaFactura(rc.ventaNum());
            productos = rc.getProductosFactura(rc.ventaNum());
            numfact.setText("Numero Factura");
            clipro.setText("Cliente");
            totfact.setText("Total Factura");
            vendedorcam.setVisibility(View.VISIBLE);
        }else{
            ultima = rc.ultimaCompra(rc.compraNum());
            productos = rc.getProductosCompra(rc.compraNum());
            numfact.setText("Numero Compra");
            clipro.setText("Proveedor");
            totfact.setText("Total Compra");
            vendedorcam.setVisibility(View.GONE);
        }
        numfactura.setText(ultima[0]);
        cliente.setText(ultima[1]);
        vendedor.setText(ultima[2]);
        fecha.setText(ultima[3]);
        usuario.setText(ultima[4]);
        totalFactura.setText(ultima[5]);
        listView.setAdapter(new AdaptadorProductosFacturar(this, getListProductos()));
    }

    public ArrayList<ProductosFacturar> getListProductos() {
        ArrayList<ProductosFacturar> arrayList = new ArrayList<>();
        int i=1;
        for(String[] strings : productos){
            arrayList.add(new ProductosFacturar(strings[0], strings[1], strings[2], strings[3], "", i+""));
            i++;
        }
        return arrayList;
    }
}
