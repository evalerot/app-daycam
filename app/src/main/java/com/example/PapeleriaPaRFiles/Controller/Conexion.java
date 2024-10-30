package com.example.PapeleriaPaRFiles.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexion {
    public static String[] ips = {"186.147.241.5", "192.168.1.150", "192.168.20.150", "192.168.1.220", "192.168.1.230", "192.168.1.240"};
    private static String bd = "dayPapeleria2PaR";
    private static final String clave = "Admin";
    private static String ip = "192.168.0.150";
    private static final String usuario = "postgres";

    public static String getBD() {
        return bd;
    }

    public static void setBD(Context c) {
        try {
            bd = PreferenceManager.getDefaultSharedPreferences(c).getString("BD", "dayPapeleria2PaR");
        }catch (Exception e){
            SharedPreferences.Editor myEditor = PreferenceManager.getDefaultSharedPreferences(c).edit();
            myEditor.putString("BD", "dayPapeleria2PaR");
            myEditor.apply();
            setBD(c);
        }
        try {
            ip = PreferenceManager.getDefaultSharedPreferences(c).getString("IP", "192.168.0.150");
        }catch (Exception e){
            SharedPreferences.Editor myEditor = PreferenceManager.getDefaultSharedPreferences(c).edit();
            myEditor.putString("IP", "192.168.0.150");
            myEditor.apply();
            setBD(c);
        }
    }

    public synchronized Statement conectar() {
        Statement createStatement = null;
        synchronized (Conexion.class) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
            try {
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection("jdbc:postgresql://" + ip + ":5432/" + getBD(), usuario, clave);
                createStatement = con.createStatement();
                Log.e("Base de Datos", getBD());
                Log.e("Ip", ip);
            } catch (Exception e) {
                Log.e("conectar()", "El Error es: " + e.getMessage());
                //return buscarBase();
            }
        }
        return createStatement;
    }

    /*
    private synchronized Statement buscarBase(){
        Statement st = null;
        int i=0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = null;
            while(con == null || st == null){
                try {
                    con = DriverManager.getConnection("jdbc:postgresql://" + ips[i] + ":5432/" + getBD(), usuario, clave);
                    st = con.createStatement();
                }catch (Exception e){
                    con = null;
                    st = null;
                    i++;
                }
            }
            ip = ips[i];
        } catch (Exception e) {
            Log.e("conectar()", "El Error es: " + e.getMessage());
            return null;
        }
        Log.e("Base de Datos", ips[i]);
        return st;
    }

     */
}
