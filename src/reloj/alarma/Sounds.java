/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj.alarma;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {

    // Variable que permite ser llamada en otras clases y así poder parar la alarma
    static Clip sonido;

    /**
     * Metodo que carga un sonido almacena en la carpeta Sounds del programa y
     * lo pone a sonar
     *
     * @throws Exception
     */
    public static void escuchar() throws Exception {

        sonido = AudioSystem.getClip();
        // Windows
        File a = new File("D:\\NeatBeansProjects\\COD_reloj\\src\\sounds\\imperial_march.wav");
        // Linux
//      File a = new File("D:\\NeatBeansProjects\\COD_reloj\\src\\sounds\\imperial_march.wav");
        sonido.open(AudioSystem.getAudioInputStream(a));
        sonido.start();

    }
}
