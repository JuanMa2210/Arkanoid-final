package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;

public class Escenario{
    protected BufferedImage img_fondoAzul = null;
    protected BufferedImage img_fondoVerde = null;
    protected BufferedImage img_fondoRojo = null;
    protected BufferedImage fondo_negro = null;
    protected BufferedImage img_nave = null;
    protected BufferedImage img_bola = null;
    protected int cantidad_vidas=3; //limitar cantidad de vidas a 5
    protected Nave nave = new Nave(null);
    protected Esfera esfera = new Esfera(null);  
    protected Vector<Bloque> bloques=new Vector<Bloque>();
    protected ArrayList<Esfera> bolas = new ArrayList<Esfera>();
    protected Escenario juego =new Escenario();

    protected Rectangle2D limites=new Rectangle(0, 0, img_fondoAzul.getWidth(),600);//creo que aca es menos
    private int nivelActual=1;
    private boolean comenzo;
    private int cont;
    protected static boolean nuevoNivel=true;
    protected Thread t;
    private KeyEvent e;


    
    public Escenario() {
        this.cargar();
        this.inicio();
    }

    // ESTE METODO CARGA TODAS LAS IMAGENES NECESARIAS PARA EL ESCENARIO
    private void cargar() {
        try {
            img_fondoAzul = ImageIO.read(getClass().getResource("imagenes/FondoAzul.jpg"));
            img_fondoVerde = ImageIO.read(getClass().getResource("imagenes/FondoVerde.png"));
            img_fondoRojo = ImageIO.read(getClass().getResource("imagenes/FondoRojo.jpg"));
            fondo_negro = ImageIO.read(getClass().getResource("imagenes/negro_solido.png"));
            img_nave = ImageIO.read(getClass().getResource("imagenes/naveNormal.png"));
            img_bola = ImageIO.read(getClass().getResource("imagenes/bola.png"));
        } catch (Exception e) {
            System.out.println("Error al cargas las imagenes del escenario");
        }
    }

    // incializamos todo en estas variables.
    public void inicio() {
        this.nave = new Nave(this.juego);
        this.bolas = new ArrayList<Esfera>();
        Esfera esfera = new Esfera(this.juego);
        esfera.parada = true;
        bolas.add(esfera);

        cargarNivel(nivelActual);
    }

    public void corriendo(){
        // actualizo las bolas en el vector
        for (int i=0; i< bolas.size(); i++){
            Esfera esfera = bolas.get(i);
            if (esfera.y >= nave.getTOPY()){
                bolas.remove(i);
                // ahora cuando no haya mas bolas perderemos una vida
                if (bolas.size()==0){
                    this.cantidad_vidas--;
                    Esfera esferaNew =new Esfera(this);
                    esferaNew.parada=true;
                    bolas.add(esferaNew);
                }
            }
            if (this.cantidad_vidas == 0){
                // dibujar "GAME OVER, vuelve a intentarlo"
            
            this.finJuego();//crear funcion
            }
        
            if (this.comenzo==true){
                if (this.cont==1){
                        //crono.start(); //definir tanto crono como el start
                        System.out.println("el juego comenzo");
                        this.cont--;
                   }
                if(esfera.parada){
                   // esfera.stop();
                   System.out.println("la pelota se detuvo");
                }else{
                    //esfera.start();
                    System.out.println("la pelota mueve");
                }
            }
        }      
    //nave.start();
    if (nuevoNivel){
        this.finNivel();// definir funcion
    }
    }

    private void finNivel() {

    }

    private void finJuego() {
        //cargar puntaje en ranking
        // deberia volver al menu y abrirse la ventana ranking

        this.borrarNivel();
        //colocar en el nivel 1
        //poner para iniciar un nuevo juego si lo desea
        this.cantidad_vidas=3;
        this.comenzo=false;
        //setear puntajes a cero
        // setear el tiempo a cero
        this.inicio();
    }

    private void borrarNivel() {
        // borrar todos los ladrillos
        //borrar los bonuses
        bolas.clear();
        this.nave=null;
    }

    public void draw(Graphics2D g) {
        this.dibujoInicial(g);
    }

    public void dibujoInicial(Graphics2D g){

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
        //nave.setImagen(img_nave);
        nave.draw(g);
        esfera.setImagen(img_bola);
        esfera.draw(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fill(new Rectangle2D.Double(0,0,20,20));
        //ACA DIBUJO TODOS LOS BLOQUES QUE TENGA CARGADO
        for (Bloque B : bloques) {
            B.draw(g);
        }
        
    }

    public void update(){
      // esfera.mover(getBounds(), colision(this.limites),colision(nave.cuerpo));
      // nave.mover();

      if (e.getKeyCode() == KeyEvent.VK_LEFT){
        nave.dx = -1;
        System.out.println("puto");
        nave.mover();
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        nave.dx = 1;
        nave.mover();
    }

    }

    private Object colision(Rectangle2D limites) {
        return esfera.getStruct().intersects(nave.cuerpo);
    }
    

  /*  public void colisionBola(Rectangle2D limites){
        if(esfera.getX()+esfera.getDX() > limites.getWidth()- esfera.DIAMETER)
        esfera.setDX(-1);
        if(esfera.getY()+esfera.getDY() < 0)
        esfera.setDY(-1);
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
    }
*/
    
    //NECESITARIA RECIBIR EL NIVEL QUE TENGO QUE CARGAR
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



    /*try {
        RandomAccessFile raf = new RandomAccessFile("demoraf.txt", "rw");
       
        raf.writeBytes("Hola Mundo!"); //Escribir algo
     
        raf.seek(0);// Se posiciona el puntero al inicio del archivo

        // Leo e imprimo  
        System.out.println("" + raf.readLine());

        // Se posiciona el puntero al inicio del archivo
        raf.seek(0);

        //Escribo algo nuevo
        raf.writeBytes("Hace mucho tiempo, en una galaxia muy, muy lejana...\n Episodio IV \n Una Nueva Esperanza");

        // Se posiciona el puntero al inicio del archivo
        raf.seek(0);

        // Leo e imprimo 
        System.out.println("" + raf.readLine());
        
        raf.close();
     } catch (IOException ex) {
        ex.printStackTrace();
     }*/



}