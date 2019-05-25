package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;

public class Escenario {
    protected BufferedImage img_fondoAzul = null;
    protected BufferedImage img_fondoVerde = null;
    protected BufferedImage img_fondoRojo = null;
    protected BufferedImage fondo_negro = null;
    protected BufferedImage img_nave = null;
    protected int cantidad_vidas=3;
    protected Nave nave=new Nave();
    //protected Rectangle2D tablero=new Rectangle(0, 0, img_fondoAzul.getWidth(),600);//creo que aca es menos
    
    public Escenario(){
        this.cargar();
    } 

    //ESTE METODO CARGA TODAS LAS IMAGENES NECESARIAS PARA EL ESCENARIO
    private void cargar(){
        try {
            img_fondoAzul= ImageIO.read(getClass().getResource("imagenes/FondoAzul.png"));
            img_fondoVerde= ImageIO.read(getClass().getResource("imagenes/FondoAzul.png"));
            img_fondoRojo= ImageIO.read(getClass().getResource("imagenes/FondoAzul.png"));
            fondo_negro=ImageIO.read(getClass().getResource("imagenes/negro_solido.png"));
            img_nave=ImageIO.read(getClass().getResource("imagenes/naveNormal.png"));
        } catch (Exception e) {
            System.out.println("Error al cargas las imagenes del escenario");
        }
    }

    public void draw(Graphics2D g){
        this.dibujoInicial(g);
    }

    public void dibujoInicial(Graphics2D g){

        int limiteEscenario=img_fondoAzul.getWidth();

        g.drawRect(18, 43, limiteEscenario-38, img_fondoAzul.getHeight()-42);
        g.drawImage(img_fondoAzul,0,25,null);
        g.drawImage(fondo_negro, limiteEscenario, 0,null);
        g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("PUNTAJE", limiteEscenario+50, 60);
        g.drawString(" MAXIMO", limiteEscenario+50, 95);
        g.setColor(Color.white);
        g.drawString(" 0000000", limiteEscenario+50, 125);  //VARIABLE DEL PUNTAJE MAXIMO, VA A SER LA POS 0 DEL RANK
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("PUNTAJE", limiteEscenario+50, 180);
        g.drawString(" ACTUAL", limiteEscenario+50, 215);
        g.setColor(Color.white);
        g.drawString(" 0000000", limiteEscenario+50, 245);  //VARIABLE DEL PUNTAJE ACTUAL
        //DIBUJAR LAS NAVES DEPENDIENDO DE LAS VIDAS QUE TENGA
        for(int i=0;i<cantidad_vidas;i++){
            g.drawImage(img_nave, limiteEscenario+50+(45*i), 300, null);
        }
        g.setColor(Color.RED);
        g.drawString("NIVEL:", limiteEscenario+150, 550);
        g.setColor(Color.white);
        g.drawString("1", limiteEscenario+250, 550);    //ACA VA EL NIVEL
        nave.setImagen(img_nave);
        nave.draw(g);
    }

    public void update(double delta,Keyboard keyboard){
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT) && nave.getX()>10){
            nave.setX(nave.getX()-5);
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && nave.getX()<img_fondoAzul.getWidth()-60){
            nave.setX(nave.getX()+5);
        }
    }
}