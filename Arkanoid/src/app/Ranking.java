package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.imageio.ImageIO;

import com.google.gson.Gson;



public class Ranking{
    protected BufferedImage fondo=null;
    
    protected Gson gson=new Gson();
    protected BufferedReader br = null;
    protected TodosLosPuntajes datos=new TodosLosPuntajes();

    public Ranking(){
        this.cargarInfo();
        //this.escribirInfo();
    }
    

    public void cargarInfo(){
        try {
            fondo=ImageIO.read(getClass().getResource("imagenes/fondoRanking.jpg"));
            
            
            br = new BufferedReader(new FileReader("ranking.json"));
            TodosLosPuntajes datos=gson.fromJson(br, TodosLosPuntajes.class);

        } catch (Exception e) {
            //System.out.println("Error al cargar imagenes ranking");
            System.out.println(e);
        }
    }
    public void escribirInfo(){
        //ESCRIBIMOS UN JSON
        String json=gson.toJson(new Puntaje("Julian","1000","2","12/12/12"));
        System.out.println(json);
    }

    public void draw(Graphics2D g){
        g.drawImage(fondo,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD, 30));
        g.drawString("Nombre",50,80);
        g.drawString("Puntos",250,80);
        g.drawString("Nivel",450,80);
        g.drawString("Fecha",650,80);
        g.setColor(Color.ORANGE);
        if (datos != null) {
            for(Puntaje p: datos.getPuntajes()){
                p.mostrar();
            }
        }
    }

    

}