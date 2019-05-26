package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Nave extends ObjetoGrafico implements Movible {
    protected double x;
    protected double y;
    protected Image img_nave=null;
    protected Rectangle2D cuerpo=new Rectangle();

    public Nave(){
        this.cuerpo.setRect(this.x, this.y, this.getWidth(), 12);
        this.x=217.0;
        this.y=550.0;
    }

    public void cargarElementos(){
        try {
            //aca va a ir la nave que elija el usuario, ahora solo tenemos esta
            this.img_nave=ImageIO.read(getClass().getResource("imagenes/naveNormal.png"));
        } catch (Exception e) {
            System.out.println("Error al cargar imagenes");
        }
    }

    @Override
    public void setPosition(double x, double y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public void setX(double x) {
        this.x=x;
    }

    @Override
    public void setY(double y) {
        this.y=y;
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
    public void update(double delta) {
        
    }

    public void setImagen(BufferedImage img){
        this.img_nave=img.getScaledInstance(60,12, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.img_nave,(int)this.getX(),(int)this.getY(),null);
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