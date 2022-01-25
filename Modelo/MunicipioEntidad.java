package Modelo;

public class MunicipioEntidad {
    public int id;
    public String nombre;
    public String categoria;

    public MunicipioEntidad() {
    }
    
    public MunicipioEntidad(int id, String nombre, String categoria){
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
}
