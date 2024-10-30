package com.example.PapeleriaPaRFiles.Controller;

import android.util.Log;

public class ProductosFacturar {

    String cantidad;
    String can;
    String codigo;
    String id;
    String precio;
    String producto;
    String subtotal;

    public ProductosFacturar(String str, String str2, String str3, String str4, String str5, String str6) {
        this.codigo = str;
        this.producto = str2;
        this.cantidad = str3;
        this.precio = str4;
        this.id = str5;
        this.can = str6;
        if (getCantidad().equals("$")) {
            setCantidad("1");
        }
        this.subtotal = Integer.parseInt(getPrecio()) * Integer.parseInt(getCantidad()) + "";
    }

    public String getProducto() {
        return this.producto;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getSubtotal() { return this.subtotal; }

    public String getCantidad() {
        return this.cantidad;
    }

    public String getPrecio() {
        return this.precio;
    }

    public String getId() {
        return this.id;
    }

    public String getCan() { return this.can; }

    public void setId(String str) {
        this.id = str;
    }

    public void setProducto(String str) {
        this.producto = str;
    }

    public void setCodigo(String str) {
        this.codigo = str;
    }

    public void setCantidad(String str) {
        this.cantidad = str;
    }

    public void setPrecio(String str) {
        this.precio = str;
    }

    public void setSubtotal(String str) {
        this.subtotal = str;
    }

    public void setCan(String str) {
        this.can = str;
    }
}
