package app;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.JFrame;

public class Configuraciones extends JFrame implements ActionListener, ItemListener{
    
    private JPanel Base;
    private JPanel Superior;
    private JButton Guardar;
    private JButton Reset;
    private JLabel Sound;
    private JRadioButton Activado;
    private JRadioButton Desactivado;
    private List Musica;
    private JCheckBox FullScren;
    private Icon icon;
    private JRadioButton Nave1;
    private JRadioButton Nave2;
    private JRadioButton Nave3;
    private JRadioButton Nave4;
    private JRadioButton Nave5;
    private String arrayNaves[]={};    
    private String arrayMusica[]={};  
    
    public Configuraciones(){
    Base = new JPanel();
    Superior = new JPanel();
    Sound  = new JLabel("Sonido");
    Musica = new List();
    FullScren =new JCheckBox("Fullscren");
    FullScren.setSelected(false);
    if (FullScren.isSelected());
        //modificar propertis
    FullScren.setName("Fullscren");
    Superior.add(FullScren);
    Musica.addItemListener(this);
    Musica.add("Original");
    Musica.add("Techno");
    Musica.add("Rock");
    Musica.add("Clasico");
    Musica.select(0);
    for (int i = 0; i < arrayMusica.length; i++) {
       // cargar musica
    }

    Guardar = new JButton("Guardar");
    Reset = new JButton("Reset");
    Base.add(Guardar);
    Base.add(Reset);
    Superior.add(Sound);
    //////////////////////////////////////////////////////////////
    JRadioButton Activado= new JRadioButton("Activado", true);
        Activado.setBounds(20, 200, 109, 23);
        Superior.add(Activado);
        Activado.addItemListener(this);

        JRadioButton Desactivado = new JRadioButton("Desactivado", false);
        Desactivado.setBounds(40, 220, 109, 23);
        Superior.add(Desactivado);
        Desactivado.addItemListener(this);

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(Activado);
        bgroup.add(Desactivado);
    ///////////////////////////////////////////////////////////////////////
    Superior.add(Musica);
    //////////////////////////////////////////////////////////////////////
    icon= new Icon(){
    
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            
        }
    
        @Override
        public int getIconWidth() {
            return 0;
        }
    
        @Override
        public int getIconHeight() {
            return 0;
        }
    };
    JRadioButton Nave1= new JRadioButton(icon,true);
    Superior.add(Nave1);
    JRadioButton Nave2 = new JRadioButton();
    Superior.add(Nave2);
    JRadioButton Nave3 = new JRadioButton();
    Superior.add(Nave3);
    JRadioButton Nave4 = new JRadioButton();
    Superior.add(Nave4);
    JRadioButton Nave5 = new JRadioButton();
    Superior.add(Nave5);

    ButtonGroup navesGroup = new ButtonGroup();
    navesGroup.add(Nave1);
    navesGroup.add(Nave2);
    navesGroup.add(Nave3);
    navesGroup.add(Nave4);
    navesGroup.add(Nave5);
    /////////////////////////////////////////////////////////////////////
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

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Activado))
        System.out.println("sonido activado");
    }
}