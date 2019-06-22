package app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;

import javax.imageio.ImageIO;

public class BonusEnlarge extends Bonus {
    //ESTE BONUS ALARGA LA NAVE
    public BonusEnlarge(Escenario escenario,int x,int y){
        this.x=x;
        this.y=y;
        this.cuerpo=new Rectangle();
        this.cuerpo.setRect(this.x,this.y,ancho,alto);
        this.escenario=escenario;
        //LA POSICION DEL BONUS VA A SER LA MISMA POSICION DEL BLOQUE QUE LO TENGA
        try {
            this.imagen=ImageIO.read(getClass().getResource("imagenes/Enlarge.gif"));
            this.imagen=imagen.getScaledInstance(ancho,alto, imagen.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR IMAGEN BONUS ENLARGE");
        }
    }

    @Override
    public void mover() {
        this.y=this.y+velocidad();
        this.cuerpo.setRect(this.x,this.y,ancho,alto);
    }

    @Override
    public int velocidad() {
        return 1;
    }

    @Override
    public int aceleracion() {
        return 0;
    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setImagen(BufferedImage img) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void update(double delta) {
        Nave nave=escenario.getNave();
        if(nave.isActivo()==false && this.getBounds().intersects(nave.getBounds())){
            nave.setWidth(nave.getWidth()+40);
            nave.getBounds().setRect(nave.getX(), nave.getY(), nave.getWidth(), nave.getHeight());
            nave.setActivo(true);
        }
        
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.imagen, this.x, this.y, null);
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