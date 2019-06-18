package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bloque extends ObjetoGrafico {
    protected int x;
    protected int y;
    protected Rectangle2D cuerpo;
    protected int ancho=45;
    protected int alto=20;
    protected int impactos;
    protected int puntaje;
    protected boolean tieneBonus;
    protected Image img;

    public Bloque(Escenario escenario,String color,int x,int y){
        switch(color){
            case "A":
                this.puntaje=120;this.impactos=1;
                try {
                    this.img=ImageIO.read(getClass().getResource("imagenes/BloqAmarillo.png"));
                    this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                } catch (Exception e) {
                    System.out.println("ERROR AL CARGAR IMAGEN BLOQUE AMARILLO");
                }break;
            case "Z":
                this.puntaje=100;this.impactos=1;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqAzul.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Azul");
                    }break;
            case "B":
                this.puntaje=50;this.impactos=1;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqBlanco.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Blanco");
                    }break;
            case "C":
                this.puntaje=70;this.impactos=1;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqCeleste.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Celeste");
                    }break;
            case "D":
                this.puntaje=0;this.impactos=10000000;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqDorado.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE AMARILLO");
                    }break;
            case "N":
                this.puntaje=60;this.impactos=1;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqNaranja.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Naranja");
                    }break;
            case "P":
                this.puntaje=50*escenario.getNivel();this.impactos=2;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqPlata.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Plateado");
                    }break;
            case "R":
                this.puntaje=90;this.impactos=1;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqRojo.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Rojo");
                    }break;
            case "S":
                this.puntaje=110;this.impactos=1;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqRosa.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Rosa");
                    }break;
            case "V":
                this.puntaje=80;this.impactos=1;
                    try {
                        this.img=ImageIO.read(getClass().getResource("imagenes/BloqVerde.png"));
                        this.img=img.getScaledInstance(45,20, img.SCALE_SMOOTH);
                    } catch (Exception e) {
                        System.out.println("ERROR AL CARGAR IMAGEN BLOQUE Verde");
                    }break;
        }
        this.x=x;
        this.y=y;
        this.cuerpo=new Rectangle();
        this.cuerpo.setRect(this.x,this.y,45,20);
        this.asignarBonus();

    }

    public void restarImpactos(){
        this.impactos--;    //cuando llegue a cero, el bloque va a estar destruido
    }

    public  void asignarBonus(){   //PUEDE ASIGNARLE UN BONUS AL BLOQUE O NO.
        
        if((int)Math.floor(Math.random()*4+1)==1){  //GENERA UN NUMERO DEL 1 AL 4 Y SOLO ASIGNA BONUS SI ES 1
            int azar=(int)Math.floor(Math.random()*6+1);
            switch(azar){
                case 1: break;  //CATCH
                case 2: break;  //DUPLICATE
                case 3: break;  //ENLARGE
                case 4: break;  //EXTRA_PLAYER
                case 5: break;  //SLOW
                case 6: break;  //WARP
            }
            this.tieneBonus=true;
        }
    }

    public int getPuntaje(){
        return this.puntaje;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public void setImagen(BufferedImage img) {

    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.img, this.x, this.y, null);
    }

    @Override
    public int getWidth() {
        return this.ancho;
    }

    @Override
    public int getHeight() {
        return this.alto;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(this.getX(),this.getY(), this.ancho, this.alto);
    }
    
    
}