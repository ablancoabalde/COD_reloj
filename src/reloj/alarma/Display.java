package reloj.alarma;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Clase en la que se muestran los metodos de visualización de la aplicación
 *
 * @author ablancoabalde
 */
public class Display {

    /**
     * Inicialización de la clase Metodos Tiempo y una variable estatica que nos
     * servirá, para controlar si la alarma se activa o se desactiva
     */
    MTiempo mt = new MTiempo();
    static Boolean alarmON = false;

    /**
     * Inserta la imagen en una etiqueta label y la redimensiona sabiendo el
     * tamaño de la etiqueta label
     *
     * @param width
     * @param height
     */
    public static void insImgSpeaker(Integer width, Integer height) {

        // Habilitar para ver imagenes en Windows
        ImageIcon icono = new ImageIcon("D:\\NeatBeansProjects\\COD_reloj\\src\\source\\speaker.png");
        // Habilitar para ver imagenes en Linux
        //       ImageIcon icono=new ImageIcon("src/source/speaker.png");
        // Escala la imagen al tamaño de la label
        Icon icon = new ImageIcon(icono.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        Inicio.imgSpeaker.setIcon(icon);

    }

    /**
     * Metodo que hace la llamada de mostrar la hora
     */
    public void insHora() {
        mt.mostrarHora();
    }

    /**
     * Metodo que activa y desactiva la alarma, marcando y desmarcando un
     * RadioButton. Si está activa y llega a la hora de la alarma hace sonar la
     * alarma y si pulsando cuando esté sonando apaga la musica
     */
    public void AcDesAlarm() {

        if (alarmON == false) {
            Inicio.jRAlarmaOn.setSelected(true);
            mt.sonarAlarm();
            alarmON = true;
        } else {
            Inicio.jRAlarmaOn.setSelected(false);
            Sounds.sonido.close();
            alarmON = false;
        }

    }

    /**
     * LLama al metodo que agrega horas e inserta el resultado en un TextField
     */
    public void verAddAlarmH() {
        mt.addHora();
        Inicio.jDHora.setText(mt.devTiempo());

    }

    /**
     * LLama al metodo que agrega minutos e inserta el resultado en un TextField
     */
    public void verAddAlarmM() {
        mt.addMin();
        Inicio.jDHora.setText(mt.devTiempo());

    }

    /**
     * Muestra a que hora a sido programada la alarma
     */
    public void verAlarm() {
        Inicio.jDHora.setText(mt.verAlarm());

    }

}
