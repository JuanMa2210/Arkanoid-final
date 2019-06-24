package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import app.Escenario;
import javax.imageio.ImageIO;
import javax.lang.model.util.ElementScanner6;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
//para sonido
import java.awt.Color;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Esfera extends ObjetoGrafico implements Movible {

    protected int DIAMETER=12;
    protected double x;
    protected double y;
    protected int dx;
    protected int dy;
    private double velocidad=3.0;
    protected Image img_bola = null;
    private int ancho;
    private int alto;
    protected Rectangle2D estructura=new Rectangle();
    protected boolean parada;
    private Escenario escenario;
    private boolean EsqDerNave;
    private boolean EsqIzqNave;
    private boolean activa;
    private double aceleracion = 1;
    private int cantcolisiones=0;

    public Esfera(Escenario escenario) {
        this.escenario = escenario;
        this.x = 241.0;
        this.y = 540.0;
        this.dx = 1;
        this.dy = -1;
        activa=true;
    }

    public void cargarElementos() {
        try {
            this.img_bola = ImageIO.read(getClass().getResource("imagenes/bola.png"));
        } catch (Exception e) {
            System.out.println("Error al cargar imagenes");
        }
    }

    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isActiva(){
        return this.activa;
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

    public void setDY(int dy) {
        this.dy = dy;
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

    public int getDY() {
        return this.dy;
    }

    @Override
    public void update(double delta) {

    }

    public void setImagen(BufferedImage img) {
        this.img_bola = img.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(this.img_bola, (int) this.getX(), (int) this.getY(), null);
        this.estructura.setRect(this.getX(), this.getY(), DIAMETER, DIAMETER);

    }

    @Override
    public int getWidth() {
        return this.DIAMETER;
    }

    @Override
    public int getHeight() {
        return this.DIAMETER;
    }

    @Override
    public void mover() {
        if(!this.parada)
        {   
            this.setY(this.getY()+(this.getDY()*this.velocidad()*this.aceleracion()));
            this.setX(this.getX()+(this.getDX()*this.velocidad()*this.aceleracion()));
            if(this.getX()+this.getDX() > 474- this.DIAMETER)//colision derecha escenario
                this.setDX(-1);
            if(this.getX()+this.getDX() < 8 + this.DIAMETER)//colision izq escenario
                this.setDX(1);
            if(this.getY()+this.getDY() <30 + this.DIAMETER)//colision sup escenario
                this.setDY(1);
            if((this.getY()+this.getDY() > 590 - this.DIAMETER)){//&& colion nave)//colision inf escenario
                this.activa=false;
                try {
                    Clip sonido = AudioSystem.getClip();
                    File a = new File("C:/Users/Julian/Documents/MEGAsync/Kraken/poo/Arkanoid/bin/app/Sonidos/VidaPerdida.wav");
                    sonido.open(AudioSystem.getAudioInputStream(a));
                    sonido.start();
                 } catch (Exception tipoError) {
                    System.out.println("" + tipoError);
                 }
            if(escenario.cantidad_vidas == 0){
                  System.out.println("GAME OVER");
                  //System.exit(0);
            }
            else{
                if(escenario.getBolas().isEmpty()){
                    escenario.cantidad_vidas --;
                    escenario.nave.setPosition(217, 550);
                    escenario.nave.update(0);
                    this.setPosition(241,540);
                    this.parada = true;
                    this.setVelocidad(3.0);
                    this.cantcolisiones=0;

                }
            }
          }
            if (collision()){
                if(escenario.nave.getTipoBonusActivo()==1){
                        this.parada=true;
                        this.setY(escenario.nave.getY()-12);
                    }
                this.cantcolisiones++;
                System.out.println(this.cantcolisiones);
                if(this.cantcolisiones==5){
                this.incrementarAceleracion(); 
                this.cantcolisiones=0;
                }
                if(this.EsqIzqNave){
                    this.setVelocidad(this.velocidad+0.07);
                    this.setDX(-1);
                    this.EsqIzqNave=false;
                }
                if (this.EsqDerNave) {
                    this.setVelocidad(this.velocidad+0.07);
                    this.setDX(1);
                    this.EsqDerNave=false;
                }
			      this.dy = -1;
                  this.y = escenario.nave.getTOPY() - DIAMETER;
                  try {
                    Clip sonido = AudioSystem.getClip();
                    File a = new File("C:/Users/Julian/Documents/MEGAsync/Kraken/poo/Arkanoid/bin/app/Sonidos/Rebotes.wav");
                    sonido.open(AudioSystem.getAudioInputStream(a));
                    sonido.start();
                 } catch (Exception tipoError) {
                    System.out.println("" + tipoError);
                 } 
            }
        }   
    }

    private boolean collision() {
        if(escenario.nave.getBounds().intersects(getBounds())){
            if(((this.estructura.getMaxX() <= escenario.nave.cuerpo.getX()+10)
                     &&(this.estructura.getMaxY() <= escenario.nave.cuerpo.getCenterY()-7))){
                        this.EsqIzqNave = true;
            }
            else if((this.estructura.getX() >= escenario.nave.cuerpo.getMaxX()-10)
                           &&((this.estructura.getMaxY() <= escenario.nave.cuerpo.getCenterY()-7))){
                            this.EsqDerNave = true;
                 }             
        }
        return escenario.nave.getBounds().intersects(getBounds());
    }
    
    public Rectangle2D getBounds() {
        return this.estructura;
    }
    
    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public double velocidad() {
        return this.velocidad;
    }

    public void incrementarAceleracion(){
        new Timer().schedule(new TimerTask() {
           @Override
           public void run() {
            for (int i = 0; i < escenario.getBolas().size(); i++)
            {
                escenario.getBolas().get(i).aceleracion+=0.158;
                System.out.println("aceleracion aumentada");
            } 
                
           }
       },5000);
    }

    @Override
    public double aceleracion() {
        return this.aceleracion; 
    }

    public void rebote(){
        for (Bloque bloque : escenario.getBloques()) {
            if(this.getBounds().intersects(bloque.getBounds())){
                if((this.getY()+12>=bloque.getY() && this.getY()+12<=bloque.getY()+20) && (this.getX()>bloque.getX() || this.getX()+12<bloque.getX()+45)){
                    this.dy=this.dy*-1;
                    System.out.println("ENTRO AL PRIMERO");
                }else{
                    if((this.getY()<=bloque.getY()+20 && this.getY()>=bloque.getY()) && (this.getX()>bloque.getX() || this.getX()+12<bloque.getX()+45)){
                        this.dy=this.dy*-1;
                        System.out.println("ENTTRO AL SEGUNDO");
                    }else{
                        this.dx=this.dx*-1;
                        System.out.println("ENTTRO AL TERCERO");
                    }
                }
                bloque.restarImpactos();
                try {
                    Clip sonido = AudioSystem.getClip();
                    File a = new File("C:/Users/Julian/Documents/MEGAsync/Kraken/poo/Arkanoid/bin/app/Sonidos/ReboteBloque.wav");
                    sonido.open(AudioSystem.getAudioInputStream(a));
                    sonido.start();
                   
                }catch (Exception tipoError) {
                    System.out.println("" + tipoError);
                }
                break;
            }
        }
    }
}
