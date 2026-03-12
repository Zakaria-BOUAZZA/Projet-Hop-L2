import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Son {

    public static void lanceur(){
        try{
            File fichier = new File("Wasted.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(fichier);
            AudioFormat format = audio.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip c = (Clip) AudioSystem.getLine(info);
            c.open(audio);
            c.start();
        }catch(UnsupportedAudioFileException e){
            System.err.println("Format du fichier non supporté !");
        }catch(LineUnavailableException e){
            System.err.println("Une ligne audio est indisponible !");
        }catch(IOException e){
            System.err.println("Erreur lors de la lecture du fichier audio !");
        }
    }
}