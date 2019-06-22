package app;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
   public Sonido(){

   }
   public static void main(String[] args) {
      try {
         Clip sonido = AudioSystem.getClip();
         File a = new File("C:/Users/Juan Manuel Lara/OneDrive/Documentos/GitKraken/Poo-new/Arkanoid/bin/app/Sonidos/Rebotes.wav");
         sonido.open(AudioSystem.getAudioInputStream(a));
         sonido.start();
         System.out.println("Reproduciendo 10s. de sonido...");
         Thread.sleep(1000); // 10000 milisegundos (10 segundos)
         sonido.close();
      } catch (Exception tipoError) {
         System.out.println("" + tipoError);
      }
   }
}