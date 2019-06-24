package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.RandomAccessFile;

import java.util.*;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;




public class Ranking{
    protected BufferedImage fondo=null;
    
    
    protected static String[] nombres=new String[10];
    protected static float[] puntos=new float[10];
    protected static int[] niveles=new int[10];
    protected static String[] fechas=new String[10];
    
    protected static int lineas=0;
    
    //protected BufferedReader datos = null;
    protected boolean isActive=true;

    public Ranking(){
        this.cargarInfo();
        //this.escribirInfo();
    }
    
    public void cargarInfo(){
        try {
            fondo=ImageIO.read(getClass().getResource("imagenes/fondoRanking.jpg"));
            RandomAccessFile datos = new RandomAccessFile("ranking.txt", "r");
            
            while(datos.readLine()!=null){
                lineas++;
            }
            datos.seek(0);

            for(int i=0;i<lineas;i++){
                String renglon=datos.readLine();
                System.out.println(renglon);
                String palabras[]=renglon.split("-");
                nombres[i]=palabras[0];
                puntos[i]=Float.parseFloat(palabras[1]);
                niveles[i]=Integer.parseInt(palabras[2]);
                fechas[i]=palabras[3];
            }

            for (String nombre : nombres) {
                System.out.println(nombre);
            }
            datos.close();
        } catch (Exception e) {
            System.out.println("ERROR"+e);
        }
    }

//TENER EN CUENTA QUE SOLO PUEDEN SER 10
    
   public static void escribirInfo(String nombre,float puntaje,int nivel){ //la fecha la tengo que poner yo
        String aux_nombre;
        float aux_puntaje;
        int aux_nivel;
        String aux_fecha;
        Calendar fecha=new GregorianCalendar();
        String fecha_actual=fecha.get(Calendar.DAY_OF_MONTH)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
        boolean inserto=false;

        try {
            RandomAccessFile datos = new RandomAccessFile("ranking.txt", "rw");
            //INSERTAR ORDENADO Y ESCRIBIR
            if(lineas==0){
                nombres[0]=nombre;
                puntos[0]=puntaje;
                niveles[0]=nivel;
                fechas[0]=fecha_actual;
            }else{
                if(puntaje>puntos[lineas-1]){    
                    for(int i=0;i<lineas && inserto==false;i++){
                        if(puntaje>puntos[i]){
                            aux_nombre=nombres[i];
                            aux_puntaje=puntos[i];
                            aux_nivel=niveles[i];
                            aux_fecha=fechas[i];
                            nombres[i]=nombre;
                            puntos[i]=puntaje;
                            niveles[i]=nivel;
                            fechas[i]=fecha_actual;
                            inserto=true;
                            for(int j=lineas-1;j>i;j--){
                                nombres[j+1]=nombres[j];
                                puntos[j+1]=puntos[j];
                                niveles[j+1]=niveles[j];
                                fechas[j+1]=fechas[j];
                            }
                            nombres[i+1]=aux_nombre;
                            puntos[i+1]=aux_puntaje;
                            niveles[i+1]=aux_nivel;
                            fechas[i+1]=aux_fecha;
                        }
                    }
                    //AHORA QUE YA INSERTE TENGO QUE ESCRIBIR EL ARCHIVO
                }
            }
            for(int i=0;i<10;i++){
                if(nombres[i]!=null){
                    datos.writeBytes(nombres[i]+"-");
                    datos.writeBytes(puntos[i]+"-");
                    datos.writeBytes(niveles[i]+"-");
                    datos.writeBytes(fechas[i]+"\n");
                }
            }
            datos.close();
        } catch (Exception e) {
            //System.out.println("ERROR AL ESCRIBIR EL RANKING");
            System.out.println(e);
        }
    }

    public static float getPuntajeMaximo(){
        return puntos[0];
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

        for (int i=0;i<nombres.length;i++){
            if(nombres[i]!=null){
                g.drawString(nombres[i], 40, 100+40*i);
                g.drawString(Float.toString(puntos[i]), 250, 100+40*i);
                g.drawString(Integer.toString(niveles[i]),470, 100+40*i);
                g.drawString(fechas[i],640, 100+40*i);
            }
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