import javax.swing.JFrame;

public class Ventana extends JFrame {
    public static final int WIDTH = 800, HEIGHT = 600;


    public Ventana() {
        setTitle("Ventana de prueba");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

}
