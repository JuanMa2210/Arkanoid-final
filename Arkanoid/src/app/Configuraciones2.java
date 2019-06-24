package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Configuraciones2 extends JFrame implements ItemListener, ActionListener {

    private JCheckBox FullScreen;
    private JButton Guardar = new JButton("Guardar");
    private JButton Reset = new JButton("Reset");
    private JButton ConfTeclas = new JButton("Conf.Teclado");
    private JLabel sound = new JLabel("Sonido: ");
    private JLabel MusicLabel = new JLabel("Musica: ");
    private List Musica;
    private ButtonGroup sonido;
    private JRadioButton sonidoSi = new JRadioButton("Si", true);
    private JRadioButton sonidoNo = new JRadioButton("No", false);

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

    public Configuraciones2() {
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
        Musica.add("Clasico");
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

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(panel);
        this.setVisible(true);
        this.pack();
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

    }
    
}