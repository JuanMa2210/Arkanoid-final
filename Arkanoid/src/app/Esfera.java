package app;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class Esfera extends ObjetoGrafico implements Movible {

    
    protected static double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected BufferedImage img_nave=null;

    public Esfera(){
        this.y= 400;
        this.x= 400;
    }

    public void mover(Rectangle2D limites, boolean colNav){
        x +=dx;
        y +=dy;
        
        if (y < limites.getMinY());{
            //definir metodo en escenario
           // Escenario.eliminarVidas();

            x= limites.getCenterX();
            y= limites.getCenterY();

            dy = -dy;


        }
    }

    @Override
    public void setPosition(double x, double y) {

    }

    @Override
    public void setX(double x) {

    }

    @Override
    public void setY(double y) {

    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

}