import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JButton;


public class Logica {
    //Inicio la Interfaz
    private Interfaz miInterfaz = new Interfaz();
    //Secuencia de botones que presionara el juego po su cuenta, y se ira incrementando
    private List<JButton> secuencia = new ArrayList<JButton>();
    //Botones presionados por el Jugador
    private List<JButton> botonesPresionados = new ArrayList<JButton>();
    //
    private Random random = new Random();
    
    public static int turno = 1;
    public boolean isTurnoDelJugador = false;

    //Action Listener del metodo reproducir sonido, para añadir a los botones.
    public static ActionListener reproducirSonidoListener (double frequencia){

        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try 
                {
                    reproducirSonido(frequencia);

                } catch (LineUnavailableException e1) 
                {
                    e1.printStackTrace();
                }
            };
        };

    }

    //METODOS
    //metodo para reproducir sonido
    private static void reproducirSonido( double frequencia) throws LineUnavailableException
    {
        float sampleRate = 44100; // Frecuencia de muestreo en Hz
        double duracion = 1 / Math.log10( 10 * Math.pow(turno, 2) ) ; // Duración de la nota, cada vez menor
        // Configuración del formato de audio
        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
        byte[] buffer = new byte[(int) (sampleRate * duracion)];

        // Generar la onda sinusoidal
        for (int i = 0; i < buffer.length; i++) {
            double angle = 2.0 * Math.PI * i * frequencia / sampleRate;
            buffer[i] = (byte) (Math.sin(angle) * 127); // Valor entre -127 y 127
        }

        // Reproducción del tono
        SourceDataLine line = AudioSystem.getSourceDataLine(format);
        line.open(format);
        line.start();
        line.write(buffer, 0, buffer.length);
        line.drain();
        line.close();
    }

    //metodos para accionar sobre la secuencia de botones
    private void aumentarSecuencia()
    {
        int numeroRandom = random.nextInt(4);
        JButton botonRandom = miInterfaz.misBotones[numeroRandom];
        secuencia.add(botonRandom);
    }

    private void tocarSecuencia() throws InterruptedException
    {
        if (!secuencia.isEmpty())
        {
            for (JButton boton : secuencia){
                boton.doClick();
                Thread.sleep(100); //espera necsaria para que la visual de los botones no se superponga
            }
        }
    }

    //metodo de logica de turnos
    public void jugarTurno() throws InterruptedException
    {

        if(!isTurnoDelJugador)
        {
            aumentarSecuencia();
            tocarSecuencia();
            
        }
        if(isTurnoDelJugador)
        {

            while (botonesPresionados.size() != secuencia.size())
            {

            }

        }

        isTurnoDelJugador = !isTurnoDelJugador;
        nuevoTurno();
        Thread.sleep(2000);

    }



    public void nuevoTurno(){
        turno++;
    }
    

    


}
