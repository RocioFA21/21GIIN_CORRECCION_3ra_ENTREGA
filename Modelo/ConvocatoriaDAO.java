package Modelo;

import Vista.ManageConvocatoria;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConvocatoriaDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion conectar = new Conexion() {};
    Connection acceso;
    
    public List listar(){
        List<ConvocatoriaEntidad>datos=new ArrayList<>();
        try {
            acceso=conectar.Conectar();
            ps=acceso.prepareStatement("select * from convocatoria");
            rs=ps.executeQuery();
            while(rs.next()){
                ConvocatoriaEntidad ue = new ConvocatoriaEntidad();
                ue.setId(rs.getInt(1));
                ue.setNombre(rs.getString(2));
                ue.setDescrip(rs.getString(3));
                ue.setFechaA(rs.getDate(4));
                ue.setFechaC(rs.getDate(5));
                ue.setEstado(rs.getBoolean(6));
                ue.setDocs(rs.getString(7));
                datos.add(ue);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio el siguiente error:" + e.getMessage());
        }
        
        return datos;
    }
    
    public int insertar(ConvocatoriaEntidad u) {
        int r=0;
        String sql="INSERT INTO convocatoria (nombre,descrip,fechaA,fechaC,estado,documentos) VALUES (?,?,?,?,?,?) ";
        java.sql.Date fechaA = new java.sql.Date(System.currentTimeMillis());
        try {
            acceso=conectar.Conectar();
            ps=(com.mysql.jdbc.PreparedStatement) acceso.prepareStatement(sql);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getDescrip());
            ps.setDate(3,fechaA);
            ps.setDate(4,null);
            ps.setBoolean(5,u.isEstado());
            ps.setString(6,u.getDocs());
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
    public int actualizar(ConvocatoriaEntidad u) {  
        int r=0;
        String sql="update convocatoria set nombre=?,descrip=?,fechaC=?,estado=?,documentos=? where id=?";        
        try {
            acceso=conectar.Conectar();
            ps = acceso.prepareStatement(sql);            
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getDescrip());
            ps.setDate(3, (Date) u.getFechaC());
            ps.setBoolean(4,u.isEstado());
            ps.setString(5,u.getDocs());
            ps.setInt(6,u.getId());
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
        String sql="delete from convocatoria where Id="+id;
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
