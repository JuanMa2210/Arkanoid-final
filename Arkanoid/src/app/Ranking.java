package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;
import com.google.gson.Gson;



public class Ranking{
    protected BufferedImage fondo=null;
    
    protected Gson gson=new Gson();
    protected BufferedReader br = null;
    protected TodosLosPuntajes datos=new TodosLosPuntajes();

    protected ArrayList<String> nombres=new ArrayList<>();
    protected ArrayList<Integer> puntos=new ArrayList<>();
    protected ArrayList<String> niveles=new ArrayList<>();
    protected ArrayList<String> fechas=new ArrayList<>();


    protected boolean isActive=true;

    public Ranking(){
        this.cargarInfo();
        //this.escribirInfo();
    }
    
    public void cargarInfo(){
        try {
            fondo=ImageIO.read(getClass().getResource("imagenes/fondoRanking.jpg"));
            RandomAccessFile datos = new RandomAccessFile("ranking.txt", "r");
            int lineas=0;
            while(datos.readLine()!=null){
                lineas++;
            }
            datos.seek(0);

            for(int i=0;i<lineas;i++){
                String renglon=datos.readLine();
                String palabras[]=renglon.split("-");
                this.nombres.add(palabras[0]);
                this.puntos.add(Integer.parseInt(palabras[1]));
                this.niveles.add(palabras[2]);
                this.fechas.add(palabras[3]);
            }

            for (String nombre : nombres) {
                System.out.println(nombre);
            }
            datos.close();
        } catch (Exception e) {
            System.out.println("ERROR"+e);
        }
    }


    
    public void escribirInfo(){
        
        String json=gson.toJson(new Puntaje("Julian","1000","2","12/12/12"));
        System.out.println(json);
    }

    public void draw(Graphics2D g){
        g.drawImage(fondo,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD, 26));
        g.drawString("Nombre",30,60);
        g.drawString("Puntos",250,60);
        g.drawString("Nivel",450,60);
        g.drawString("Fecha",650,60);
        g.setColor(Color.ORANGE);

        for (int i=0;i<nombres.size();i++){
            g.drawString(this.nombres.get(i), 40, 100+40*i);
            g.drawString(this.puntos.get(i).toString(), 250, 100+40*i);
            g.drawString(this.niveles.get(i),470, 100+40*i);
            g.drawString(this.fechas.get(i),640, 100+40*i);
        }
        g.setColor(Color.WHITE);
        g.drawString("ESC para volver...",290,550);
    }

    public void update(double delta,Keyboard teclado){
        if(teclado.isKeyPressed(KeyEvent.VK_ESCAPE)){
            this.isActive=false;
        }
    }

    public void activar(){
        this.isActive=true;
    }

    public boolean isActive(){
        return isActive;
    }

    

}