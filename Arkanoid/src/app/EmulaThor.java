package app;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EmulaThor extends JFrame implements ActionListener, ItemListener {

    private JMenu Archivo;
    private JMenu Ayuda;
    private JMenuItem Configuraciones;
    private JMenuItem Salir;
    private JFrame config;
    //Botones y opciones de config
    private JPanel Base;
    private JPanel Superior;
    private JButton Guardar;
    private JButton Reset;
    private JLabel Sound;
    private JRadioButton Activado;
    private JRadioButton Desactivado;
    private List Musica;
    
    ///
    private JButton Comenzar;
    private JPanel ListPanel;
    private List Juegos;
    private JPanel PanelImagen;
    
    private MyCanvas PreviewJuego;
    private JMenuBar jMenuBar1;
    private String arrayRuta[] = { "bin/app/imagenes/Arkanoid.jpg", "bin/app/imagenes/circuscharlie.png",
            "bin/app/imagenes/contra.jpg", "bin/app/imagenes/SuperMArioBros.jpg", "bin/app/imagenes/MetalSlug.jpg",
            "bin/app/imagenes/pacman.jpg", "bin/app/imagenes/Rayman-Original.jpg" };
    private Image arrayPreviews[] = new Image[7];

    public EmulaThor() {
        ListPanel = new JPanel();
        ListPanel.setLayout(new BorderLayout());
        Juegos = new List();
        Juegos.addItemListener(this);
        PanelImagen = new JPanel();
        PanelImagen.setLayout(new BorderLayout());
        Comenzar = new JButton();
        jMenuBar1 = new JMenuBar();
        Archivo = new JMenu();
        Configuraciones = new JMenuItem();
        Salir = new JMenuItem();
        Ayuda = new JMenu();

        Juegos.add("Arkanoid");
        Juegos.add("Circus Charlie");
        Juegos.add("Contra");
        Juegos.add("Super Mario Bros");
        Juegos.add("Metal Slug");
        Juegos.add("Pac-Man");
        Juegos.add("Rayman");

        for (int i = 0; i < arrayRuta.length; i++) {
            try {
                arrayPreviews[i] = ImageIO.read(new File(arrayRuta[i]));
                arrayPreviews[i] = arrayPreviews[i].getScaledInstance(600,400, Image.SCALE_SMOOTH);
            } catch (IOException e) {
            }
        }
        PreviewJuego = new MyCanvas(arrayPreviews[0]);
        Archivo.setText("Archivo");
        Configuraciones.setText("Configuraciones");
        Configuraciones.addActionListener(this);
        Salir.setText("Salir");
        Salir.addActionListener(this);
        Ayuda.setText("Ayuda");
        Archivo.add(Configuraciones);
        Archivo.add(Salir);
        jMenuBar1.add(Archivo);
        jMenuBar1.add(Ayuda);
        setJMenuBar(jMenuBar1);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Juegos.select(0);
        ListPanel.add(Juegos, BorderLayout.CENTER);

        Comenzar.addActionListener(this);
        Comenzar.setIcon(new ImageIcon("bin/app/imagenes/ComenzarJuego.jpg"));
        Comenzar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        Comenzar.setBorderPainted(false);
        Comenzar.setContentAreaFilled(false);

        PanelImagen.add(Comenzar, BorderLayout.SOUTH);
        PanelImagen.add(PreviewJuego, BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        this.add(ListPanel, BorderLayout.WEST);
        this.add(PanelImagen, BorderLayout.EAST);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        setTitle("EmulaThor");
        this.setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(Salir))
            System.exit(0);
        if (evt.getSource().equals(Configuraciones)){
            /* 1. Juego en ventana o en pantalla completa (por defecto: en ventana)
                2. Sonido activado/desactivado (por defecto: activado)
                3. Definición de teclas (por defecto: flechas=dirección; disparos=barra espaciadora)
                4. Selección de pista musical (por defecto: tema original)
                5. Selección de la apariencia de la nave.
                La interface debe incluir un botón para “Guardar” los cambios y 
                además un botón “RESET” para volver todos los parámetros a sus valores por defecto.*/ 
            
            Base = new JPanel();
            Superior = new JPanel();
            Sound  = new JLabel("Sonido");
            Superior.add(Sound);
            Musica = new List();
            Musica.addItemListener(this);
            Guardar = new JButton("Guardar");
            Reset = new JButton("Reset");
            Base.add(Guardar);
            Base.add(Reset);
            JRadioButton Activado= new JRadioButton("Activado", true);
                Activado.setBounds(20, 200, 109, 23);
                Superior.add(Activado);
                Activado.addItemListener(this);
                if (evt.getSource().equals(Activado))
                    System.out.println("sonido activado");
 
                JRadioButton Desactivado = new JRadioButton("Desactivado", false);
                Desactivado.setBounds(40, 220, 109, 23);
                Superior.add(Desactivado);
                Desactivado.addItemListener(this);
 
                ButtonGroup bgroup = new ButtonGroup();
                bgroup.add(Activado);
                bgroup.add(Desactivado);

            JFrame config =new JFrame();
          //  Superior.setBounds(500, 300, 300, 300);
           // Base.setBounds(800, 300, 100, 300);
            config.add(Base, BorderLayout.SOUTH);
            config.add(Superior, BorderLayout.NORTH);

            config.setVisible(true);
            config.setResizable(false);
            config.pack();
            config.setTitle("Configuraciones");
            config.setLocationRelativeTo(null);
        }

        if (evt.getSource().equals(Comenzar) && Juegos.getSelectedItem().equals("Arkanoid")) {
            System.out.println("Juego");
            new Menu();
            this.dispose();
        }
    }

    public void itemStateChanged(java.awt.event.ItemEvent evt) {
        PreviewJuego.cambiarImagen(arrayPreviews[Juegos.getSelectedIndex()]);
    }

    public static void main(String args[]) {
        new EmulaThor();
    }

}

class MyCanvas extends Canvas{
    private Image imagen;

	public MyCanvas (Image i){
		imagen = i;
		this.setPreferredSize(new Dimension(600,400));
	}

	public void cambiarImagen(Image i){
		imagen = i;
		repaint();
	}

	public void paint(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawRect(0,0,imagen.getWidth(this)+1 ,imagen.getHeight(this)+1);
		g.drawImage(imagen,0,0,this);
	}

}
