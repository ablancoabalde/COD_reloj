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

    static Boolean condicion = false;
    static Clip sonido;

    public static void escuchar() throws Exception {

        sonido = AudioSystem.getClip();
        File a = new File("D:\\NeatBeansProjects\\COD_reloj\\src\\sounds\\imperial_march.wav");
        sonido.open(AudioSystem.getAudioInputStream(a));
        if (condicion == false) {
            sonido.start();

        }

    }
}
