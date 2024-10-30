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

public class ClienteController {
    
    Conexion c = new Conexion();
    String nombreCliente;
    String nitCliente;
    String direccionCliente;
    String telefonoCliente;
    String celularCliente;
    String correoCliente;
    String idCliente;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getFecha(){
        return sdf.format(new Date());
    }
    
    public ArrayList<String[]> getClientes() {
        int numColumns = 8;
        ArrayList<String[]> arrayList = new ArrayList<>();
        try {
            String[] strArr2;
            Statement con = c.conectar();
            ResultSet rs = con.executeQuery("SELECT id, lower(nombre), nit, direccion, telefono, celular, correo, estado FROM cliente WHERE desvincular = 'NO'");
            while (rs.next()) {
                strArr2 = new String[numColumns];
                for (int i=0; i < numColumns; i++){
                    strArr2[i] = rs.getObject(i+1).toString();
                }
                arrayList.add(strArr2);
            }
            rs.close();
            con.getConnection().close();
        } catch (SQLException e) {
            Log.e("getDataClientes", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
        return arrayList;
    }

    public int getCantidadClientes() {
        try {
            Statement con = c.conectar();
            ResultSet rs = con.executeQuery("select count(id) from cliente WHERE desvincular = 'NO'");
            int i = 0;
            while (rs.next()) {
                i = rs.getInt(1);
            }
            rs.close();
            con.getConnection().close();
            return i;
        } catch (SQLException e) {
            Log.e("getData", "El Error es: " + e.getMessage());
            e.getStackTrace();
            return 0;
        }
    }

    public void addCliente() {
        try {
            Connection con = c.conectar().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cliente (nombre, nit, direccion, telefono, celular, correo, estado, desvincular, valor_credito, fecha_credito, remitente, cedularemitente, telefonoremitente) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, nombreCliente);
            ps.setString(2, nitCliente);
            ps.setString(3, direccionCliente);
            ps.setString(4, telefonoCliente);
            ps.setString(5, celularCliente);
            ps.setString(6, correoCliente);
            ps.setString(7, "activo");
            ps.setString(8, "NO");
            ps.setInt(9, 0);
            ps.setString(10, getFecha());
            ps.setString(11, "-");
            ps.setString(12, "-");
            ps.setString(13, "-");
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            Log.e("addCliente", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modifyCliente() {
        try {
            Connection con = c.conectar().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE cliente SET nombre = ?, nit=?, direccion=?, telefono=?, celular=?, correo=?, estado = ?, valor_credito = ?, fecha_credito = ? WHERE id =" + getIdCliente());
            ps.setString(1, nombreCliente);
            ps.setString(2, nitCliente);
            ps.setString(3, direccionCliente);
            ps.setString(4, telefonoCliente);
            ps.setString(5, celularCliente);
            ps.setString(6, correoCliente);
            ps.setString(7, "activo");
            ps.setInt(8, 0);
            ps.setString(9, getFecha());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            Log.e("addCliente", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteCliente() {
        try {
            Connection con = c.conectar().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE cliente SET desvincular='SI' WHERE id =" + getIdCliente());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            Log.e("addCliente", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
