package com.example.PapeleriaPaRFiles.Controller;

import android.util.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReporteController {

    Conexion c = new Conexion();

    public String[] ultimaFactura(int numfact) {
        String[] strArr = new String[6];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select distinct numfactura, lower(c.nombre), lower(vendedor), fechafactura, lower(u.usuarionombre), sum(precio) \n" +
                    "from facturar f inner join cliente c on c.id=f.cliente inner join usuario u on u.id=f.usuario where numfactura = " + numfact + " group by 1,2,3,4,5");
            while (executeQuery.next()) {
                int i = 0;
                while (i < 6) {
                    strArr[i] = executeQuery.getObject(i+1).toString();
                    i++;
                }
            }
            executeQuery.close();
            conectar.getConnection().close();
        } catch (SQLException e) {
            Log.e("ultimaFactura", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return strArr;
    }

    public int ventaNum() {
        int num = 0;
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT max(numFactura) FROM facturar");
            while (executeQuery.next()) {
                num = executeQuery.getInt(1);
            }
            executeQuery.close();
            conectar.close();
        } catch (SQLException e) {
            Log.e("Num Venta", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return num;
    }

    public String[] ultimaCompra(int numcom) {
        String[] strArr = new String[6];
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select distinct numfactura, lower(pr.nombre), '', fechafactura, lower(u.usuarionombre), sum(precio)\n" +
                    "from compras c inner join proveedor pr on pr.id=c.proveedor inner join usuario u on u.id=c.usuario where numfactura = " + numcom + " group by 1,2,3,4,5");
            while (executeQuery.next()) {
                int i = 0;
                while (i < 6) {
                    strArr[i] = executeQuery.getObject(i+1).toString();
                    i++;
                }
            }
            executeQuery.close();
            conectar.getConnection().close();
        } catch (SQLException e) {
            Log.e("ultimaCompra", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return strArr;
    }

    public int compraNum() {
        int num = 0;
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT max(numfactura) FROM compras");
            while (executeQuery.next()) {
                num = executeQuery.getInt(1);
            }
            executeQuery.close();
            conectar.close();
        } catch (SQLException e) {
            Log.e("Num Compra", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return num;
    }

    public ArrayList<String[]> getProductosFactura(int fact) {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr;
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select lower(p.codigo), lower(p.nombre), catidadproductos, precio_unitario\n" +
                    "from facturar f inner join productos p on p.id=f.producto where f.numfactura = " + fact);
            while (executeQuery.next()) {
                strArr = new String[4];
                int i = 0;
                while (i < 4) {
                    strArr[i] = executeQuery.getObject(i+1).toString();
                    i++;
                }
                arrayList.add(strArr);
            }
            executeQuery.close();
            conectar.getConnection().close();
        } catch (SQLException e) {
            Log.e("getProdVendidos", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return arrayList;
    }

    public ArrayList<String[]> getProductosCompra(int comp) {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strArr;
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("select lower(p.codigo), lower(p.nombre), catidadproductos, precio_unitario\n" +
                    "from compras c inner join productos p on p.id=c.producto where numfactura = " + comp);
            while (executeQuery.next()) {
                strArr = new String[4];
                int i = 0;
                while (i < 4) {
                    strArr[i] = executeQuery.getObject(i+1).toString();
                    i++;
                }
                arrayList.add(strArr);
            }
            executeQuery.close();
            conectar.getConnection().close();
        } catch (SQLException e) {
            Log.e("getProdComprados", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return arrayList;
    }
}
