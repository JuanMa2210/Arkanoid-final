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
    public void restarImpactos(){
        this.impactos--;    //cuando llegue a cero, el bloque va a estar destruido
    }

    public  boolean tieneBonus(){   
        return true;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public int getHeight() {
        return this.alto;
    }
    @Override
    public int getWidth() {
        return this.ancho;
    }

    //Este metodo se puede implementar aca si va a ser para todos los ladrillos el mismo rebote
    public void rebote(Esfera esfera){
        if(this.cuerpo.intersects(esfera.getStruct())){
            double xEsfera=esfera.getX();   //CREO QUE ACA ESTA MAL, PORQUE NO ESTOY CONSIDERANDO 
            double yEsfera=esfera.getY();           //EL CUERPO DE LA PELOTA

            if(xEsfera==this.x){
                //PEGO EN EL LADO IZQUIERDO
            }else{
                if(xEsfera==this.x+this.ancho){
                    //PEGO EN EL LADO DERECHO
                }else{
                    if(yEsfera==this.y){
                        //PEGO EN EL LADO DE ARRIBA
                    }else{
                        if(yEsfera==this.y+this.alto){
                            //PEGO EN EL LADO DE ABAJO
                        }
                    }
                }
            }
            this.restarImpactos();
        }
    }

    public int getImpactos(){
        return(this.impactos);
    }

}