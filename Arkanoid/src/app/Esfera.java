package app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import app.Nave;
import app.Escenario;
import javax.imageio.ImageIO;


public class Esfera extends ObjetoGrafico implements Movible {

    protected int DIAMETER=12;
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected Image img_bola = null;
    private int ancho;
    private int alto;
    protected Rectangle2D estructura=new Rectangle();
    //protected Nave nave= new Nave();
    protected boolean parada;
    private Escenario escenario;


    public Esfera(Escenario escenario) {
        this.escenario = escenario;
        this.estructura.setRect(this.x, this.y, DIAMETER, DIAMETER);
        this.x = 241;
        this.y = 540;
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

    public void setDY(int dy) {
        this.dy = dy;
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
        return (int)this.DIAMETER;
    }

    @Override
    public int getHeight() {
        return (int)this.DIAMETER;
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
			this.dy = -1;
			this.y = escenario.nave.getTOPY() - DIAMETER;
		}
        }
        else{
            this.setX(this.getX()+5); 
        }

        

    }

    private boolean collision() {
		return this.getBounds().intersects(escenario.nave.getBounds());
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle(this.getX(),this.getY(), DIAMETER, DIAMETER);
	}
    @Override
    public int velocidad() {
        return 4;
    }

    @Override
    public int aceleracion() {
        return 3; 
    }

    public void rebote(){ 
        for (int i=0;i<escenario.getBloques().size();i++) {  
            Bloque bloque=escenario.getBloques().get(i);         
            if(this.getBounds().intersects(bloque.getBounds())){
                /*System.out.println("X DE LA ESFERA  "+this.getX());
                System.out.println("X DEL FIN DE LA ESFERA  "+(this.getX()+12));
                System.out.println("Y DE LA ESFERA  "+this.getY());
                System.out.println("Y DEL FIN DE LA ESFERA  "+(this.getX()+12));  
                System.out.println("X DEl BLOQUE  "+bloque.getX());
                System.out.println("X FINAL DEl BLOQUE  "+(bloque.getX()+bloque.getWidth()));
                System.out.println("Y DEl BLOQUE  "+bloque.getY());
                System.out.println("Y FINAL DEl BLOQUE  "+(bloque.getY()+bloque.getHeight()));*/
                if((this.getX()+this.DIAMETER)==bloque.getX() || this.getX()==(bloque.getX()+bloque.getWidth())){
                    this.setDX(this.getDX()*-1);
                }else{
                    this.setDY(this.getDY()*-1);
                }
                bloque.restarImpactos();
                break;
            }
        }
    }
}