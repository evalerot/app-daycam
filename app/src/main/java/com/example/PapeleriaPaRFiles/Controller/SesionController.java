package com.example.PapeleriaPaRFiles.Controller;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PapeleriaPaRFiles.Presentacion.Sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SesionController extends AppCompatActivity {

    Conexion c = new Conexion();
    public int ConexionInicio(String str, String str2) {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT id FROM usuario where lower(usuarionombre)='" + str.toLowerCase() + "' and lower(pass)='" + str2.toLowerCase() + "';");
            int i = 0;
            while (executeQuery.next()) {
                i = executeQuery.getInt(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return i;
        } catch (SQLException e) {
            Log.e("ConexionInicio", "El Error es: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "SELECCIONE LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public boolean verifyIMEI(String str) {
        boolean val = false;
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT conectar FROM sesion_activa where informe like '2020@app%'");
            while (executeQuery.next()) {
                if (executeQuery.getString(1).equals(str)) {
                    val = true;
                }
            }
            executeQuery.close();
            conectar.getConnection().close();
        } catch (SQLException e) {
            Log.e("ConexionInicio", "El Error es: " + e.getMessage());
        }
        return val;
    }

    public int cantidadCelulares() {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT valor::int FROM parametros where id=100");
            int i = 0;
            while (executeQuery.next()) {
                i = executeQuery.getInt(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return i;
        } catch (SQLException e) {
            Log.e("CantidadCelulares", "El Error es: " + e.getMessage());
            return 0;
        }
    }

    public void setIMEI(String str, int i) {
        try {
            Connection connection = c.conectar().getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("UPDATE sesion_activa SET conectar=? WHERE informe='2020@app" + i + "';");
            prepareStatement.setString(1, str);
            prepareStatement.executeUpdate();
            prepareStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.e("setIMEI", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public void actualizarUsuario(String str, int i) {
        try {
            Connection connection = c.conectar().getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("UPDATE sesion_activa SET usuario=? WHERE conectar='" + str + "';");
            prepareStatement.setInt(1, i);
            prepareStatement.executeUpdate();
            prepareStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.e("setIMEI", "El Error es: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public int getUser(Context context) {
        try {
            Statement conectar = c.conectar();
            ResultSet executeQuery = conectar.executeQuery("SELECT usuario FROM sesion_activa where conectar='" + getIMEI(context) + "';");
            int i = 0;
            while (executeQuery.next()) {
                i = executeQuery.getInt(1);
            }
            executeQuery.close();
            conectar.getConnection().close();
            return i;
        } catch (SQLException e) {
            Log.e("ConexionInicio", "El Error es: " + e.getMessage());
            return 0;
        }
    }

    public String getIMEI(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }
}
