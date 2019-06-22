package app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Nave extends ObjetoGrafico implements Movible {
    protected int dx = 0;
    protected int x;
    protected int y;
    protected Image img_nave=null;
    protected Rectangle2D cuerpo=new Rectangle();
    private int width = 60;
    private int height = 12;
    private Escenario escenario;
    protected boolean bonusActivo=false;

    public Nave(Escenario escenario) {
        this.x = 217;
        this.y = 550;
        this.cuerpo.setRect(this.x, this.y, this.getWidth(), this.getHeight());
        this.escenario=escenario;
        try {
            // aca va a ir la nave que elija el usuario, ahora solo tenemos esta
            this.img_nave = ImageIO.read(getClass().getResource("imagenes/Vaus1.png"));
            this.img_nave = img_nave.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error al cargar imagenes Nave");
        }
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }


    public void setDX(int dx) {
        this.dx = dx;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    public int getDX() {
        return this.dx;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void update(double delta) {
        this.cuerpo.setRect(this.x, this.y, this.getWidth(), this.getHeight());
    }

    public void setImagen(BufferedImage img) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(img_nave.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH),this.getX(),this.getY(),null);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void mover(){
        this.setX(getX()+(this.getDX()*this.velocidad()));
        this.update(0);
    }

    @Override
    public int velocidad() {
        return 5;
    }

    @Override
    public int aceleracion() {
        return 3;
    }

    public void setWidth(int ancho){
        this.width=ancho;
    }

	public Rectangle2D getBounds() {
        return this.cuerpo;
    }


	public int getTOPY() {
		return this.y - this.getHeight();
    }
    
    public void setActivo(boolean verdad){
        this.bonusActivo=verdad;
    }
    public boolean isActivo(){
        return this.bonusActivo;
    }
}


