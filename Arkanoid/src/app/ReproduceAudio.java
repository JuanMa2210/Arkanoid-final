package app;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ReproduceAudio {

    Clip sonido;

    public ReproduceAudio(String ubicacion){
        try {
            sonido = AudioSystem.getClip(); //se obtiene un clip de sonido
            sonido.open(AudioSystem.getAudioInputStream(new File(ubicacion)));  //se carga un fichero wav
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void comenzar(){
        sonido.start();
    }

    public void loop(){
        sonido.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void detener(){
        sonido.stop();
    }

}