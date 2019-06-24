package app;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

import javax.management.timer.Timer;

public abstract class Bonus extends ObjetoGrafico implements Movible{
    protected int Duracion=5;
    protected Image imagen;
    protected Rectangle2D cuerpo;
    protected int ancho=30;
    protected int alto=15;
    protected double x;
    protected double y;
    protected Escenario escenario;
    protected int tipoBonus;

    protected Rectangle2D getBounds(){
        return this.cuerpo;
    }
    
    protected int TipoBonus(){
        return this.tipoBonus;
    }
}