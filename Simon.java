//Cambio en la Branch del Trabajo

import javax.sound.sampled.LineUnavailableException;

public class Simon {
    public static void main(String[] args) throws LineUnavailableException {
        //inicio de la ventana.
        Interfaz miInterfaz = new Interfaz();

        Logica.tocarBotones(miInterfaz.misBotones);

    }

} 