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
    public void mostrar(){
        System.out.println(this.nombre);
        System.out.println(this.puntos);
        System.out.println(this.nivel);
        System.out.println(this.fecha);
    }
}