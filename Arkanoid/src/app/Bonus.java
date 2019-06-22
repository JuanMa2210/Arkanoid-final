package app;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Bonus extends ObjetoGrafico implements Movible{
    protected int duracion=10;
    protected Image imagen;
    protected Rectangle2D cuerpo;
    protected int ancho=30;
    protected int alto=15;
    protected double x;
    protected double y;
    protected Escenario escenario;

    protected Rectangle2D getBounds(){
        return this.cuerpo;
    }
}