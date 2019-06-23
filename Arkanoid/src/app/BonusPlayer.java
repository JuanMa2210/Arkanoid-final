package app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BonusPlayer extends Bonus {
    //ESTE BONUS ALARGA LA NAVE
    public BonusPlayer(Escenario escenario,double x,double y){
        this.x=x;
        this.y=y;
        this.cuerpo=new Rectangle();
        this.cuerpo.setRect(this.x,this.y,ancho,alto);
        this.escenario=escenario;
        //LA POSICION DEL BONUS VA A SER LA MISMA POSICION DEL BLOQUE QUE LO TENGA
        try {
            this.imagen=ImageIO.read(getClass().getResource("imagenes/Player.gif"));
            this.imagen=imagen.getScaledInstance(ancho,alto, imagen.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR IMAGEN BONUS Player");
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
    public int aceleracion() {
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
        if(nave.isActivo()==false && this.getBounds().intersects(nave.getBounds())){
            if(escenario.getVidas()<5){
                escenario.setVidas(escenario.getVidas()+1);
                try {
                    Clip sonido = AudioSystem.getClip();
                    File a = new File("C:/Users/Juan Manuel Lara/OneDrive/Documentos/GitKraken/poo/Arkanoid/bin/app/Sonidos/BonusLevelUp.wav");
                    sonido.open(AudioSystem.getAudioInputStream(a));
                    sonido.start();
                   // System.out.println("Reproduciendo 10s. de sonido...");
                   // Thread.sleep(200); // 10000 milisegundos (10 segundos)
                   // sonido.close();
                 } catch (Exception tipoError) {
                    System.out.println("" + tipoError);
                 }
            
            }
        }
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