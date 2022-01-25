package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static Vista.Login.password;
import static Vista.Login.usernameLogin;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion conectar = new Conexion() {};
    Connection acceso;
    
    public UsuarioEntidad ValidarUsuario(String nombre, String clave) throws ClassNotFoundException{
        UsuarioEntidad ue = new UsuarioEntidad();
        String sql="select * from usuarios where nombre=? and clave=?";
        try {
            acceso=conectar.Conectar();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, usernameLogin.getText());
            ps.setString(2, password.getText());
            rs=ps.executeQuery();
            
            while(rs.next()){
            ue.setId(rs.getInt(1));
            ue.setNombre(rs.getString(2));
            ue.setClave(rs.getString(3));
            ue.setTipo(rs.getString(4));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio el siguiente error:" + e.getMessage());
        }
        return ue;
    }
    
    public List listar(){
        List<UsuarioEntidad>datos=new ArrayList<>();
        try {
            acceso=conectar.Conectar();
            ps=acceso.prepareStatement("select * from usuarios");
            rs=ps.executeQuery();
            while(rs.next()){
                UsuarioEntidad ue = new UsuarioEntidad();
                ue.setId(rs.getInt(1));
                ue.setNombre(rs.getString(2));
                ue.setClave(rs.getString(3));
                ue.setTipo(rs.getString(4));
                datos.add(ue);
            }
            
        } catch (Exception e) {
        }
        
        return datos;
    }
    
    public int insertar(UsuarioEntidad u) {
        int r=0;
        String sql="INSERT INTO usuarios (nombre, clave, tipo) VALUES (?, ?, ?) ";
        try {
            acceso=conectar.Conectar();
            ps=(com.mysql.jdbc.PreparedStatement) acceso.prepareStatement(sql);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getClave());
            ps.setString(3,u.getTipo());
            r=ps.executeUpdate();
            if (r == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio el siguiente error:" + e.getMessage());
        }
        return r;
    }
    public int actualizar(UsuarioEntidad u) {  
        int r=0;
        String sql="update usuarios set nombre=?,clave=?,tipo=? where id=?";
        try {
            acceso=conectar.Conectar();
            ps = acceso.prepareStatement(sql);            
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getClave());
            ps.setString(3,u.getTipo());
            ps.setInt(4,u.getId());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio el siguiente error:" + e.getMessage());
        }
        return r;
    }
    public int Delete(int id){
        int r=0;
        String sql="delete from usuarios where Id="+id;
        try {
            acceso=conectar.Conectar();
            ps=acceso.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio el siguiente error:" + e.getMessage());
        }
        return r;
    }
}
