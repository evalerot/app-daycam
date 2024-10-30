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

public class FacturarController {

    Conexion c = new Conexion();
    private boolean apartado;
    private boolean pagado;
    private int cliente;
    private int numfactura;
    private int producto;
    private int credito;
    private int porcentaje;
    private int dineroRecibido;
    private int cambio;
    private int costoventa2;
    private int costoventa3;
    private int usuario;
    private String catidadProductos;
    private String codigo;
    private String formapago;
    private String gramos;
    private String mesa;
    private String origen;
    private String tarjeta;
    private String vendedor;
    private double precio;
    private double precio_unitario;

    /*
     * se colocan las siguientes variables que hacen falta para realizar una facturación Edwin Valero.
     * */
    private boolean imprimir;
    private int subtotal;

    private int ivavalor;

    private String fechaimpresion;

    private int bodega;

    private String notacredito;

    private String fechacredito;

    private String formapago1;

    private int dinerorecibido1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public FacturarController() {
    }

    public int getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(int numfactura) {
        this.numfactura = numfactura;
    }

    public int getCostoventa2() {
        return costoventa2;
    }

    public void setCostoventa2(int costoventa2) {
        this.costoventa2 = costoventa2;
    }

    public int getCostoventa3() {
        return costoventa3;
    }

    public void setCostoventa3(int costoventa3) {
        this.costoventa3 = costoventa3;
    }

    public String getFecha() {
        return sdf.format(new Date());
    }

    public int getCliente() {
        return this.cliente;
    }

    public void setCliente(int i) {
        this.cliente = i;
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

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double d) {
        this.precio = d;
    }

    public int getCredito() {
        return this.credito;
    }

    public void setCredito(int i) {
        this.credito = i;
    }

    public int getDineroRecibido() {
        return this.dineroRecibido;
    }

    public void setDineroRecibido(int i) {
        this.dineroRecibido = i;
    }

    public int getCambio() { return this.cambio; }

    public void setCambio(int i) {
        this.cambio = i;
    }

    public int getCostoVenta2() { return this.costoventa2; }

    public void setCostoVenta2(int i) {
        this.costoventa2 = i;
    }

    public int getCostoVenta3() { return this.costoventa3; }

    public void setCostoVenta3(int i) {
        this.costoventa3 = i;
    }

    public double getPrecio_unitario() {
        return this.precio_unitario;
    }

    public void setPrecio_unitario(double d) {
        this.precio_unitario = d;
    }

    public int getPorcentaje() {
        return this.porcentaje;
    }

    public void setPorcentaje(int i) {
        this.porcentaje = i;
    }

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String str) {
        this.origen = str;
    }

    public String getFormapago() {
        return this.formapago;
    }

    public void setFormapago(String str) {
        this.formapago = str;
    }

    public int getUsuario() {
        return this.usuario;
    }

    public void setUsuario(int i) {
        this.usuario = i;
    }

    public String getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(String str) {
        this.vendedor = str;
    }

    public String getGramos() {
        return this.gramos;
    }

    public void setGramos(String str) {
        this.gramos = str;
    }

    public String getMesa() {
        return this.mesa;
    }

    public void setMesa(String str) {
        this.mesa = str;
    }

    public boolean isApartado() {
        return this.apartado;
    }

    public void setApartado(boolean z) {
        this.apartado = z;
    }

    public boolean isPagado() {
        return this.pagado;
    }

    public void setPagado(boolean z) {
        this.pagado = z;
    }

    public String getTarjeta() {
        return this.tarjeta;
    }

    public void setTarjeta(String str) {
        this.tarjeta = str;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String str) {
        this.codigo = str;
    }

    /*
    * se colocan los siguientes getters and setters de variables que hacen falta para realizar una facturación.
    * */
    public boolean isImprimir(){ return this.imprimir; }
    public void setImprimir(boolean z){ this.imprimir = z; }

    public int getSubtotal(){ return this.subtotal; }

    public void setSubtotal(int i){ this.subtotal = i; }

    public void setImprimir(int i){ this.subtotal = i; }

    public int getIvavalor(){ return this.ivavalor; }

    public void setIvavalor(int i){ this.ivavalor = i; }

    public String getFechaimpresion() {
        return this.fechaimpresion;
    }

    public void setFechaimpresion(String str) {
        this.fechaimpresion = str;
    }

    public int getBodega(){ return this.bodega; }

    public void setBodega(int i){ this.bodega = i; }

    public String getNotacredito() {
        return this.notacredito;
    }

    public void setNotacredito(String str) {
        this.notacredito = str;
    }

    public String getFechacredito() {
        return this.fechacredito;
    }

    public void setFechacredito(String str) {
        this.fechacredito = str;
    }

    public String getFormapago1() {
        return this.formapago1;
    }

    public void setFormapago1(String str) {
        this.formapago1 = str;
    }

    public int getDinerorecibido1(){ return this.dinerorecibido1; }

    public void setDinerorecibido1(int i){ this.dinerorecibido1 = i; }
    

    public ArrayList<String[]> getDataProductos() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT lower(codigo), lower(nombre), case when (select precio_venta from compras where id = (select max(id) from compras where producto = id)) is null then costoventa else (select precio_venta from compras where id = (select max(id) from compras where producto = id)) end costoventa, case when linea=1 then '22' else '1' end cantidad, id \nFROM productos p where codigo != 'fra#000' order by 1");
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

    public ArrayList<String[]> getDataProductosLibreria(String str) {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT lower(codigo)::int, lower(nombre), case when (select precio_venta from compras where id = (select max(id) from compras where producto = id)) is null then \"costoVenta\"\nelse (select precio_venta from compras where id = (select max(id) from compras where producto = id)) end costoventa, case when linea=1 then '22' else '1' end cantidad, id \nFROM productos p where (select lower(nombre) from bodega where id=p.bodega)=lower('" + str + "') order by 1");
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
            Log.e("", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public ArrayList<String[]> getDataProductosFiltrado(String str) {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT lower(codigo)::int, lower(nombre), case when (select precio_venta from compras where id = (select max(id) from compras where producto = id)) is null then \"costoVenta\"\nelse (select precio_venta from compras where id = (select max(id) from compras where producto = id)) end costoventa, '1' cantidad, id FROM productos where linea= '" + str + "' order by 1");
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
            Log.e("", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public ArrayList<String[]> getDataProductos2() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT lower(codigo), lower(nombre), case when (select precio_venta from compras where id = (select max(id) from compras where producto = id)) is null then \"costoVenta\"\nelse (select precio_venta from compras where id = (select max(id) from compras where producto = id)) end costoventa, '1' cantidad, id FROM productos2 order by 1");
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
            Log.e("getDataProductos2", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] getClientes() {
        String[] strArr = new String[(getCantidadClientes() + 1)];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT 'Cliente' nombre union all SELECT lower(nombre) FROM cliente WHERE desvincular = 'NO'");
            int i = 0;
            while (executeQuery.next()) {
                strArr[i] = executeQuery.getObject(1).toString();
                i++;
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("getClientes", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] getVendedores() {
        String[] strArr = new String[(getCantidadVendedores() + 1)];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT 'Vendedor' nombre union all SELECT lower(nombre) FROM vendedor WHERE desvincular = 'NO'");
            int i = 0;
            while (executeQuery.next()) {
                strArr[i] = executeQuery.getObject(1).toString();
                i++;
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("getVendedores", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] getBodegas() {
        String[] strArr = new String[getCantidadBodegas()];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT lower(nombre) FROM bodega WHERE desvincular = 'NO' order by 1");
            int i = 0;
            while (executeQuery.next()) {
                strArr[i] = executeQuery.getObject(1).toString();
                i++;
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("getVendedores", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] getFragancias() {
        String[] strArr = new String[(getCantidadFragancias() + 1)];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT 'Fragancia' nombre union all (SELECT lower(nombre) FROM productos WHERE linea = 1 order by 1)");
            int i = 0;
            while (executeQuery.next()) {
                strArr[i] = executeQuery.getObject(1).toString();
                i++;
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("getVendedores", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] getCajas() {
        String[] strArr = new String[(getCantidadCajas() + 1)];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT 'Caja' nombre union all SELECT lower(codigo) FROM productos WHERE linea = 6");
            int i = 0;
            while (executeQuery.next()) {
                strArr[i] = executeQuery.getObject(1).toString();
                i++;
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("getVendedores", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] getEmbases() {
        String[] strArr = new String[(getCantidadEmbases() + 1)];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT 'Embase' nombre union all SELECT lower(codigo) FROM productos WHERE linea = 3");
            int i = 0;
            while (executeQuery.next()) {
                strArr[i] = executeQuery.getObject(1).toString();
                i++;
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("getVendedores", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public int getCantidadClientes() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select count(id) from cliente WHERE desvincular = 'NO'");
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

    public int getCantidadVendedores() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select count(id) from vendedor WHERE desvincular = 'NO'");
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

    public int getCantidadBodegas() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select count(id) from bodega WHERE desvincular = 'NO'");
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

    public int getCantidadFragancias() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT count(codigo) FROM productos WHERE linea = 1");
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

    public int getCantidadCajas() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT count(codigo) FROM productos WHERE linea = 6");
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

    public int getCantidadEmbases() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT count(codigo) FROM productos WHERE linea = 3");
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

    public String getValor(String str) {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select \"costoVenta\"::varchar from productos WHERE lower(codigo) = lower('" + str + "')");
            String str2 = "0";
            while (executeQuery.next()) {
                str2 = executeQuery.getString(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return str2;
        } catch (SQLException e) {
            Log.e("getData", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return "0";
        }
    }

    public String getValorNombre(String str) {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select \"costoVenta\"::varchar from productos WHERE lower(nombre) = lower('" + str + "')");
            String str2 = "0";
            while (executeQuery.next()) {
                str2 = executeQuery.getString(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return str2;
        } catch (SQLException e) {
            Log.e("getData", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return "0";
        }
    }

    public String getNombre(String str) {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select lower(nombre) from productos WHERE lower(codigo) = lower('" + str + "')");
            String str2 = "0";
            while (executeQuery.next()) {
                str2 = executeQuery.getString(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return str2;
        } catch (SQLException e) {
            Log.e("getData", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return "0";
        }
    }

    public void insertarVenta() {
        try {
            Connection connection = c.conectar().getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO facturar (numFactura, fechaFactura, cliente, producto, catidadProductos, estado, precio, credito, precio_unitario, usuario, vendedor, porcentaje, origen, formapago, dinerorecibido, cambio, costoventa2, costoventa3, imprimir, subtotal, ivavalor, fechaimpresion, bodega, notacredito, fechacredito, formapago1, dinerorecibido1) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prepareStatement.setInt(1, getNumfactura());
            prepareStatement.setString(2, getFecha());
            prepareStatement.setInt(3, getCliente());
            prepareStatement.setInt(4, getProducto());
            prepareStatement.setString(5, getCatidadProductos());
            prepareStatement.setString(6, "activo");
            prepareStatement.setDouble(7, getPrecio());
            prepareStatement.setInt(8, getCredito());
            prepareStatement.setDouble(9, getPrecio_unitario());
            prepareStatement.setInt(10, getUsuario());
            prepareStatement.setString(11, getVendedor());
            prepareStatement.setInt(12, getPorcentaje());
            prepareStatement.setString(13, getOrigen());
            prepareStatement.setString(14, getFormapago());
            prepareStatement.setInt(15, getDineroRecibido());
            prepareStatement.setInt(16, getCambio());
            prepareStatement.setInt(17, getCostoVenta2());
            prepareStatement.setInt(18, getCostoVenta3());
            prepareStatement.setBoolean(19, isImprimir());
            prepareStatement.setInt(20, getSubtotal());
            prepareStatement.setInt(21, getIvavalor());
            prepareStatement.setString(22, getFechaimpresion());
            prepareStatement.setInt(23, getBodega());
            prepareStatement.setString(24,getNotacredito());
            prepareStatement.setString(25, getFechacredito());
            prepareStatement.setString(26, getFormapago1());
            prepareStatement.setInt(27, getDinerorecibido1());
            prepareStatement.executeUpdate();
            prepareStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.e("insertarVenta", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public void insertarVentaFragancia() {
        try {
            Connection connection = c.conectar().getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO facturar (numFactura, fechaFactura, cliente, producto, catidadProductos, estado, precio, credito, precio_unitario, usuario, vendedor, identificador, fragancia, porcentaje, gramos, iva, valorconiva, formapago, origen, dinerorecibido, cambio ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prepareStatement.setInt(1, ventaNum());
            prepareStatement.setString(2, getFecha());
            prepareStatement.setInt(3, getCliente());
            prepareStatement.setInt(4, getProducto());
            prepareStatement.setString(5, getCatidadProductos());
            prepareStatement.setString(6, "activo");
            prepareStatement.setDouble(7, getPrecio());
            prepareStatement.setInt(8, getCredito());
            prepareStatement.setDouble(9, getPrecio_unitario());
            prepareStatement.setInt(10, getUsuario());
            prepareStatement.setString(11, getVendedor());
            prepareStatement.setInt(12, 0);
            prepareStatement.setInt(13, getProducto());
            prepareStatement.setInt(14, 0);
            prepareStatement.setInt(15, 0);
            prepareStatement.setInt(16, 0);
            prepareStatement.setInt(17, 0);
            prepareStatement.setString(18, "EFECTIVO");
            prepareStatement.setString(19, "Celular");
            prepareStatement.setDouble(20, getPrecio());
            prepareStatement.setInt(21, 0);
            prepareStatement.executeUpdate();
            prepareStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.e("insertarVenta", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public int ventaNum() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT max(numFactura) FROM facturar");
            while (executeQuery.next()) {
                setNumfactura(executeQuery.getInt(1)+1);
            }
            executeQuery.close();
            conectar.close();
        } catch (SQLException e) {
            Log.e("Num Venta", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return getNumfactura();
    }

    public ArrayList<String[]> getClientes2() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[2];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT id, lower(nombre) FROM cliente WHERE desvincular = 'NO'");
            while (executeQuery.next()) {
                String[] strArr2 = new String[2];
                int i = 0;
                while (i < 2) {
                    int i2 = i + 1;
                    strArr2[i] = executeQuery.getObject(i2).toString();
                    i = i2;
                }
                arrayList.add(strArr2);
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            executeQuery.close();
            conectar.getConnection().close();
            return arrayList;
        } catch (SQLException e) {
            Log.e("getData", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] ultimaFactura() {
        String[] strArr = new String[6];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select distinct numfactura, (select lower(nombre) from cliente where id=cliente), lower(vendedor), fechafactura, (select lower(usuarionombre) from usuario where id=usuario), sum(precio) from facturar where numfactura = (select max(numfactura) from facturar) group by 1,2,3,4,5");
            while (executeQuery.next()) {
                int i = 0;
                while (i < 6) {
                    int i2 = i + 1;
                    strArr[i] = executeQuery.getObject(i2).toString();
                    i = i2;
                }
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("ultimaFactura", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String[] ultimaFacturaAgrupado() {
        String[] strArr = new String[6];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select distinct numfactura, (select lower(nombre) from cliente where id=cliente), lower(vendedor), fechafactura, (select lower(usuarionombre) from usuario where id=usuario), sum(precio) from facturar where numfactura = (select max(numfactura) from facturar) group by 1,2,3,4,5");
            while (executeQuery.next()) {
                int i = 0;
                while (i < 6) {
                    int i2 = i + 1;
                    strArr[i] = executeQuery.getObject(i2).toString();
                    i = i2;
                }
            }
            executeQuery.close();
            conectar.getConnection().close();
            return strArr;
        } catch (SQLException e) {
            Log.e("ultimaFactura", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public ArrayList<String[]> getProdVendidos() {
        ResultSet resultSet;
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            try {
                resultSet = conectar.executeQuery("select (select lower(codigo) from productos2 where id=producto), (select lower(nombre) from productos2 where id=producto), catidadproductos, precio_unitario, id from facturar where numfactura = (select max(numfactura) from facturar)");
            } catch (Exception unused) {
                resultSet = conectar.executeQuery("select (select lower(codigo) from productos where id=producto), (select lower(nombre) from productos where id=producto), catidadproductos, precio_unitario, id from facturar where numfactura = (select max(numfactura) from facturar)");
            }
            while (resultSet.next()) {
                String[] strArr2 = new String[5];
                int i = 0;
                while (i < 5) {
                    int i2 = i + 1;
                    strArr2[i] = resultSet.getObject(i2).toString();
                    i = i2;
                }
                arrayList.add(strArr2);
            }
            resultSet.close();
            conectar.getConnection().close();
            return arrayList;
        } catch (SQLException e) {
            Log.e("getProdVendidos", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public ArrayList<String[]> getProdVendidosLibreria() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select (select lower(codigo) from productos where id=producto), (select lower(nombre) from productos where id=producto), catidadproductos, precio_unitario, id from facturar where numfactura = (select max(numfactura) from facturar)");
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
            Log.e("getProdVendidos", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public ArrayList<String[]> getProdVendidosLibreriaAgrupados() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr = new String[5];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select (select linea from productos where id=producto), (select lower(nombre) from linea where id=(select linea from productos where id=producto)), sum(catidadproductos::int), \nprecio_unitario, 0 from facturar where numfactura = (select max(numfactura) from facturar) group by 1,2,4,5");
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
            Log.e("getProdVendidos", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public String getAdicional() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT valor FROM parametros where id=26;");
            String str = "0";
            while (executeQuery.next()) {
                str = executeQuery.getString(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return str;
        } catch (SQLException e) {
            Log.e("getData", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return "0";
        }
    }
}
