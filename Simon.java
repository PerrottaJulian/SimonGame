//Cambio en la Branch del Trabajo
public class Simon
{
    public static void main(String[] args) throws InterruptedException 
    {
        //inicio de la ventana.
        //!codigo viejo
        /*
        Logica miJuego = new Logica();

        for (int i = 0; i < 5; i++)
        {
        }*/

        //toDo: Agregar en Logica funcion turno
        //toDo: Agregar new Logica()

        new Logica();

        for (int i = 0; i < 5; i++) {
            System.out.println("Turno Numero: "+Logica.turno);
            System.out.println("Juega el jugador? "+Logica.isTurnoDelJugador);

            Logica.jugarTurno();
        }

    }

}
