import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Interfaz extends JFrame {
    public static final int ANCHO = 800, ALTO = 600;
    /*public */ public JPanel panel;

    private JButton botonVerde;
    private JButton botonRojo;
    private JButton botonAmarillo;
    private JButton botonAzul;
    
    public JButton[] misBotones = new JButton[]{botonVerde, botonRojo, botonAmarillo, botonAzul};

    public Interfaz() 
    {
        setTitle("Ventana de prueba");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        setComponentes();
    }

    private void setComponentes()
    {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        setTitulo();
        setBotones();
    }

    private void setTitulo()
    {
        JLabel titulo = new JLabel("SIMON GAME", SwingConstants.CENTER);
        titulo.setBounds(0,20,ANCHO, 50);
        titulo.setFont(new Font("times new roman", Font.BOLD, 50));
        panel.add(titulo);

    }

    /*
     Cuadrante inferior derecho (color Azul) nota musical mi4 (659.255 Hz)
    • Cuadrante inferior izquierdo (color: Amarillo) nota musical do#4 (554.365 Hz)
    • Cuadrante superior derecho (color: Rojo) nota musical la3 (440 Hz)
    • Cuadrante superior izquierdo (color Verde) nota musical mi3 (329.628 Hz)
     */
    private void setBotones()
    {
        botonVerde = new JButton();
        botonVerde.setBounds(300,200 ,80,80);
        botonVerde.setBackground(Color.GREEN);
        botonVerde.addActionListener(Logica.reproducirSonidoListener(botonVerde, 329.628));
        

        botonRojo = new JButton();
        botonRojo.setBounds(380,200 ,80,80);
        botonRojo.setBackground(Color.RED);
        botonRojo.addActionListener(Logica.reproducirSonidoListener(botonRojo, 440));
        
        botonAmarillo = new JButton();
        botonAmarillo.setBounds(300,280,80,80);
        botonAmarillo.setBackground(Color.YELLOW);
        botonAmarillo.addActionListener(Logica.reproducirSonidoListener(botonAmarillo, 554.365));

        botonAzul = new JButton();
        botonAzul.setBounds(380,280,80,80);
        botonAzul.setBackground(Color.BLUE);
        botonAzul.addActionListener(Logica.reproducirSonidoListener(botonAzul, 659.255));

        misBotones[0] = botonVerde;
        misBotones[1] = botonRojo;
        misBotones[2] = botonAmarillo;
        misBotones[3] = botonAzul;
        // misBotones.add(botonVerde);
        // misBotones.add(botonRojo);
        // misBotones.add(botonAmarillo);
        // misBotones.add(botonAzul);

        panel.add(botonVerde);
        panel.add(botonRojo);
        panel.add(botonAmarillo);
        panel.add(botonAzul);
    }

}
