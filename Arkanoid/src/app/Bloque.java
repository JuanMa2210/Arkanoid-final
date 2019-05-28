package app;


import java.awt.Image;
import java.awt.geom.Rectangle2D;



public abstract class Bloque extends ObjetoGrafico {
    protected double x;
    protected double y;
    protected Rectangle2D cuerpo;
    protected int ancho=45;
    protected int alto=20;
    protected int impactos;
    protected int puntaje;
    protected boolean tieneBonus;
    protected Image img;
    //ELL ANCHO Y EL ALTO DE LOS LADRILLOS VA A SER FIJO ASI QUE PODRIA IR ACA

    //este metodo tambien es para todos igual
    public int contarImpactos(){
        return(this.impactos--);    //cuando llegue a cero, el bloque va a estar destruido
    }

    public  boolean tieneBonus(){   
        return true;
    }

    public abstract void destruirse();  //ESTO TIENE QUE QUITAR EL BLOQUE DEL VECTOR EN ESCENARIO

    //Este metodo se puede implementar aca si va a ser para todos los ladrillos el mismo rebote
    public void rebote(Esfera esfera){
        if(this.cuerpo.intersects(esfera.getStruct())){
            //ME TENGO QUE FIJAR EN QUE LADO PEGA.
            //ASI MODIFICO EL VECTOR DIRECCION
        }
    }

}