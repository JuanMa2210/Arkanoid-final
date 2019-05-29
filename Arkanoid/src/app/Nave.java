package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Nave extends ObjetoGrafico implements Movible {
    protected int dx = 0;
    protected double x;
    protected double y;
    protected Image img_nave=null;
    protected Rectangle2D cuerpo=new Rectangle();
    private Escenario juego;
    private KeyEvent e;

    public Nave(Escenario juego){
        this.cuerpo.setRect(this.x, this.y, this.getWidth(), 12);
        this.x=217.0;
        this.y=550.0;
        try {
            //aca va a ir la nave que elija el usuario, ahora solo tenemos esta
            this.img_nave=ImageIO.read(getClass().getResource("imagenes/naveNormal.png"));
            this.img_nave=img_nave.getScaledInstance(60,12, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error al cargar imagenes Nave");
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

    public void setDX(int dx) {
        this.dx=dx;
    }

    @Override
    public void setY(double y) {
        this.y=y;
    }

    @Override
    public double getX() {
        return this.x;
    }

    public int getDX() {
        return dx;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void update(double delta) {
        
    }

    public void setImagen(BufferedImage img){
        
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.img_nave,(int)this.getX(),(int)this.getY(),null);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
    
    public void keyPress(){
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            this.dx = -1;
            this.mover();
        }
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.dx = 1;
            this.mover();
        }
    }   
    
    @Override
    public void mover(){ 
        this.x = x + dx;
    }

    @Override
    public void velocidad() {

    }

    @Override
    public void aceleracion() {

    }


	public Rectangle2D getBounds() {
            return new Rectangle(this.getWidth(),this.getHeight());
        }


	public double getTOPY() {
		return this.x - this.getWidth();
	}


	public Object getStruct() {
		return null;
	}
	}

