package app;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BonusEnlarge extends Bonus {
    //ESTE BONUS ALARGA LA NAVE
    public BonusEnlarge(Escenario escenario){
        
        //LA POSICION DEL BONUS VA A SER LA MISMA POSICION DEL BLOQUE QUE LO TENGA
        try {
            //CARGAR IMAGEN
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR IMAGEN BONUS ENLARGE");
        }
    }

    @Override
    public void mover() {

    }

    @Override
    public int velocidad() {
        return 0;
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