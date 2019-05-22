package app;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;

public class Escenario {
    protected BufferedImage img_fondoAzul = null;
    protected BufferedImage img_fondoVerde = null;
    protected BufferedImage img_fondoRojo = null;
    
    public Escenario(){
        this.cargar();
    } 

    //ESTE METODO CARGA TODAS LAS IMAGENES NECESARIAS PARA EL ESCENARIO
    private void cargar(){
        try {
            img_fondoAzul= ImageIO.read(getClass().getResource("imagenes/FondoAzul.png"));
            img_fondoVerde= ImageIO.read(getClass().getResource("imagenes/FondoAzul.png"));
            img_fondoRojo= ImageIO.read(getClass().getResource("imagenes/FondoAzul.png"));
        } catch (Exception e) {
            System.out.println("Error al cargas las imagenes del escenario");
        }
    }

    public void draw(Graphics2D g){
        g.drawImage(img_fondoAzul,0,25,null);
        //dibujar fondo negro desde que termine la imagen hasta el borde de la ventana
        //dibujar los strings con los puntajes, etc

    }

    public void update(double delta,Keyboard keyboard){

    }
}