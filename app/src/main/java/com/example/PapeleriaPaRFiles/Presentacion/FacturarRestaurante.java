package com.example.PapeleriaPaRFiles.Presentacion;

import static com.example.PapeleriaPaRFiles.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.PapeleriaPaRFiles.Controller.AdaptadorProductosFacturar;
import com.example.PapeleriaPaRFiles.Controller.Escanear;
import com.example.PapeleriaPaRFiles.Controller.FacturarController;
import com.example.PapeleriaPaRFiles.Controller.ProductosFacturar;
import com.example.PapeleriaPaRFiles.R;

import java.util.ArrayList;

public class FacturarRestaurante extends AppCompatActivity {

    ImageButton botonbuscarProducto;
    ImageButton botonbuscarProductoCodigo;
    ImageButton btnCliente;
    ImageButton btnCodigoBarras;
    EditText buscarProducto;
    Spinner textoCliente;
    Spinner textoVendedor;
    Spinner textotipoPago;
    Button totalpagar;
    TextView valortotal;
    AdaptadorProductosFacturar adaptadorProductosFacturar;
    ArrayList<ProductosFacturar> listItems = new ArrayList<>();
    ArrayList<String[]> clientes;
    ArrayList<String[]> completedata;
    FacturarController fc;
    public boolean permisoCamaraConcedido = false;
    public boolean permisoSolicitadoDesdeBoton = false;
    int valtotalPagar = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(layout.activity_facturar_restaurante);
        fc = new FacturarController();
        btnCodigoBarras = findViewById(id.btnCodigoBarras);
        buscarProducto = findViewById(id.buscarProducto);
        textoCliente = findViewById(id.Cliente);
        textoVendedor = findViewById(id.Vendedor);
        textotipoPago = findViewById(id.tipoPago);
        botonbuscarProducto = findViewById(id.btnBuscar);
        botonbuscarProductoCodigo = findViewById(id.btnBuscarCodigo);
        totalpagar = findViewById(id.totalPagar);
        valortotal = findViewById(id.valortotal);
        completedata = fc.getDataProductos();
        clientes = fc.getClientes2();
        seleccionarClienteVendedor();
        verificarYPedirPermisosDeCamara();
        btnCodigoBarras.setOnClickListener(view -> {
            if (!permisoCamaraConcedido) {
                Toast.makeText(getApplicationContext(), "Por favor permite que la app acceda a la cámara", Toast.LENGTH_SHORT).show();
                verificarYPedirPermisosDeCamara();
                return;
            }
            escanear();
        });
        botonbuscarProducto.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BusquedaRapida.class);
            intent.putExtra("tipo", "0");
            startActivityForResult(intent, 2);
        });
        botonbuscarProductoCodigo.setOnClickListener(view -> insertarProducto());
        totalpagar.setOnClickListener(view -> realizarVenta());
    }

    public ArrayList<ProductosFacturar> getListProductos() {
        String lowerCase = buscarProducto.getText().toString().toLowerCase();
        for (ProductosFacturar next : listItems) {
            if (next.getCodigo().equals(lowerCase)) {
                int parseInt = Integer.parseInt(next.getCantidad());
                next.setCantidad(parseInt + "");
                int parseInt2 = parseInt * Integer.parseInt(next.getPrecio());
                next.setSubtotal(parseInt2 + "");
                return listItems;
            }
        }
        for (String[] next2 : completedata) {
            if (next2[0].equals(lowerCase)) {
                listItems.add(new ProductosFacturar(next2[0], next2[1], next2[3], next2[2], next2[4], listItems.size() + 1 + ""));
            }
        }
        return listItems;
    }

    private void seleccionarClienteVendedor() {
        String[] datos = {"efectivo", "transferencia", "tarjeta"};
        textoCliente.setAdapter(new ArrayAdapter(getApplicationContext(), layout.dialog_opciones_desplegables, id.opciones, fc.getClientes()));
        textoVendedor.setAdapter(new ArrayAdapter(getApplicationContext(), layout.dialog_opciones_desplegables, id.opciones, fc.getVendedores()));
        textotipoPago.setAdapter(new ArrayAdapter(getApplicationContext(), layout.dialog_opciones_desplegables, id.opciones, datos));
    }

    public void insertarProducto() {
        adaptadorProductosFacturar = new AdaptadorProductosFacturar(this, getListProductos());
        sumar();
        buscarProducto.setText("");
    }

    public void sumar() {
        valtotalPagar = 0;
        for (int i = 0; i < adaptadorProductosFacturar.getCount(); i++) {
            valtotalPagar += Integer.parseInt(listItems.get(i).getCantidad()) * Integer.parseInt(listItems.get(i).getPrecio());
        }
        valortotal.setText(valtotalPagar+"");
        actualizarNumeroCantidad();
    }

    public void realizarVenta() {
        int i = 0;
        while (true) {
            if (i >= clientes.size()) {
                break;
            } else if (clientes.get(i)[1].equals(textoCliente.getSelectedItem().toString())) {
                fc.setCliente(Integer.parseInt(clientes.get(i)[0]));
                break;
            } else {
                fc.setCliente(1);
                i++;
            }
        }
        fc.ventaNum();
        fc.setUsuario(1);
        fc.setCredito(0);
        fc.setPorcentaje(0);
        fc.setOrigen("Celular");
        fc.setFormapago(textotipoPago.getSelectedItem().toString());
        fc.setDineroRecibido(Integer.parseInt(valortotal.getText().toString()));
        fc.setCambio(0);
        fc.setVendedor(textoVendedor.getSelectedItem().toString());
        fc.setCostoVenta2(0);
        fc.setCostoVenta3(0);
        if (fc.getVendedor().equals("Vendedor")) {
            fc.setVendedor("Ocacional");
        }
        for (int i2 = 0; i2 < adaptadorProductosFacturar.getCount(); i2++) {
            fc.setProducto(Integer.parseInt(listItems.get(i2).getId()));
            fc.setPrecio(((double) Integer.parseInt(listItems.get(i2).getCantidad())) * Double.parseDouble(listItems.get(i2).getPrecio()));
            fc.setPrecio_unitario(Double.parseDouble(listItems.get(i2).getPrecio()));
            fc.setCatidadProductos(listItems.get(i2).getCantidad());
            fc.insertarVenta();
            Toast.makeText(getApplicationContext(), "VENTA REALIZADA", Toast.LENGTH_SHORT).show();
        }
        limpiarTodo();
        startActivityForResult(new Intent(this, Reporte.class), 2);
    }

    private void limpiarTodo() {
        valtotalPagar = 0;
        valortotal.setText(String.valueOf(0));
        textoCliente.setSelection(0);
        adaptadorProductosFacturar.getList().clear();
    }

    private void actualizarNumeroCantidad() {
        for (int i = 0; i<listItems.size(); i++){
            listItems.get(i).setCan(i+1+"");
        }
    }

    public void escanear() {
        startActivityForResult(new Intent(this, Escanear.class), 2);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2 && i2 == -1 && intent != null) {
            buscarProducto.setText(intent.getStringExtra("codigo").toLowerCase());
            insertarProducto();
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 1) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(this, "No puedes escanear si no das permiso", Toast.LENGTH_SHORT).show();
                return;
            }
            if (permisoSolicitadoDesdeBoton) {
                escanear();
            }
            permisoCamaraConcedido = true;
        }
    }

    public void verificarYPedirPermisosDeCamara() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            permisoCamaraConcedido = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, 1);
        }
    }
}
