package app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import java.io.File;
//para sonido

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BonusCatch extends Bonus {
    //ESTE BONUS ALARGA LA NAVE
    public BonusCatch(Escenario escenario,double x,double y){
        this.x=x;
        this.y=y;
        this.cuerpo=new Rectangle();
        this.cuerpo.setRect(this.x,this.y,ancho,alto);
        this.escenario=escenario;
        //LA POSICION DEL BONUS VA A SER LA MISMA POSICION DEL BLOQUE QUE LO TENGA
        try {
            this.imagen=ImageIO.read(getClass().getResource("imagenes/Catch.gif"));
            this.imagen=imagen.getScaledInstance(ancho,alto, imagen.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR IMAGEN BONUS Catch");
        }
    }

    @Override
    public void mover() {
        this.y=this.y+velocidad();
        this.cuerpo.setRect(this.x,this.y,ancho,alto);
    }

    @Override
    public double velocidad() {
        return 1.0;
    }

    @Override
    public double aceleracion() {
        return 0;
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
        Nave nave=escenario.getNave();
        if(nave.isActivo()==false){
            for (Esfera esfera : escenario.getBolas()){
                nave.setActivo(true);
                nave.setTipoBonusActivo(1);
                
                    try {
                        Clip sonido = AudioSystem.getClip();
                        File a = new File("C:/Users/Julian/Documents/MEGAsync/Kraken/poo/Arkanoid/bin/app/Sonidos/BonusGeneral.wav");
                        sonido.open(AudioSystem.getAudioInputStream(a));
                        sonido.start();
                     } catch (Exception tipoError) {
                        System.out.println("" + tipoError);
                     }

            }
            duracion();
            
        }
        
    }

    public void duracion(){
        new Timer().schedule(new TimerTask() {
            Nave nave=escenario.getNave();
           @Override
           public void run() {
               nave.setActivo(false); 
               nave.setTipoBonusActivo(0);
           }
       },7000);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.imagen, (int)this.x, (int)this.y, null);
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