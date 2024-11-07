import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Ventana extends JFrame {
    public static final int ANCHO = 800, ALTO = 600;
    private JPanel panel;

    private JButton botonVerde;
    private JButton botonRojo;
    private JButton botonAmarillo;
    private JButton botonAzul;
    
    public static List<JButton> misBotones = new ArrayList<JButton>();

    public Ventana() 
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

    private void setBotones()
    {
        botonVerde = new JButton();
        botonVerde.setBounds(300,200 ,80,80);
        botonVerde.setBackground(Color.GREEN);
        

        botonRojo = new JButton();
        botonRojo.setBounds(380,200 ,80,80);
        botonRojo.setBackground(Color.RED);
        
        botonAmarillo = new JButton();
        botonAmarillo.setBounds(300,280,80,80);
        botonAmarillo.setBackground(Color.YELLOW);

        botonAzul = new JButton();
        botonAzul.setBounds(380,280,80,80);
        botonAzul.setBackground(Color.BLUE);

        misBotones.add(botonVerde);
        misBotones.add(botonRojo);
        misBotones.add(botonAmarillo);
        misBotones.add(botonAzul);

        panel.add(botonVerde);
        panel.add(botonRojo);
        panel.add(botonAmarillo);
        panel.add(botonAzul);
    }

    private void vaciar(){
        panel.removeAll();
    }

}
