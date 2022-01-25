package Modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {

    Connection con = null;
    String db="proyectodb";
    String url="jdbc:mysql://rds-21giin.c0bc3kbxuj7d.us-east-1.rds.amazonaws.com:3306/"+db;
    String user="admin";
    String pass="admin1234";
    String driver = "com.mysql.jdbc.Driver";;
    
    public Connection Conectar() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio el siguiente error:" + e.getMessage());
        }
        return con;
    }
    
    public String getUrl() {
        return url;
    }

    public String getUss() {
        return user;
    }

    public String getPwd() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }
    
}



