package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Nave extends ObjetoGrafico implements Movible {
    protected int dx = 0;
    protected Double x;
    protected Double y;
    protected Image img_nave=null;
    protected Rectangle2D cuerpo=new Rectangle();
    private int width = 60;
    private int height = 12;

    public Nave() {
        this.x = 217.0;
        this.y = 550.0;
        try {
            // aca va a ir la nave que elija el usuario, ahora solo tenemos esta
            this.img_nave = ImageIO.read(getClass().getResource("imagenes/Vaus3.png"));
            this.img_nave = img_nave.getScaledInstance(60, 12, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error al cargar imagenes Nave");
        }
    }

    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    public void setDX(int dx) {
        this.dx = dx;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return this.x;
    }

    public int getDX() {
        return this.dx;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void update(double delta) {
        this.cuerpo.setRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void setImagen(BufferedImage img) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.img_nave, (int) this.getX(), (int) this.getY(), null);
        this.cuerpo.setRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
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
        this.setX(this.getX()+(this.getDX()*this.velocidad()));
    }

    @Override
    public Double velocidad() {
        return 5.0;
    }

    @Override
    public int aceleracion() {
        return 3;
    }


	public Rectangle getBounds() {
            return (Rectangle) this.cuerpo;
        }


	public Double getTOPY() {
		return this.y - this.getHeight();
	}
}


