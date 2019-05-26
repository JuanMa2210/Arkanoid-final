package app;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class ObjetoGrafico{
    public abstract void setPosition(double x,double y);
    public abstract void setX(double x);
    public abstract void setY(double y);
    public abstract void setImagen(BufferedImage img);
    public abstract double getX();
    public abstract double getY();
    public abstract void update(double delta);
    public abstract void draw(Graphics2D g);
    public abstract int getWidth();
    public abstract int getHeight();
}