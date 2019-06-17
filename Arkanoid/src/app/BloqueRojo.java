package app;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BloqueRojo extends Bloque {
    public BloqueRojo(double x, double y) {
        this.x = x;
        this.y = y;
        this.puntaje = 90;
        this.cuerpo = new Rectangle();
        this.cuerpo.setRect(this.x, this.y, 45, 20);
        this.impactos = 1;
        try {
            this.img=ImageIO.read(getClass().getResource("imagenes/BloqRojo.png"));
            this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR IMAGEN BLOQUE AMARILLO");
        }
        //super.rebote(esfera);
    }

    @Override
    public void rebote(Esfera esfera) {

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
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.img,(int)this.x,(int)this.y,null);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
    public Rectangle getBounds() {
        return new Rectangle((int)this.getX(), (int)this.getY(), this.ancho, this.alto);
    }
}