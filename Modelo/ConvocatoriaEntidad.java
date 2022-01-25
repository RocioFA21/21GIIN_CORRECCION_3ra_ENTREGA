package Modelo;

import java.util.Date;

public class ConvocatoriaEntidad {
    public int id;
    public String nombre;
    public String descrip;
    public Date fechaA;
    public Date fechaC;
    public boolean estado;
    public String docs;
    
    
    

    public ConvocatoriaEntidad() {
    }
    
    public ConvocatoriaEntidad(int id, String nombre, String descrip, Date fechaA, Date fechaC, boolean estado, String docs){
        this.id = id;
        this.nombre = nombre;
        this.descrip = descrip;
        this.fechaA= fechaA;
        this.fechaA= fechaC;
        this.estado= estado;
        this.docs = docs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Date getFechaA() {
        return fechaA;
    }

    public void setFechaA(Date fechaA) {
        this.fechaA = fechaA;
    }

    public Date getFechaC() {
        return fechaC;
    }

    public void setFechaC(Date fechaC) {
        this.fechaC = fechaC;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }
    
}
