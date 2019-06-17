package app;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;



public abstract class Bloque extends ObjetoGrafico {
    protected double x;
    protected double y;
    protected Rectangle2D cuerpo = new Rectangle() ;
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

    //Este metodo se puede implementar aca si va a ser para todos los ladrillos el mismo rebote
    public void rebote(Esfera esfera){//no puedo lograr que ingrese
        
       /* if(esfera.getBounds().intersects(getBounds())){
            System.out.println("chocamo un bloque");
            double xEsfera=esfera.getX();   //CREO QUE ACA ESTA MAL, PORQUE NO ESTOY CONSIDERANDO 
            double yEsfera=esfera.getY();           //EL CUERPO DE LA PELOTA
            if(xEsfera==this.x || xEsfera==this.x+this.ancho){
                //PEGO EN ALGUNO DE LOS LADOS
                esfera.setDX(esfera.getDX()*-1);
            }else{
                if(yEsfera==this.y || yEsfera==this.y+this.alto){
                    //PEGO ARRIBA O ABAJO
                    esfera.setDY(esfera.getDY()*-1);
                }
            }
            this.restarImpactos();
        }*/
    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.getX(), (int)this.getY(), this.ancho, this.alto);
    }

    public int getImpactos(){
        return(this.impactos);
    }

}