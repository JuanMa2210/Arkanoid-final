package app;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class ObjetoGrafico{
    public abstract void setPosition(int x,int y);
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract void setImagen(BufferedImage img);
    public abstract int getX();
    public abstract int getY();
    public abstract void update(double delta);
    public abstract void draw(Graphics2D g);
    public abstract int getWidth();
    public abstract int getHeight();
}