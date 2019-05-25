package app;

public class Puntaje{
    protected String nombre;
    protected String puntos;
    protected String nivel;
    protected String fecha;

    public Puntaje(String nombre,String puntos,String nivel,String fecha){
        this.nombre=nombre;
        this.puntos=puntos;
        this.nivel=nivel;
        this.fecha=fecha;
    }
    public String getNombre(){
        return (this.nombre);
    }
    public String getPuntos(){
        return this.puntos;
    }
    public String getNivel(){
        return this.nivel;
    }
    public String getFecha(){
        return this.fecha;
    }
}