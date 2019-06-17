package app;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;



public abstract class Bloque extends ObjetoGrafico {
    protected double x;
    protected double y;
    protected Rectangle2D cuerpo = new Rectangle2D.Double(this.x,this.y,this.ancho,this.alto);
    protected int ancho=45;
    protected int alto=20;
    protected int impactos;
    protected int puntaje;
    protected boolean tieneBonus;
    protected Image img;
    //protected Bonus bonus=null;

    
    public void restarImpactos(){
        this.impactos--;    //cuando llegue a cero, el bloque va a estar destruido
    }

    public  void asignarBonus(){   //PUEDE ASIGNARLE UN BONUS AL BLOQUE O NO.
        
        if((int)Math.floor(Math.random()*4+1)==1){  //GENERA UN NUMERO DEL 1 AL 4 Y SOLO ASIGNA BONUS SI ES 1
            int azar=(int)Math.floor(Math.random()*6+1);
            switch(azar){
                case 1: break;
                case 2: break;      //una opcion para cada bonus
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
            }
        }
    }

    /*public Bonus lanzarBonus(){
        return this.bonus;              hay que ver que pasa aca cuando quiera devolver algo que no tenga
    }*/

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


    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(this.getX(),this.getY(), this.ancho, this.alto);
    }

    public int getImpactos(){
        return(this.impactos);
    }

}