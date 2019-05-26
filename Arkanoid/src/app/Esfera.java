package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Esfera extends ObjetoGrafico implements Movible {

    
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected Image img_bola = null;
    protected Rectangle2D estructura=new Rectangle();

    public Esfera(){
        this.estructura.setRect(this.x, this.y, 12, 12);
        this.x= 241.0;
        this.y= 540.0;
    }

    public void cargarElementos(){
        try {
            this.img_bola=ImageIO.read(getClass().getResource("imagenes/bola.png"));
        } catch (Exception e) {
            System.out.println("Error al cargar imagenes");
        }
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
        this.img_bola=img.getScaledInstance(12,12, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.img_bola,(int)this.getX(),(int)this.getY(),null);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setImagen(BufferedImage img) {

    }

}