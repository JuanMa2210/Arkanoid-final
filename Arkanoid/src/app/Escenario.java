package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    protected Nave nave = new Nave();
    protected Esfera esfera = new Esfera(this);  
    protected Vector<Bloque> bloques=new Vector<Bloque>();
    protected ArrayList<Esfera> bolas = new ArrayList<Esfera>();
    protected ArrayList<String> niveles=new ArrayList<String>();
    protected ArrayList<BufferedImage> fondos=new ArrayList<BufferedImage>();
    protected float puntaje_actual=0;
    protected float puntaje_maximo=0;

    protected Rectangle limites;//creo que aca es menos
    private int nivelActual= 2;
    private boolean comenzo;
    private int cont;
    protected boolean nuevoNivel=false;
    //protected Thread t;     //lo puse para hacer el cronometro 
 


    
    public Escenario() {
        this.cargar();
        this.inicio();
        this.limites=new Rectangle(20, 45, img_fondoAzul.getWidth()-40,img_fondoAzul.getHeight()-90);
    }

    // ESTE METODO CARGA TODAS LAS IMAGENES NECESARIAS PARA EL ESCENARIO
    private void cargar() {
        try {
            img_fondoAzul = ImageIO.read(getClass().getResource("imagenes/FondoAzul.jpg"));
            img_fondoVerde = ImageIO.read(getClass().getResource("imagenes/FondoVerde.png"));
            img_fondoRojo = ImageIO.read(getClass().getResource("imagenes/FondoRojo.jpg"));
            fondo_negro = ImageIO.read(getClass().getResource("imagenes/negro_solido.png"));
            img_nave = ImageIO.read(getClass().getResource("imagenes/Vaus1.png"));
            img_bola = ImageIO.read(getClass().getResource("imagenes/bola.png"));

            fondos.add(img_fondoAzul);
            fondos.add(img_fondoRojo);
            fondos.add(img_fondoVerde);

            Files.walk(Paths.get("Niveles")).forEach(ruta-> {
                if (Files.isRegularFile(ruta)) {
                    this.niveles.add(String.valueOf(ruta));
                }
            });
        } catch (Exception e) {
            System.out.println("Error al cargas las imagenes del escenario");
        }
    }

    // incializamos todo en estas variables.
    public void inicio() {   
        this.nave = new Nave();
        this.bolas = new ArrayList<Esfera>();
        this.esfera = new Esfera(this);
        esfera.parada = true;
        bolas.add(esfera);
        cargarLadrillos(nivelActual);
    }

    public void corriendo(){
        // actualizo las bolas en el vector
        for (int i=0; i< bolas.size(); i++){
            Esfera esfera = bolas.get(i);
            if (esfera.y <= nave.getTOPY()){
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
    }

    public void siguienteNivel(){
        this.nivelActual++;
        this.nave = new Nave();
        this.bolas = new ArrayList<Esfera>();
        this.esfera = new Esfera(this);
        esfera.parada = true;
        bolas.add(esfera);
        cargarLadrillos(nivelActual);
        
        this.nuevoNivel=false;
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
        this.dibujoInicial(g,this.nivelActual,this. puntaje_actual,this.puntaje_maximo);
    }

    public void dibujoInicial(Graphics2D g,int nivelActual,float puntaje_actual,float puntaje_maximo){

        BufferedImage fondo=fondos.get(nivelActual-1);

        int limiteEscenario=fondo.getWidth();
        g.drawRect(18, 43, limiteEscenario-38, fondo.getHeight()-42);
        g.drawImage(fondo,0,25,null);
        g.drawImage(fondo_negro, limiteEscenario, 0,null);
        g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("PUNTAJE", limiteEscenario+50, 60);
        g.drawString(" MAXIMO", limiteEscenario+50, 95);
        g.setColor(Color.white);
        g.drawString(""+this.puntaje_maximo, limiteEscenario+50, 125);  //VARIABLE DEL PUNTAJE MAXIMO, VA A SER LA POS 0 DEL RANK
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("PUNTAJE", limiteEscenario+50, 180);
        g.drawString(" ACTUAL", limiteEscenario+50, 215);
        g.setColor(Color.white);
        g.drawString(""+puntaje_actual, limiteEscenario+50, 245);  //VARIABLE DEL PUNTAJE ACTUAL
        //DIBUJAR LAS NAVES DEPENDIENDO DE LAS VIDAS QUE TENGA
        for(int i=0;i<cantidad_vidas;i++){
            g.drawImage(img_nave, limiteEscenario+50+(45*i), 300, null);
        }
        g.setColor(Color.RED);
        g.drawString("NIVEL:", limiteEscenario+150, 550);
        g.setColor(Color.white);
        g.drawString(""+nivelActual, limiteEscenario+250, 550);    //ACA VA EL NIVEL
        //nave.setImagen(img_nave);
        Graphics2D g2 = (Graphics2D)g;
        g2.draw(this.limites);
        nave.draw(g);
        esfera.setImagen(img_bola);
        esfera.draw(g);
        //ACA DIBUJO TODOS LOS BLOQUES QUE TENGA CARGADO
        for(int i=0;i<this.bloques.size();i++){
            if(this.bloques.get(i).impactos<=0){
                this.puntaje_actual+=this.bloques.get(i).getPuntaje();
                this.bloques.remove(i);
            }
        }
        for (Bloque B : this.bloques) {
            B.draw(g);
        }
        
    }

    public Vector<Bloque> getBloques(){
        return this.bloques;
    }

    public int getNivel(){
        return this.nivelActual;
    }

    public void update(double delta,Keyboard keyboard){
        for (int i=0;i<bolas.size();i++) {
           bolas.get(i).rebote();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT) && nave.getX()>23){
            nave.setDX(-1);
             nave.mover();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && nave.getX()<img_fondoAzul.getWidth()-84){
            nave.setDX(1);
            nave.mover();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
            this.esfera.parada = false;
        }

        if (this.esfera.parada){
            
            if ((keyboard.isKeyPressed(KeyEvent.VK_LEFT) || keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) 
                                && (nave.getX()>23 && nave.getX()<img_fondoAzul.getWidth()-84)){
              this.esfera.setX(nave.getX()+(nave.getWidth()/2)-(this.esfera.getWidth()/2));
              this.esfera.mover();
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && nave.getX()<img_fondoAzul.getWidth()-84){
               nave.setDX(1);
               nave.mover();
            }
        }

        else{
         this.esfera.mover();
          if(esfera.getY()==nave.getY()&&(esfera.getX()>=nave.getX()&&esfera.getX()<=(nave.getX()+nave.getWidth())))
            {
                esfera.setDY(-1);
                esfera.setY(nave.getTOPY() - esfera.DIAMETER);
            }
        }
        if(this.bloques.isEmpty()){
            siguienteNivel();
        }
    }
    
    
    //NECESITARIA RECIBIR EL NIVEL QUE TENGO QUE CARGAR
    public void cargarLadrillos(int nivelActual){
        try {
            RandomAccessFile nivel1 = new RandomAccessFile(this.niveles.get(nivelActual-1), "r");
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
                        case "A": bloques.add(new Bloque(this, "A", x, y));break;
                        case "Z": bloques.add(new Bloque(this, "Z", x, y));break;
                        case "B": bloques.add(new Bloque(this, "B", x, y));break;
                        case "C": bloques.add(new Bloque(this, "C", x, y));break;
                        case "D": bloques.add(new Bloque(this, "D", x, y));break;
                        case "N": bloques.add(new Bloque(this, "N", x, y));break;
                        case "P": bloques.add(new Bloque(this, "P", x, y));break;
                        case "R": bloques.add(new Bloque(this, "R", x, y));break;
                        case "S": bloques.add(new Bloque(this, "S", x, y));break;
                        case "V": bloques.add(new Bloque(this, "V", x, y));break;
                        case "X": break;
                    }
                    x=x+40;
                }
                x=25;
                y = y + 20;
            }
            nivel1.close();
        } catch (Exception e) {
            //System.out.println("Error al cargar los niveles");
            System.out.println(e);
        }
    }
    //CALCULA EL REBOTE DE LA PELOTA CON LOS BLOQUES
    

}