package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;
import com.google.gson.Gson;



public class Ranking{
    protected BufferedImage fondo=null;
    
    protected Gson gson=new Gson();
    protected BufferedReader br = null;
    protected TodosLosPuntajes datos=new TodosLosPuntajes();
    protected String[] nombres;
    protected String[] puntos;
    protected String[] niveles;
    protected String[] fechas;

    protected boolean isActive=true;

    public Ranking(){
        this.cargarInfo();
        //this.escribirInfo();
    }
    

    public void cargarInfo(){
        try {
            fondo=ImageIO.read(getClass().getResource("imagenes/fondoRanking.jpg"));
            
            br = new BufferedReader(new FileReader("ranking.json"));
            datos=gson.fromJson(br,TodosLosPuntajes.class);
            if (datos != null) {
                for (Puntaje p : datos.getPuntajes()) {
                    //System.out.println(p.getNombre());
                }
            }

        } catch (Exception e) {
            //System.out.println("Error al cargar imagenes ranking");
            System.out.println(e);
        }
    }
    public void escribirInfo(){
        //ESCRIBIMOS UN JSON
        //ESTA FUNCION TAMBIEN TIENE QUE ORDENAR ANTS DE INSERTAR UN NUEVO.
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

        int i=0;
        for (Puntaje p : datos.getPuntajes()) {
            g.drawString(p.getNombre(), 40, 100+40*i);
            g.drawString(p.getPuntos(), 250, 100+40*i);
            g.drawString(p.getNivel(),470, 100+40*i);
            g.drawString(p.getFecha(),640, 100+40*i);
            i++;
        }
    }

    public void update(double delta,Keyboard teclado){
        if(teclado.isKeyPressed(KeyEvent.VK_ENTER)){
            isActive=false;
        }
    }

    public boolean isActive(){
        return isActive;
    }

    

}