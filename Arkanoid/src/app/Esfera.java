package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import app.Nave;
import app.Escenario;
import javax.imageio.ImageIO;


public class Esfera extends ObjetoGrafico implements Movible {

    protected double DIAMETER=12;
    protected double x;
    protected double y;
    protected int dx;
    protected int dy;
    protected Image img_bola = null;
    private int ancho;
    private int alto;
    protected Rectangle2D estructura=new Rectangle2D.Double(x,y,ancho,alto);
    protected Nave nave= new Nave();
    protected boolean parada;
    protected Rectangle2D bordes= new Rectangle2D.Double();
    private Escenario escenario;


    // JUANMA, AL FINAL ESTABA BIEN.. LA ESFERA SI O SI TIENE QUE RECIBIR EL
    // ESCENARIO.. Y LOS REBOTES LOS TIENE QUE CALCULAR LA ESFERA
    public Esfera(Escenario escenario) {
        this.escenario = escenario;
        this.estructura.setRect(this.x, this.y, DIAMETER, DIAMETER);
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

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    public void setDireccion(double x, double y, boolean colision) {

    }

    @Override
    public void mover() {
        // y = y + dy;
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
                   System.exit(0);
               }
               else{
                    escenario.nave.setPosition(217, 550);
                    this.setPosition(241,540);
                    this.parada = true;
               }
            }
        if(!this.parada)
        {   
            this.setY(this.getY()+(this.getDY()*this.velocidad()));
            this.setX(this.getX()+(this.getDX()*this.velocidad()));
            if (collision()){
			this.dy = -1;
			this.y = escenario.nave.getTOPY() - DIAMETER;
		}
        }
        else{
            this.setX(this.getX()+5); 
        }

        

    }

    private boolean collision() {
		return escenario.nave.getBounds().intersects(getBounds());
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int)this.getX(),(int)this.getY(), (int) DIAMETER, (int) DIAMETER);
	}
    @Override
    public double velocidad() {
        return 4;
    }

    @Override
    public double aceleracion() {
        return 3.0; 
    }


}