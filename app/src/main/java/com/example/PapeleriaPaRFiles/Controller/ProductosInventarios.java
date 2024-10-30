package com.example.PapeleriaPaRFiles.Controller;

public class ProductosInventarios {

    String codigo;
    String producto;
    String cantidad;
    String costocompra;
    String costoventa;
    String can;

    public ProductosInventarios(String str, String str2, String str3, String str4, String str5, String str6) {
        this.codigo = str;
        this.producto = str2;
        this.cantidad = str3;
        this.costocompra = str4;
        this.costoventa = str5;
        this.can = str6;
    }

    public String getProducto() {
        return this.producto;
    }

    public String getCodigo() { return this.codigo; }

    public String getCostoVenta() { return this.costoventa; }

    public String getCantidad() {
        return this.cantidad;
    }

    public String getCostoCompra() {
        return this.costocompra;
    }

    public String getCan() { return this.can; }

    public void setProducto(String str) {
        this.producto = str;
    }

    public void setCodigo(String str) {
        this.codigo = str;
    }

    public void setCantidad(String str) {
        this.cantidad = str;
    }

    public void setCostoCompra(String str) {
        this.costocompra = str;
    }

    public void setCostoVenta(String str) {
        this.costoventa = str;
    }

    public void setCan(String str) {
        this.can = str;
    }
}
