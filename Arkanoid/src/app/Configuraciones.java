package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.FileOutputStream;

public class Configuraciones extends JFrame implements ItemListener, ActionListener {

    private JCheckBox FullScreen;
    private JButton Guardar = new JButton("Guardar");
    private JButton Reset = new JButton("Reset");
    private JButton ConfTeclas = new JButton("Conf.Teclado");
    private JLabel sound = new JLabel("Sonido: ");
    private JLabel MusicLabel = new JLabel("Musica: ");
    private List Musica;
    private ButtonGroup sonido;
    private JRadioButton sonidoSi = new JRadioButton("Si");
    private JRadioButton sonidoNo = new JRadioButton("No");

    private ImageIcon nave1Icon = new ImageIcon("imagenes/Vaus0.png");
    private ImageIcon nave2Icon = new ImageIcon("imagenes/Vaus1.png");
    private ImageIcon nave3Icon = new ImageIcon("imagenes/Vaus2.png");
    private ImageIcon nave4Icon = new ImageIcon("imagenes/Vaus3.png");
    private ImageIcon nave5Icon = new ImageIcon("imagenes/Vaus4.png");

    private ButtonGroup naves = new ButtonGroup();
    private JRadioButton nave1 = new JRadioButton();
    private JRadioButton nave2 = new JRadioButton();
    private JRadioButton nave3 = new JRadioButton();
    private JRadioButton nave4 = new JRadioButton();
    private JRadioButton nave5 = new JRadioButton();

    private Properties propiedades=new Properties();

    public Configuraciones() {
        super("Configuracion");

        FullScreen = new JCheckBox("Fullscren");
        FullScreen.addActionListener(this);
        sonido = new ButtonGroup();
        sonido.add(sonidoSi);
        sonido.add(sonidoNo);

        Musica = new List();
        Musica.addItemListener(this);
        Musica.add("Original");
        Musica.add("Techno");
        Musica.add("Rock");
        Musica.add("Clasica");
        Musica.select(0);

        naves.add(nave1);
        naves.add(nave2);
        naves.add(nave3);
        naves.add(nave4);
        naves.add(nave5);

        nave1.setLabel("Clasica");
        nave2.setLabel("Futurista");
        nave3.setLabel("FuturistaGris");
        nave4.setLabel("FuturistaAzul");
        nave5.setLabel("FuturistaRoja");

        Guardar.addActionListener(this);
        Reset.addActionListener(this);
        ConfTeclas.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(agregarItem(FullScreen));
        panel.add(agregarItem(sound));
        panel.add(agregar2Item(sonidoSi, sonidoNo));
        panel.add(agregar2Item(MusicLabel, Musica));
        panel.add(agregarItem(new JLabel("Nave: ")));

        panel.add(agregarItem(nave1));
        panel.add(agregarItem(nave2));
        panel.add(agregarItem(nave3));
        panel.add(agregarItem(nave4));
        panel.add(agregarItem(nave5));

        panel.add(agregarItem(ConfTeclas));
        panel.add(agregar2Item(Reset, Guardar));

        //LEO LOS VALORES ACTUALES Y PONGO LOS ITEM ACORDE
        this.leerPropiedades();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(panel);
        this.setVisible(true);
        this.pack();
    }


    public void leerPropiedades(){
        try {
            propiedades.load(new FileInputStream("jgame.properties"));
            switch (propiedades.getProperty("fullScreen")){
                case "true": this.FullScreen.setSelected(true);break;
                case "false": this.FullScreen.setSelected(false);break;
            }
            switch(propiedades.getProperty("sonido")){
                case "true": this.sonidoSi.setSelected(true);break;
                case "false": this.sonidoNo.setSelected(true);break;
            }
            switch (propiedades.getProperty("musica")){
                case "Original": this.Musica.select(0);break;
                case "Techno":  this.Musica.select(1);break;
                case "Rock":    this.Musica.select(2);break;
                case "Clasica":    this.Musica.select(3);break;
            }
            switch (propiedades.getProperty("nave")){
                case "clasica":     this.nave1.setSelected(true);break;
                case "futurista":   this.nave2.setSelected(true);break;
                case "futuristaGris":   this.nave3.setSelected(true);break;
                case "futuristaAzul":   this.nave4.setSelected(true);break;
                case "futuristaRoja":   this.nave5.setSelected(true);break;
            }
        } catch (Exception exception) {
            System.out.println("ERROR AL CARGAR PROPERTIES");
        }
    }

    private JPanel agregarItem(Component com1) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel.add(com1);
        return panel;
    }

    private JPanel agregar2Item(Component com1, Component com2) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel.add(com1);
        panel.add(com2);
        return panel;
    }

    // para la musica
    @Override
    public void itemStateChanged(ItemEvent e) {

    }
    //botones y checkbox
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()==this.Guardar.getActionCommand()){
            //escribir properties con las opciones actuales
            try {
                propiedades.load(new FileInputStream("jgame.properties"));
                propiedades.setProperty("fullScreen", this.FullScreen.isSelected()+"");
                System.out.println(this.FullScreen.isSelected()+"");
                propiedades.setProperty("sonido", this.sonidoSi.isSelected()+"");
                System.out.println(this.sonidoSi.isSelected()+"");
                propiedades.setProperty("musica", this.Musica.getSelectedItem());
                System.out.println(this.Musica.getSelectedItem());
                if(nave1.isSelected()){
                    propiedades.setProperty("nave", "clasica");
                }else{
                    if(nave2.isSelected()){
                        propiedades.setProperty("nave", "futurista");
                    }else{
                        if(nave3.isSelected()){
                            propiedades.setProperty("nave", "futuristaGris");
                        }else{
                            if(nave4.isSelected()){
                                propiedades.setProperty("nave", "futuristaAzul");
                            }else{
                                if(nave5.isSelected()){
                                    propiedades.setProperty("nave", "futuristaRoja");
                                }
                            }
                        }
                    }
                }
                propiedades.store(new FileOutputStream("jgame.properties"), null);
            } catch (Exception exception) {
                System.out.println("ERROR AL CARGAR PROPERTIES");
            }
        }
        if(e.getActionCommand()==this.Reset.getActionCommand()){
            //resetear cambios
            propiedades.setProperty("fullScreen", "false");
            propiedades.setProperty("sonido", "true");
            propiedades.setProperty("musica", "Original");
            propiedades.setProperty("nave", "clasica");
            try {
                propiedades.store(new FileOutputStream("jgame.properties"), null);
                this.leerPropiedades();
            } catch (Exception eeException) {
                System.out.println("ERROR AL RESETEAR CONFIGURACION");
            }
        }
        if(e.getActionCommand()==this.ConfTeclas.getActionCommand()){
            //abrir configuracion teclas
            new ConfigurarTeclas();

        }
    }



    private class ConfigurarTeclas extends JFrame{
        private JPanel principal=new JPanel();
        private JButton izquierda=new JButton("     ");
        private JButton derecha=new JButton("     ");
        private JButton soltar_bola=new JButton("     ");
        private JButton reset=new JButton("Reset");
        private JButton aceptar=new JButton("Aceptar");

        private ConfigurarTeclas(){
            super("Conf.Teclas");
            principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
            principal.add(agregar2Item(new JLabel("Izquierda: "), this.izquierda));
            principal.add(agregar2Item(new JLabel("Derecha: "), this.derecha));
            principal.add(agregar2Item(new JLabel("Soltar bola: "), this.soltar_bola));
            principal.add(agregar2Item(this.reset, this.aceptar));


            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setAlwaysOnTop(true);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.add(principal);
            this.setVisible(true);
            this.pack();
        }
    }
    
}