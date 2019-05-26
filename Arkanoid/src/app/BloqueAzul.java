package app;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BloqueAzul extends Bloque {

    public BloqueAzul(double x,double y){
        this.x=x;
        this.y=y;
        this.puntaje=100;
        this.cuerpo=new Rectangle((int)this.x,(int)this.y, 12, 8);
        this.impactos=1;
        try {
            //this.img= CARGAR IMAGEN DEL BLOQUE
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR IMAGEN BLOQUE AZUL");
        }
    }
    

    @Override
    public void rebote(Esfera esfera) {

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
    public void setImagen(BufferedImage img) {

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
        g.drawImage(this.img,(int)this.getX(),(int)this.getY(),null);
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