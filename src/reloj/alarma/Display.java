package reloj.alarma;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import static reloj.alarma.Inicio.*;

/**
 * Clase en la que se muestran los metodos de visualización de la aplicación
 *
 * @author ablancoabalde
 */
public class Display {

    /**
     * Variable estatica que nos sirve, para controlar si la alarma se activa o
     * se desactiva
     */
    static Boolean alarmON=false;

    /**
     * Inserta la imagen en una etiqueta label y la redimensiona sabiendo el
     * tamaño de la etiqueta label
     *
     * @param width
     * @param height
     */
    public static void insImgSpeaker(Integer width, Integer height) {

        // Habilitar para ver imagenes en Windows
        //     ImageIcon icono = new ImageIcon("D:\\NeatBeansProjects\\COD_reloj\\src\\source\\speaker.png");
        ImageIcon icono=new ImageIcon("/home/local/DANIELCASTELAO/ablancoabalde/NetBeansProjects/COD/COD_reloj/src/source/speaker.png");

        // Habilitar para ver imagenes en Linux
        Icon icon=new ImageIcon(icono.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        imgSpeaker.setIcon(icon);

    }

    /**
     * Metodo que hace la llamada de mostrar la hora
     */
    public static void insHora() {
        MTiempo.mostrarHora();
    }

    /**
     * Metodo que activa y desactiva la alarma, marcando y desmarcando un
     * RadioButton. Si está activa y llega a la hora de la alarma hace sonar la
     * alarma y si pulsando cuando esté sonando apaga la musica
     */
    public static void AcDesAlarm() {

        if (alarmON==false) {
            jRAlarmaOn.setSelected(true);
            jBposponer.setEnabled(true);
            MTiempo.sonarAlarm();
            alarmON=true;
        } else {
            jRAlarmaOn.setSelected(false);
            jBposponer.setEnabled(false);
            if (Sounds.sSuena==true) {
                Sounds.sonido.close();
            }
            alarmON=false;
        }

    }

    /**
     * LLama al metodo que agrega horas e inserta el resultado en un TextField
     */
    public static void verAddAlarmH() {
        MTiempo.addHora();
        jDHora.setText(MTiempo.devTiempo());

    }

    /**
     * LLama al metodo que agrega minutos e inserta el resultado en un TextField
     */
    public static void verAddAlarmM() {
        MTiempo.addMin();
        jDHora.setText(MTiempo.devTiempo());

    }

    /**
     * Muestra a que hora a sido programada la alarma
     */
    public static void verAlarm() {

        jDHora.setText(MTiempo.verAlarm());

    }

    /**
     * Metodo que cambia el texto del boton Jbverhora, para motrar Alarma cuando
     * estés en modo Alarma y Hora cuando estes en modo Hora
     *
     * @param opciones Son opciones que reciben para meter un texto u otro
     */
    public static void cambiarTextHA(Integer opciones) {

        switch (opciones) {
            case 1:
                jBverhora.setText("Hora");
                break;
            case 2:
                jBverhora.setText("Alarma");
                break;
        }

    }

}
