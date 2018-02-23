/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj.alarma;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author ablancoabalde
 */
public class Display {

    MTiempo mt=new MTiempo();
    static Boolean alarmON=false;

    public static void insImgSpeaker(Integer width, Integer height) {

        // Habilitar para ver imagenes en Windows
//            ImageIcon icono = new ImageIcon("D:\\NeatBeansProjects\\Maquina-Cafe\\src\\com\\vasoV.jpg");
        // Habilitar para ver imagenes en Linux
        ImageIcon icono=new ImageIcon("src/source/speaker.png");
        // Escala la imagen al tamaño de la label
        Icon icon=new ImageIcon(icono.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        Inicio.imgSpeaker.setIcon(icon);

    }

    public void insHora() {
        mt.mostrarHora();
    }

    public void AcDesAlarm() {

        if (alarmON==false) {
            Inicio.jRAlarmaOn.setSelected(true);
            mt.sonarAlarm();
            alarmON=true;
        } else {
            Inicio.jRAlarmaOn.setSelected(false);
            alarmON=false;
        }

    }

    public void verAddAlarmH() {
        Inicio.jDHora.setText(mt.addHora());

    }

    public void verAddAlarmM() {
        Inicio.jDHora.setText(mt.addMin());

    }

    public void verAlarm() {
        Inicio.jDHora.setText(mt.verAlarm());

    }
    
    

}
