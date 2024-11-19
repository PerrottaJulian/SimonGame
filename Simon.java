//Cambio en la Branch del Trabajo

import javax.sound.sampled.LineUnavailableException;

public class Simon
{
    public static void main(String[] args) throws InterruptedException, LineUnavailableException 
    {
        //inicio del juego
        Logica miJuego = new Logica();
        miJuego.iniciar();
        
        while (true) 
        {
            miJuego.jugarTurno();
        }

    }

}
