package app;

import com.entropyinteractive.*;


import java.awt.*;
import java.awt.event.*; //eventos

import java.awt.image.*;  //imagenes
import javax.imageio.*; //imagenes

import java.awt.Graphics2D;

import java.awt.geom.*; //Point2d
import java.util.LinkedList;

public class Menu extends JGame {
    

    protected BufferedImage img_fondo = null;
    protected BufferedImage img_titulo = null;
    protected BufferedImage img_nave=null;
    protected Puntero nave=new Puntero();
    protected Escenario escenario=new Escenario();
    protected Ranking ranking=new Ranking();
    protected boolean isEscenario=false;
    protected boolean isRanking=false;
    
   

    public Menu(){
        super("Arkanoid", 800, 600);
    }

    @Override
    public void gameDraw(Graphics2D g) {

        dibujarMenu(g);
        if(isEscenario){
            escenario.draw(g);
        }
        if(isRanking){
            ranking.draw(g);
        }
    }


    private void dibujarMenu(Graphics2D g){
        g.drawImage(img_fondo,0,0,null);
        g.drawImage(img_titulo,150,20,null);
        g.setColor(Color.white);
        g.setFont(new Font("Courier", Font.BOLD, 30));
        g.drawString("Jugar", 355, 300);
        g.drawString("Ranking", 355, 380);
        g.drawString("Salir", 355, 460);
        nave.draw(g);
    }

    @Override
    public void gameShutdown() {
        Keyboard keyboard = this.getKeyboard();
       
        // Esc fin del juego
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }
    }
    

    @Override
    public void gameStartup() {
        try{
            img_fondo= ImageIO.read(getClass().getResource("imagenes/fondoMenu.jpg"));
            img_titulo=ImageIO.read(getClass().getResource("imagenes/arkanoid_logo.png"));
            img_nave=ImageIO.read(getClass().getResource("imagenes/naveNormal.png"));
            nave.setImagen(img_nave);
            nave.setPosicion(308, 290);
            
        }
        catch(Exception e){
            System.out.println("Error al cargar las imagenes");
        }

    }

    @Override
    public void gameUpdate(double delta) {
        Keyboard keyboard = this.getKeyboard();
        if (keyboard.isKeyPressed(KeyEvent.VK_UP) && nave.getY()>290){
            nave.setY(nave.getY()-80);
        }
        
        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)&& nave.getY()<450){
            nave.setY(nave.getY()+80);
        }
        
        if(keyboard.isKeyPressed(KeyEvent.VK_ENTER)){
            switch((int)nave.getY()){
                case 290: isEscenario=true;break;
                case 370: isRanking=true;ranking.activar();break;
                case 450: System.exit(0);break;
            }
        }
        
        if(isRanking && ranking.isActive()){
            ranking.update(delta,keyboard);
            isRanking=ranking.isActive();
        }
        if(isEscenario){
            escenario.update(delta,keyboard);
        }
        if(isRanking){
            //ranking.update(delta,keyboard);
        }
    }
    

    private class Puntero{

        BufferedImage imagen=null;
        private Point2D.Double posicion  = new Point2D.Double();
    
        public Puntero(){
    
        }
        public void setImagen(BufferedImage img){
            this.imagen=img;
    
        }

        public void setPosicion(double x, double y){
            posicion.setLocation(x, y);
        }

        public void setY(double y){
            posicion.y=y;
        }
    
        public double getY(){
            return posicion.getY(); 
        }
        
        public void draw(Graphics2D g){
            g.drawImage(imagen,(int)posicion.getX(),(int)posicion.getY(),null);
        }
    }



}