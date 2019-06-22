package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import app.Escenario;
import javax.imageio.ImageIO;
import javax.lang.model.util.ElementScanner6;


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
    //protected Nave nave= new Nave();
    protected boolean parada;
    private Escenario escenario;
    private boolean EsqDerNave;
    private boolean EsqIzqNave;
    


    public Esfera(Escenario escenario) {
        this.escenario = escenario;
        this.x = 241.0;
        this.y = 540.0;
        this.dx = 1;
        this.dy = -1;
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
            if(this.getX()+this.getDX() > 474- this.DIAMETER)//colision derecha escenario
              this.setDX(-1);
            if(this.getX()+this.getDX() < 8 + this.DIAMETER)//colision izq escenario
               this.setDX(1);
            if(this.getY()+this.getDY() <30 + this.DIAMETER)//colision sup escenario
               this.setDY(1);
            if((this.getY()+this.getDY() > 590 - this.DIAMETER)){//&& colion nave)//colision inf escenario
               escenario.cantidad_vidas --;
               if(escenario.cantidad_vidas == 0){
                   System.out.println("GAME OVER");
                   //mostrar pantalla de game over, junto a un pulsar para volver.
                   //System.exit(0);
               }
               else{
                    escenario.nave.setPosition(217, 550);
                    escenario.nave.update(0);
                    this.setPosition(241,540);
                    this.parada = true;
               }
            }
        if(!this.parada)
        {   
            this.setY(this.getY()+(this.getDY()*this.velocidad()));
            this.setX(this.getX()+(this.getDX()*this.velocidad()));
            if (collision()){
                if(this.EsqIzqNave){
                    this.setVelocidad(this.velocidad*1.5);
                    this.setDX(-1);
                    System.out.println("aumento de velocidad");
                    this.EsqIzqNave=false;
                }
                if (this.EsqDerNave) {
                    this.setVelocidad(this.velocidad*1.5);
                    this.setDX(1);
                    System.out.println("aumento de velocidad");
                    this.EsqDerNave=false;
                }
			      this.dy = -1;
			      this.y = escenario.nave.getTOPY() - DIAMETER;
		}
        }
        else{
            this.setX(this.getX()+5); 
        }

        

    }

    private boolean collision() {
        if(escenario.nave.getBounds().intersects(getBounds())){
            if(((this.estructura.getMaxX() <= escenario.nave.cuerpo.getX()+5)
                     &&(this.estructura.getMaxY() >= escenario.nave.getY()))){
                        this.EsqIzqNave = true;
            }
            else if((this.estructura.getX() >= escenario.nave.cuerpo.getMaxX()-5)
                           &&((this.estructura.getMaxY() >= escenario.nave.getY())
                            &&(this.estructura.getMaxY() <= escenario.nave.getHeight()/2))){
                            this.EsqDerNave = true;
                 }             
        }
        return escenario.nave.getBounds().intersects(getBounds());
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(this.getX(),this.getY(), DIAMETER, DIAMETER);
    }
    
    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public Double velocidad() {
        return this.velocidad;
    }

    @Override
    public int aceleracion() {
        return 3; 
    }

    public void rebote(){ 
        for (int i=0;i<escenario.getBloques().size();i++) {  
            Bloque bloque=escenario.bloques.get(i);    
            if(this.getBounds().intersects(bloque.getBounds())){  //Divido en colisiones por arriba y abajo, y por otro lado laterales
                System.out.println("bloque x:"+bloque.getX()+"bloque y:"+bloque.getY()+"ancho:"+bloque.getWidth()+"Alto:"+bloque.getHeight());
                System.out.println("esfera en x:"+this.getX()+" esfera en y:"+this.getY()); 
                if((this.getY()<=bloque.cuerpo.getMaxY())||((this.getY()+this.DIAMETER+this.velocidad())<=bloque.cuerpo.getY())
                        &&(this.getX()>bloque.cuerpo.getMinX()&&this.getX()<bloque.cuerpo.getMaxX())){
                    this.setDY(this.getDY()*-1);
                }else if(((this.getX()+this.DIAMETER+this.velocidad())<=bloque.cuerpo.getX())||(this.getX()<=bloque.cuerpo.getMaxX())
                            &&(this.getY()>bloque.cuerpo.getMinY()&&this.getY()<bloque.cuerpo.getMaxY())){
                    this.setDX(this.getDX()*-1);
                }
                bloque.restarImpactos();
                    
                break;
            }
        }
    }
}