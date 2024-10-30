package com.example.PapeleriaPaRFiles.Controller;

import android.util.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InventariosController {

    Conexion c = new Conexion();

    public ArrayList<String[]> reporte1() {
        int NumColumnas = 5;
        ArrayList<String[]> objetoProducto = new ArrayList<>();
        try {
            String Fila[];
            Statement stms = c.conectar();
            ResultSet rs = stms.executeQuery("select vw.codigo, lower(p.nombre), (productoss - factura + compras + devolucion - compras_anuladas + factura_anulada - bajas) cantidad,\n" +
                    "case when (select precio_unitario from compras where id = (select max(id) from compras where producto = p.id)) is null then p.costo\n" +
                    "else (select precio_unitario from compras where id = (select max(id) from compras where producto = p.id)) end costo,\n" +
                    "case when (select precio_venta from compras where id = (select max(id) from compras where producto = p.id)) is null then p.\"costoVenta\"\n" +
                    "else (select precio_venta from compras where id = (select max(id) from compras where producto = p.id)) end costoventa\n" +
                    "from vw_inventarios vw inner join productos p on p.id = vw.idproducto inner join bodega b on p.bodega = b.id inner join linea l\n" +
                    "on p.linea = l.id WHERE p.desvincular != 'SI' and p.codigo != 'ARP' order by 2");
            while (rs.next()) {
                Fila = new String[NumColumnas];
                for (int i = 0; i < NumColumnas; i++) {
                    Fila[i] = rs.getObject(i + 1).toString();
                }
                objetoProducto.add(Fila);
            }
            rs.close();
            stms.getConnection().close();
        } catch (SQLException e) {
            return reporte1Letras();
        }
        return objetoProducto;
    }

    public ArrayList<String[]> reporte1Letras() {
        int NumColumnas = 5;
        ArrayList<String[]> objetoProducto = new ArrayList<>();
        String Fila[];
        try {
            Statement stms = c.conectar();
            ResultSet rs = stms.executeQuery("select vw.codigo::numeric, lower(p.nombre), (productoss - factura + compras + devolucion - compras_anuladas + factura_anulada - bajas) cantidad,\n" +
                    "case when (select precio_unitario from compras where id = (select max(id) from compras where producto = p.id)) is null then p.costo\n" +
                    "else (select precio_unitario from compras where id = (select max(id) from compras where producto = p.id)) end costo,\n" +
                    "case when (select precio_venta from compras where id = (select max(id) from compras where producto = p.id)) is null then p.\"costoVenta\"\n" +
                    "else (select precio_venta from compras where id = (select max(id) from compras where producto = p.id)) end costoventa\n" +
                    "from vw_inventarios vw inner join productos p on p.id = vw.idproducto inner join bodega b on p.bodega = b.id inner join linea l\n" +
                    "on p.linea = l.id WHERE p.desvincular != 'SI' and p.codigo != 'ARP' order by 2");
            while (rs.next()) {
                Fila = new String[NumColumnas];
                for (int i = 0; i < NumColumnas; i++) {
                    Fila[i] = rs.getObject(i + 1).toString();
                }
                objetoProducto.add(Fila);
            }
            rs.close();
            stms.getConnection().close();
        } catch (SQLException e) {
            System.err.println("El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return objetoProducto;
    }
}
