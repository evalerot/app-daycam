package com.example.PapeleriaPaRFiles.Presentacion;

import static com.example.PapeleriaPaRFiles.R.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.PapeleriaPaRFiles.Controller.AdaptadorProductosFacturar;
import com.example.PapeleriaPaRFiles.Controller.ComprasController;
import com.example.PapeleriaPaRFiles.Controller.Escanear;
import com.example.PapeleriaPaRFiles.Controller.ProductosFacturar;
import com.example.PapeleriaPaRFiles.R;

import java.util.ArrayList;
import java.util.Iterator;

public class Compras extends AppCompatActivity {

    AdaptadorProductosFacturar adaptadorProductosFacturar;
    ImageButton botonbuscarProducto;
    ImageButton botonbuscarProductoCodigo;
    ImageButton btnCliente;
    ImageButton btnCodigoBarras;
    EditText buscarProducto;
    ArrayList<String[]> proveedores;
    ArrayList<String[]> completedata;
    ComprasController cc;
    ArrayList<ProductosFacturar> listItems = new ArrayList<>();
    ListView listView;
    public boolean permisoCamaraConcedido = false;
    public boolean permisoSolicitadoDesdeBoton = false;
    Spinner textoProveedor;
    Spinner textotipoPago;
    Button totalpagar;
    TextView valortotal;
    int valtotalPagar = 0;
    static boolean fondoactual;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(layout.activity_compras);
        cc = new ComprasController();
        listView = findViewById(id.listaFacturacion);
        btnCodigoBarras = findViewById(id.btnCodigoBarras);
        buscarProducto = findViewById(id.buscarProducto);
        textoProveedor = findViewById(id.Proveedor);
        textotipoPago = findViewById(id.tipoPago);
        botonbuscarProducto = findViewById(id.btnBuscar);
        botonbuscarProductoCodigo = findViewById(id.btnBuscarCodigo);
        totalpagar = findViewById(id.totalPagar);
        valortotal = findViewById(id.valortotal);
        completedata = cc.getDataProductos();
        proveedores = cc.getProveedores();
        seleccionarClienteVendedor();
        verificarYPedirPermisosDeCamara();
        btnCodigoBarras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!permisoCamaraConcedido) {
                    Toast.makeText(getApplicationContext(), "Por favor permite que la app acceda a la cámara", Toast.LENGTH_SHORT).show();
                    verificarYPedirPermisosDeCamara();
                    return;
                }
                escanear();
            }
        });
        botonbuscarProducto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BusquedaRapida.class);
                intent.putExtra("tipo", "0");
                startActivityForResult(intent, 2);
            }
        });
        botonbuscarProductoCodigo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                insertarProducto();
            }
        });
        totalpagar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {realizarVenta();}
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                editarCantidadValor(i);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                adaptadorProductosFacturar.getList().remove(i);
                listView.setAdapter(adaptadorProductosFacturar);
                sumar();
                return false;
            }
        });
    }

    public void editarCantidadValor(int k) {
        View inflate = getLayoutInflater().inflate(layout.dialog_facturar, (ViewGroup) null);
        ProductosFacturar item = adaptadorProductosFacturar.getItem(k);
        String cantidad = item.getCantidad();
        String precio = item.getPrecio();
        final EditText editText = (EditText) inflate.findViewById(id.cantidad);
        final EditText editText2 = (EditText) inflate.findViewById(id.precio);
        editText.setText(cantidad);
        editText2.setText(precio);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Valores");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                adaptadorProductosFacturar.getItem(k).setCantidad(editText.getText().toString());
                adaptadorProductosFacturar.getItem(k).setPrecio(editText2.getText().toString());
                int parseInt = Integer.parseInt(editText.getText().toString()) * Integer.parseInt(editText2.getText().toString());
                ProductosFacturar item = adaptadorProductosFacturar.getItem(k);
                item.setSubtotal(parseInt + "");
                listView.setAdapter(adaptadorProductosFacturar);
                sumar();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setView(inflate);
        builder.show();
    }

    public ArrayList<ProductosFacturar> getListProductos() {
        String lowerCase = buscarProducto.getText().toString().toLowerCase();
        Iterator<ProductosFacturar> it = listItems.iterator();
        while (it.hasNext()) {
            ProductosFacturar next = it.next();
            if (next.getCodigo().equals(lowerCase)) {
                int parseInt = Integer.parseInt(next.getCantidad());
                next.setCantidad(parseInt + "");
                int parseInt2 = parseInt * Integer.parseInt(next.getPrecio());
                next.setSubtotal(parseInt2 + "");
                return listItems;
            }
        }
        Iterator<String[]> it2 = completedata.iterator();
        while (it2.hasNext()) {
            String[] next2 = it2.next();
            if (next2[0].equals(lowerCase)) {
                listItems.add(new ProductosFacturar(next2[0], next2[1], next2[3], next2[2], next2[4], listItems.size()+1+""));
            }
        }
        return listItems;
    }

    private void seleccionarClienteVendedor() {
        textoProveedor.setAdapter(new ArrayAdapter(getApplicationContext(), layout.dialog_opciones_desplegables, id.opciones, cc.getProveedores()));
        String[] datos = {"efectivo", "transferencia", "tarjeta"};
        textotipoPago.setAdapter(new ArrayAdapter(getApplicationContext(), layout.dialog_opciones_desplegables, id.opciones, datos));
    }

    /* access modifiers changed from: private */
    public void insertarProducto() {
        AdaptadorProductosFacturar adaptadorProductosFacturar2 = new AdaptadorProductosFacturar(this, getListProductos());
        fondoactual = !fondoactual;
        adaptadorProductosFacturar = adaptadorProductosFacturar2;
        listView.setAdapter(adaptadorProductosFacturar2);
        listView.setSelection(getListProductos().size()-1);
        sumar();
        buscarProducto.setText("");
    }

    /* access modifiers changed from: private */
    public void sumar() {
        valtotalPagar = 0;
        for (int i = 0; i < adaptadorProductosFacturar.getCount(); i++) {
            valtotalPagar += Integer.parseInt(listItems.get(i).getCantidad()) * Integer.parseInt(listItems.get(i).getPrecio());
        }
        valortotal.setText(valtotalPagar+"");
        actualizarNumeroCantidad();
    }

    /* access modifiers changed from: private */
    public void realizarVenta() {
        int i = 0;
        while (true) {
            if (i >= proveedores.size()) {
                break;
            } else if (proveedores.get(i)[1].equals(textoProveedor.getSelectedItem().toString())) {
                cc.setProveedor(Integer.parseInt(proveedores.get(i)[0]));
                break;
            } else {
                cc.setProveedor(1);
                i++;
            }
        }
        cc.compraNum();
        cc.setUsuario(1);
        cc.setCredito(0);
        cc.setOrigen("Celular");
        cc.setFormapago(textotipoPago.getSelectedItem().toString());
        cc.setCambio(0);
        cc.setPrecioventa2(0);
        cc.setPrecioventa3(0);
        for (int i2 = 0; i2 < adaptadorProductosFacturar.getCount(); i2++) {
            cc.setProducto(Integer.parseInt(listItems.get(i2).getId()));
            cc.setPrecio(( Integer.parseInt(listItems.get(i2).getCantidad())) * Integer.parseInt(listItems.get(i2).getPrecio()));
            cc.setPrecio_unitario(Integer.parseInt(listItems.get(i2).getPrecio()));
            cc.setCatidadProductos(listItems.get(i2).getCantidad());
            cc.insertarCompra();
        }
        Toast.makeText(getApplicationContext(), "COMPRA REALIZADA", Toast.LENGTH_SHORT).show();
        limpiarTodo();
        Intent intent = new Intent(Compras.this, Reporte.class);
        intent.putExtra("Tipo", "Compra");
        startActivity(intent);
    }

    private void limpiarTodo() {
        valtotalPagar = 0;
        valortotal.setText(String.valueOf(0));
        textoProveedor.setSelection(0);
        adaptadorProductosFacturar.getList().clear();
        listView.setAdapter(adaptadorProductosFacturar);
    }

    private void actualizarNumeroCantidad() {
        for (int i = 0; i<listItems.size(); i++){
            listItems.get(i).setCan(i+1+"");
        }
    }

    /* access modifiers changed from: private */
    public void escanear() {
        startActivityForResult(new Intent(this, Escanear.class), 2);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2 && i2 == -1 && intent != null) {
            buscarProducto.setText(intent.getStringExtra("codigo").toLowerCase());
            insertarProducto();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
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

    /* access modifiers changed from: private */
    public void verificarYPedirPermisosDeCamara() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            permisoCamaraConcedido = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, 1);
        }
    }
}
