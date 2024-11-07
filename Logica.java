import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Logica {
    
    public static void ReproducirSonido(/* double frequencia */) throws LineUnavailableException
    {
        float sampleRate = 44100; // Frecuencia de muestreo en Hz
        double duration = 0.5; // Duración de la nota en segundos
        double frequency = 554.365; // Frecuencia de la nota mi (E4)

        // Configuración del formato de audio
        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
        byte[] buffer = new byte[(int) (sampleRate * duration)];

        // Generar la onda sinusoidal
        for (int i = 0; i < buffer.length; i++) {
            double angle = 2.0 * Math.PI * i * frequency / sampleRate;
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

}
