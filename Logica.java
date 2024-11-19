import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.RemoteRef;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.print.attribute.standard.RequestingUserName;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class Logica {
    //Inicio la Interfaz
    private Interfaz miInterfaz;
    //Secuencia de botones que presionara el juego po su cuenta, y se ira incrementando
    private static List<JButton> secuencia = new ArrayList<JButton>();
    //Botones presionados por el Jugador
    private static List<JButton> botonesPresionados = new ArrayList<JButton>();
    //
    private Random random = new Random();
    
    public static boolean isTurnoDelJugador = false;
    private Timer timer;
    public boolean perdio  = false;

    //Action Listener del metodo reproducir sonido, para añadir a los botones.
    public static ActionListener reproducirSonidoListener (JButton boton, double frequencia)
    {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try 
                {
                    reproducirSonido(frequencia);

                    if(isTurnoDelJugador)
                    {
                        botonesPresionados.add(boton);
                        //System.out.println("Botones presionados: "+botonesPresionados.size());
                    }

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
        double duracion = 1 / Math.log10( 10 * Math.pow( secuencia.size() , 2) ) ; // Duración de la nota, cada vez menor
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

    // Verificar si el jugador acertó la secuencia o no
    private void verificarResultadoJugador()
    {
        if (!botonesPresionados.equals(secuencia))
        {
            perdio = true;
            JOptionPane.showMessageDialog(miInterfaz.panel, "Juego terminado, Logro acertar "+(secuencia.size()-1)+" veces"); //se muestra en un mensaje el resultado del jugador
            timer.stop();
            System.exit(0); //La aplicacion termina cuando el jugador falla 
            //toDo: cuando se falla, el juego podria reiniciarse en ves de termiar
        }
        else{
            isTurnoDelJugador = false; // si el jugador acerto la secuencia, se inicia un nuevo turno del sistema y se reinicia la lista de los botones presionados
            botonesPresionados.clear();
            timer.stop();
        }
    }

    // metodo de LOGICA DE TURNOS
    public void jugarTurno() throws InterruptedException
    {

        if(!isTurnoDelJugador) // si no es turno del jugador, el sistema aumenta el tamaño de la secuencia, la reproduce y luego indica que es turno del jugador
        {
            aumentarSecuencia();
            tocarSecuencia();
            isTurnoDelJugador = true;
            
        }
        if(isTurnoDelJugador && !perdio) // si es turno del jugador, el sistema espera a que el jugador presiona una cantidad de botones igual a la secuencia, y luego vera si este input es igual a la secuencia 
        {

            timer = new Timer(100, e -> { 
                if (botonesPresionados.size() == secuencia.size()) {
                    // El jugador terminó su turno
                    verificarResultadoJugador();
                }
            });
            timer.start(); // Iniciar el temporizador

        }

        Thread.sleep(2000);

    }

    public void iniciar(){
        miInterfaz = new Interfaz();
    }

    
    

    


}
