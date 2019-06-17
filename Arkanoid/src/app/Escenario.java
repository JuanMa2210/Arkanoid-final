package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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

    protected Rectangle limites;//creo que aca es menos
    private int nivelActual= 3;
    private boolean comenzo;
    private int cont;
    protected static boolean nuevoNivel=true;
    protected Thread t;
 


    
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
            img_nave = ImageIO.read(getClass().getResource("imagenes/naveNormal.png"));
            img_bola = ImageIO.read(getClass().getResource("imagenes/bola.png"));
         //   int i=0;
            Files.walk(Paths.get("Niveles")).forEach(ruta-> {
                if (Files.isRegularFile(ruta)) {
                    System.out.println(String.valueOf(ruta));
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
    //nave.start();
    if (nuevoNivel){    //aca pasamos al siguiente nivel
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
        Graphics2D g2 = (Graphics2D)g;
        g2.draw(this.limites);
        nave.draw(g);
        esfera.setImagen(img_bola);
        esfera.draw(g);
        //ACA DIBUJO TODOS LOS BLOQUES QUE TENGA CARGADO
        for (Bloque B : bloques) {
            if(B.getImpactos()==0){
                bloques.remove(B);
            }else{
                B.draw(g);
            }
        }
        
    }

    public void update(double delta,Keyboard keyboard){
        this.rebote(this.esfera);
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

    public void rebote(Esfera esfera){ //lo traje para acá a ver si lo podia hacer andar
                                        // pero tampoco anda, no me entra al if
        
        for(int i=0;i<bloques.size();i++){
           Bloque bloquesillo= bloques.get(i); 
            if(esfera.getBounds().intersects(bloquesillo.getBounds())){
                System.out.println("chocamo un bloque");
                double xEsfera=esfera.getX();   //CREO QUE ACA ESTA MAL, PORQUE NO ESTOY CONSIDERANDO 
                double yEsfera=esfera.getY();           //EL CUERPO DE LA PELOTA
                if(xEsfera==bloquesillo.x || xEsfera==bloquesillo.x+bloquesillo.ancho){
                   //PEGO EN ALGUNO DE LOS LADOS
                    esfera.setDX(esfera.getDX()*-1);
                }else{
                    if(yEsfera==bloquesillo.y || yEsfera==bloquesillo.y+bloquesillo.alto){
                        //PEGO ARRIBA O ABAJO
                        esfera.setDY(esfera.getDY()*-1);
                    }
                }
                bloquesillo.restarImpactos();
            }
        }
    }

}