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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;

public class Escenario implements Runnable{
    protected BufferedImage img_fondoAzul = null;
    protected BufferedImage img_fondoVerde = null;
    protected BufferedImage img_fondoRojo = null;
    protected BufferedImage fondo_negro = null;
    protected BufferedImage img_nave = null;
    protected BufferedImage img_bola = null;
    protected int cantidad_vidas=3; //limitar cantidad de vidas a 5
    protected Nave nave = new Nave(this);
    protected Esfera esfera = new Esfera(this);  
    protected Vector<Bloque> bloques=new Vector<Bloque>();
    protected ArrayList<Esfera> bolas = new ArrayList<Esfera>();
    protected ArrayList<String> niveles=new ArrayList<String>();
    protected Vector<Bonus> bonuses=new Vector<Bonus>();
    protected ArrayList<BufferedImage> fondos=new ArrayList<BufferedImage>();
    protected float puntaje_actual=0;
    protected float puntaje_maximo=0;
    protected int cant_dorados;


    protected Rectangle limites;
    private int nivelActual= 1;
    private boolean comenzo;
    private int cont;
    protected boolean nuevoNivel=false;
    //protected Thread t;     //lo puse para hacer el cronometro 
	//private int contador;
     Date dInit = new Date();
	 Date dAhora;
	 SimpleDateFormat ft = new SimpleDateFormat ("mm:ss");
 


    
    public Escenario() {
        this.cargar();
        this.inicio();
        this.limites=new Rectangle(20, 45, img_fondoAzul.getWidth()-40,img_fondoAzul.getHeight()-90);

        //crono = new Thread(this);
    }

    // ESTE METODO CARGA TODAS LAS IMAGENES NECESARIAS PARA EL ESCENARIO
    private void cargar() {
        try {
            img_fondoAzul = ImageIO.read(getClass().getResource("imagenes/FondoAzul.jpg"));
            img_fondoVerde = ImageIO.read(getClass().getResource("imagenes/FondoVerde.png"));
            img_fondoRojo = ImageIO.read(getClass().getResource("imagenes/FondoRojo.jpg"));
            fondo_negro = ImageIO.read(getClass().getResource("imagenes/negro_solido.png"));
            img_nave = ImageIO.read(getClass().getResource("imagenes/Vaus0.png"));
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
        this.nave = new Nave(this);
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
            
            this.gameOver();//crear funcion
            }
        
            if (this.comenzo==true){
                if (this.cont==1){
                        //crono.start(); //definir tanto crono como el start
                        System.out.println("el juego comenzo");
                        this.cont--;
                   }
              
            }
        }      
    }

    public void siguienteNivel(){
        if(this.nivelActual<this.niveles.size()){
            this.nivelActual++;
            this.nave = new Nave(this);
            this.bolas = new ArrayList<Esfera>();
            this.esfera = new Esfera(this);
            esfera.parada = true;
            this.bolas.clear();
            this.bonuses.clear();
            this.bloques.clear();
            bolas.add(esfera);
            cargarLadrillos(nivelActual);
            
            this.nuevoNivel=false;
        }
    }

    private void gameOver() {
        //cargar puntaje en ranking
        // deberia volver al menu y abrirse la ventana ranking

        this.borrarNivel();
        //colocar en el nivel 1
        FinJuego finJuego= new FinJuego();
        finJuego.run(1.0/60.0);
        this.cantidad_vidas=3;
        this.comenzo=false;
        //setear puntajes a cero
        // setear el tiempo a cero
        this.inicio();
    }

    private void borrarNivel(){
        bolas.clear();
        bonuses.clear();
        bloques.clear();
        this.nave=null;
        this.nivelActual=1;
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

        dAhora= new Date( );
    	long dateDiff = dAhora.getTime() - dInit.getTime();
    	long diffSeconds = dateDiff / 1000 % 60;
		long diffMinutes = dateDiff / (60 * 1000) % 60;

        g.setFont(new Font("Courier", Font.BOLD, 15));
        g.setColor(Color.black);
        g.drawString("Tiempo de Juego: "+diffMinutes+":"+diffSeconds,limiteEscenario+25,592);

    	g.setColor(Color.white);
    	g.drawString("Tiempo de Juego: "+diffMinutes+":"+diffSeconds,limiteEscenario+27,594);
		
       
        //nave.setImagen(img_nave);
        
        nave.draw(g);
        for (int i=0;i<this.bolas.size();i++) {
            if(this.bolas.get(i).isActiva()==false){
                this.bolas.remove(this.bolas.get(i));             
            }else{
                this.bolas.get(i).setImagen(img_bola);
                this.bolas.get(i).draw(g);
            }
        }
        //ACA DIBUJO TODOS LOS BLOQUES QUE TENGA CARGADO
        for(int i=0;i<this.bloques.size();i++){
            if(this.bloques.get(i).impactos<=0){
                this.puntaje_actual+=this.bloques.get(i).getPuntaje();
                this.bonuses.add(this.bloques.get(i).getBonus());
                this.bloques.remove(i);
            }
        }
        for (Bloque B : this.bloques) {
            B.draw(g);
        }
        for(int i=0;i<bonuses.size();i++){
            if(bonuses.get(i)!=null){
                bonuses.get(i).draw(g);
            }
        }
        
    }

    public ArrayList<String> getNiveles(){
        return this.niveles;
    }

    public Vector<Bonus> getBonuses(){
        return this.bonuses;
    }

    public ArrayList<Esfera> getBolas(){
        return this.bolas;
    }

    public void setBolas(ArrayList<Esfera> bolas){
        this.bolas=bolas;
    }

    public Nave getNave(){
        return this.nave;
    }

    public Vector<Bloque> getBloques(){
        return this.bloques;
    }

    public int getNivel(){
        return this.nivelActual;
    }

    public void update(double delta,Keyboard keyboard){
        if(this.cantidad_vidas== 0)
         gameOver();
        else{
        for (int i=0;i<bolas.size();i++) {
           bolas.get(i).rebote();
        }
        for(int i=0;i<bonuses.size();i++){
            if(bonuses.get(i)!=null){
                bonuses.get(i).mover();
                if(bonuses.get(i).cuerpo.intersects(this.nave.cuerpo)){
                    bonuses.get(i).update(0);
                    try {
                        bonuses.remove(i);
                    } catch (Exception e) {
                        //ESTO ES SOLO PARA CUANDO PASA DE NIVEL
                        System.out.println("Paso de nivel");
                    }
                }
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT) && nave.getX()>23){
            nave.setDX(-1);
             nave.mover();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && nave.getX()+nave.getWidth()<img_fondoAzul.getWidth()-20){
            nave.setDX(1);
            nave.mover();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
            this.esfera.parada = false;
            /*if(contador==1){ //Solo empieza una vez el cronometro
                crono.start(); //Empezamos el cronometro
                contador--;
            }*/
        }

        if (this.esfera.parada){
            
            if ((keyboard.isKeyPressed(KeyEvent.VK_LEFT) || keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) 
                                && (nave.getX()>23 && nave.getX()+nave.getWidth()<img_fondoAzul.getWidth()-20)){
              this.esfera.setX(nave.getX()+(nave.getWidth()/2)-(this.esfera.getWidth()/2));
              this.esfera.mover();
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && nave.getX()+nave.getWidth()<img_fondoAzul.getWidth()-20){
               nave.setDX(1);
               nave.mover();
            }
        }
        else{
            for (Esfera esfera : this.bolas) {
                esfera.mover();
            }
        }
        if(this.bloques.size()==this.cant_dorados){
            siguienteNivel();
        }
    }
    }
    
    public void addBonus(Bloque bloque){
        if(bloque.tieneBonus())
            this.bonuses.add(bloque.getBonus());
    }

    public int getVidas(){
        return this.cantidad_vidas;
    }

    public void setVidas(int vidas){
        this.cantidad_vidas=vidas;
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
                        case "D": bloques.add(new Bloque(this, "D", x, y));this.cant_dorados++;break;
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
                y = y + 23;
            }
            nivel1.close();
        } catch (Exception e) {
            System.out.println("Error al cargar los niveles");
        }
    }
    //CALCULA EL REBOTE DE LA PELOTA CON LOS BLOQUES

    @Override
    public void run() {
        /*try {
			for(;;) { //Que comience a contar todo el rato +1; en cada interaccion
				if(this.segundos==59) { // Si llegamos el minuto
					this.segundos=0; this.minutos++;} // Aadimos un minuto mas
				if(this.minutos==59) { this.minutos=0; this.horas++;} // Si llegamos a la hora
				this.segundos++;//Aadimos una hora mas
				crono.sleep(1000); }
		}

		catch (InterruptedException e) { System.out.println(e.getMessage()); }*/
	}
}

    

