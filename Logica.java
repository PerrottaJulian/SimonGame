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
    private Interfaz miInterfaz = new Interfaz();
    public static int turno = 1;
    public static boolean isTurnoDelJugador = false;

    private static List<JButton> secuencia = new ArrayList<JButton>();
    private static Random random = new Random();
    
    
    public static ActionListener reproducirSonidoListener (double frequencia){

        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    reproducirSonido(frequencia);
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                }
            };
        };

    }

    private static void reproducirSonido( double frequencia) throws LineUnavailableException
    {
        float sampleRate = 44100; // Frecuencia de muestreo en Hz
        double duracion = 1 / Math.log10( 10 * Math.pow(turno, 2) ) ; // Duración de la nota en segundos
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

    /*public static void tocarBotonesAutomatico(JButton[] listaBotones ) throws InterruptedException{
        
        if (!botonesPresionados.isEmpty())
        {
            for (JButton boton : botonesPresionados) 
            {
                boton.doClick();
            }
        }

        int numeroRandom = random.nextInt(4);
        JButton botonRandom = listaBotones[numeroRandom];
        botonRandom.doClick();
        botonesPresionados.add(botonRandom);

        nuevoTurno();
        Thread.sleep(2000);

    } */

    private void agregarASecuencia()
    {
        int numeroRandom = random.nextInt(4);
        JButton botonRandom = miInterfaz.misBotones[numeroRandom];
        secuencia.add(botonRandom);
    }

    private void tocarSecuencia()
    {
        if (!secuencia.isEmpty())
        {
            for (JButton boton : secuencia){
                boton.doClick();
            }
        }
    }


    public static void nuevoTurno(){
        turno++;
    }
    

    


}
