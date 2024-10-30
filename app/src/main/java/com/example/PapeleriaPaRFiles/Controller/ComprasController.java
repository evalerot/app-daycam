package com.example.PapeleriaPaRFiles.Controller;

import android.util.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ComprasController {

    Conexion c = new Conexion();
    private String catidadProductos;
    private String gramos;
    private String origen;
    private String formapago;
    private int num_preliminar = 0;
    private int numcompra;
    private int precio;
    private int precio_unitario;
    private int precio_venta;
    private int producto;
    private int proveedor;
    private int credito;
    private int procentaje;
    private int dinerorecibido;
    private int cambio;
    private int precioventa2;
    private int precioventa3;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private int usuario;

    public String getFecha() {
        return sdf.format(new Date());
    }

    public int getNumcompra() {
        return numcompra;
    }

    public void setNumcompra(int numcompra) {
        this.numcompra = numcompra;
    }

    public int getPrecioventa2() {
        return precioventa2;
    }

    public void setPrecioventa2(int precioventa2) {
        this.precioventa2 = precioventa2;
    }

    public int getPrecioventa3() {
        return precioventa3;
    }

    public void setPrecioventa3(int precioventa3) {
        this.precioventa3 = precioventa3;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getProcentaje() {
        return procentaje;
    }

    public void setProcentaje(int procentaje) {
        this.procentaje = procentaje;
    }

    public int getDinerorecibido() {
        return dinerorecibido;
    }

    public void setDinerorecibido(int dinerorecibido) {
        this.dinerorecibido = dinerorecibido;
    }

    public int getCambio() {
        return cambio;
    }

    public void setCambio(int cambio) {
        this.cambio = cambio;
    }

    public int getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(int i) {
        this.proveedor = i;
    }

    public int getProducto() {
        return this.producto;
    }

    public void setProducto(int i) {
        this.producto = i;
    }

    public String getCatidadProductos() {
        return this.catidadProductos;
    }

    public void setCatidadProductos(String str) {
        this.catidadProductos = str;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int i) {
        this.precio = i;
    }

    public int getPrecio_unitario() {
        return this.precio_unitario;
    }

    public void setPrecio_unitario(int i) {
        this.precio_unitario = i;
    }

    public int getUsuario() {
        return this.usuario;
    }

    public void setUsuario(int i) {
        this.usuario = i;
    }

    public int getNum_preliminar() {
        return this.num_preliminar;
    }

    public void setNum_preliminar(int i) {
        this.num_preliminar = i;
    }

    public int getPrecio_venta() {
        return this.precio_venta;
    }

    public void setPrecio_venta(int i) {
        this.precio_venta = i;
    }

    public String getGramos() {
        return this.gramos;
    }

    public void setGramos(String str) {
        this.gramos = str;
    }

    public ArrayList<String[]> getDataProductos() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT lower(codigo), lower(nombre), case when (select precio_venta from compras where id = (select max(id) from compras where producto = id)) is null then \"costoVenta\"\nelse (select precio_venta from compras where id = (select max(id) from compras where producto = id)) end costoventa, case when linea=1 then '22' else '1' end cantidad, id \nFROM productos p where codigo != 'fra#000' order by 1");
            while (executeQuery.next()) {
                String[] strArr2 = new String[5];
                int i = 0;
                while (i < 5) {
                    int i2 = i + 1;
                    strArr2[i] = executeQuery.getObject(i2).toString();
                    i = i2;
                }
                arrayList.add(strArr2);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return arrayList;
        } catch (SQLException e) {
            Log.e("getDataProductos", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public void insertarCompra() {
        try {
            Connection connection = c.conectar().getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO compras (numfactura, fechaFactura, proveedor, producto, catidadProductos, estado, precio, precio_unitario, usuario, num_preliminar, precio_venta, gramosproductos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            prepareStatement.setInt(1, getNumcompra());
            prepareStatement.setString(2, getFecha());
            prepareStatement.setInt(3, getProveedor());
            prepareStatement.setInt(4, getProducto());
            prepareStatement.setString(5, getCatidadProductos());
            prepareStatement.setString(6, "activo");
            prepareStatement.setInt(7, getPrecio());
            prepareStatement.setInt(8, getPrecio_unitario());
            prepareStatement.setInt(9, getUsuario());
            prepareStatement.setInt(10, 0);
            prepareStatement.setInt(11, getPrecio_venta());
            prepareStatement.setString(12, getGramos());
            prepareStatement.executeUpdate();
            prepareStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.e("insertarCompra", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public int compraNum() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT max(numFactura) FROM compras");
            while (executeQuery.next()) {
                setNumcompra(executeQuery.getInt(1)+1);
            }
            executeQuery.close();
            conectar.close();
        } catch (SQLException e) {
            Log.e("Num Compra", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return getNumcompra();
    }

    public ArrayList<String[]> getProveedores() {
        int numcol = getCantidadProveedor();
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr;
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT id, lower(nombre) FROM proveedor WHERE desvincular = 'NO'");
            while (executeQuery.next()) {
                strArr = new String[numcol];
                int i = 0;
                while (i < numcol) {
                    strArr[i] = executeQuery.getObject(i).toString();
                    i++;
                }
                arrayList.add(strArr);
            }
            executeQuery.close();
            conectar.getConnection().close();
        } catch (SQLException e) {
            Log.e("getProveedores", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return arrayList;
    }

    public int getCantidadProveedor() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select count(id) from proveedor WHERE desvincular = 'NO'");
            int i = 0;
            while (executeQuery.next()) {
                i = executeQuery.getInt(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return i;
        } catch (SQLException e) {
            Log.e("getData", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return 0;
        }
    }
}
