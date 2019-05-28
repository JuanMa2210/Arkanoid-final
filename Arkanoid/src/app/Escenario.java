package app;

/*
* agregar metodo eliminarVidas();
* agregar metodo sumarVidas();
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;

public class Escenario {
    protected BufferedImage img_fondoAzul = null;
    protected BufferedImage img_fondoVerde = null;
    protected BufferedImage img_fondoRojo = null;
    protected BufferedImage fondo_negro = null;
    protected BufferedImage img_nave = null;
    protected BufferedImage img_bola = null;
    protected int cantidad_vidas=3; //limitar cantidad de vidas a 5
    protected Nave nave=new Nave(null);
    protected Esfera esfera = new Esfera();  
    protected Vector<Bloque> bloques=new Vector<Bloque>();
    //protected Rectangle2D limites=new Rectangle(0, 0, img_fondoAzul.getWidth(),600);//creo que aca es menos
    //###############JUANMAAAAAA#################
    //EL ANCHO DE TODAS LAS IMAGENES DE FONDO ES IGUAL, PORQUE LAS REDIMENSIONAMOS, ES DE 495
    
    public Escenario(){
        this.cargar();
        this.cargarNivel(1);
    } 

    //ESTE METODO CARGA TODAS LAS IMAGENES NECESARIAS PARA EL ESCENARIO
    private void cargar(){
        try {
            img_fondoAzul= ImageIO.read(getClass().getResource("imagenes/FondoAzul.jpg"));
            img_fondoVerde= ImageIO.read(getClass().getResource("imagenes/FondoVerde.png"));
            img_fondoRojo= ImageIO.read(getClass().getResource("imagenes/FondoRojo.jpg"));
            fondo_negro=ImageIO.read(getClass().getResource("imagenes/negro_solido.png"));
            img_nave=ImageIO.read(getClass().getResource("imagenes/naveNormal.png"));
            img_bola=ImageIO.read(getClass().getResource("imagenes/bola.png"));
        } catch (Exception e) {
            System.out.println("Error al cargas las imagenes del escenario");
        }
    }


    public void draw(Graphics2D g){

        int limiteEscenario=img_fondoAzul.getWidth();

        g.drawRect(18, 43, limiteEscenario-38, img_fondoAzul.getHeight()-42);
        g.drawImage(img_fondoAzul,0,25,null);
        g.drawImage(fondo_negro, limiteEscenario, 0,null);
        g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("PUNTAJE", limiteEscenario+50, 60);
        g.drawString(" MAXIMO", limiteEscenario+50, 95);
        g.setColor(Color.white);
        g.drawString(" 0000000", limiteEscenario+50, 125);  //VARIABLE DEL PUNTAJE MAXIMO, VA A SER LA POS 0 DEL RANK
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("PUNTAJE", limiteEscenario+50, 180);
        g.drawString(" ACTUAL", limiteEscenario+50, 215);
        g.setColor(Color.white);
        g.drawString(" 0000000", limiteEscenario+50, 245);  //VARIABLE DEL PUNTAJE ACTUAL
        //DIBUJAR LAS NAVES DEPENDIENDO DE LAS VIDAS QUE TENGA
        for(int i=0;i<cantidad_vidas;i++){
            g.drawImage(img_nave, limiteEscenario+50+(45*i), 300, null);
        }
        g.setColor(Color.RED);
        g.drawString("NIVEL:", limiteEscenario+150, 550);
        g.setColor(Color.white);
        g.drawString("1", limiteEscenario+250, 550);    //ACA VA EL NIVEL
        nave.draw(g);
        esfera.setImagen(img_bola);
        esfera.draw(g);
        //ACA DIBUJO TODOS LOS BLOQUES QUE TENGA CARGADO SI ES QUE NO SE DESTRUYERON
        for (Bloque B : bloques) {
            if(B.getImpactos()==0){
                bloques.remove(B);
            }else{
                B.draw(g);
            }
        }
        
    }

    public void update(double delta,Keyboard keyboard){
        /*if (keyboard.isKeyPressed(KeyEvent.VK_LEFT) && (this.colisionNave())){
            nave.mover();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && (this.colisionNave())){
            nave.mover();
        }
        esfera.mover();*/
    }
    

    /*public void colisionBola(){
        if(esfera.getX()+esfera.getDX() > limites.getWidth()- esfera.DIAMETER)
        esfera.setDX(-1);
        if(esfera.getY()+esfera.getDY() < 0)
        esfera.setDY(1);
        if (esfera.colision()){
            esfera.setDY(-1);
            esfera.y = nave.getTOPY() - esfera.DIAMETER;
        }
        esfera.mover();
    }

    public boolean colisionNave(){
        if (nave.x + nave.dx > 50 && nave.x + nave.dx < (limites.getWidth()-20) - nave.getWidth() )
            return true;
        else return false;
    }*/

    public void cargarNivel(int nivelActual){
        try {
            RandomAccessFile nivel1 = new RandomAccessFile("Nivel1.txt", "r");
            int y=80;
            int x=25;
            int lineas=0;
            while(nivel1.readLine()!=null){
                lineas++;
            }
            nivel1.seek(0);
            for(int j=0;j<lineas;j++){
                String fila=nivel1.readLine();
                String[] caracteres=fila.split(",");
                for (String c : caracteres) {
                    switch (c){
                        case "A": bloques.add(new BloqueAmarillo(x,y));break;
                        case "Z": bloques.add(new BloqueAzul(x,y));break;
                        case "B": bloques.add(new BloqueBlanco(x,y));break;
                        case "C": bloques.add(new BloqueCeleste(x,y));break;
                        case "D": bloques.add(new BloqueDorado(x,y));break;
                        case "N": bloques.add(new BloqueNaranja(x,y));break;
                        case "P": bloques.add(new BloquePlateado(x,y,nivelActual));break;
                        case "R": bloques.add(new BloqueRojo(x,y));break;
                        case "S": bloques.add(new BloqueRosa(x,y));break;
                        case "V": bloques.add(new BloqueVerde(x,y));break;
                        case "X": break;
                    }
                    x=x+40;
                }
                x=25;
                y = y + 25;
            }
            nivel1.close();
        } catch (Exception e) {
            System.out.println("Error al cargar los niveles");
        }
    }

}