package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MunicipioDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion conectar = new Conexion() {};
    Connection acceso;
    
    public List listar(){
        List<MunicipioEntidad>datos=new ArrayList<>();
        try {
            acceso=conectar.Conectar();
            ps=acceso.prepareStatement("select * from municipio");
            rs=ps.executeQuery();
            while(rs.next()){
                MunicipioEntidad ue = new MunicipioEntidad();
                ue.setId(rs.getInt(1));
                ue.setNombre(rs.getString(2));
                ue.setCategoria(rs.getString(3));
                datos.add(ue);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio el siguiente error:" + e.getMessage());
        }
        
        return datos;
    }
    
    public int insertar(MunicipioEntidad u) {
        int r=0;
        String sql="INSERT INTO municipio (nombre, categoria) VALUES (?, ?) ";
        try {
            acceso=conectar.Conectar();
            ps=(com.mysql.jdbc.PreparedStatement) acceso.prepareStatement(sql);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getCategoria());
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
    public int actualizar(MunicipioEntidad u) {  
        int r=0;
        String sql="update municipio set nombre=?,categoria=? where id=?";
        try {
            acceso=conectar.Conectar();
            ps = acceso.prepareStatement(sql);            
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getCategoria());
            ps.setInt(3,u.getId());
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
        String sql="delete from municipio where Id="+id;
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
