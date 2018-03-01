/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj.alarma;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static reloj.alarma.Inicio.*;

/**
 *
 * @author ablancoabalde
 */
public class MTiempo {

    /**
     * Variables para hacer funcionar la hora del sistema
     */
    static Timer timer;
    static SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
    static Date date;

    // Variables para la alarma
    static Date Alarm;
    static String dateInString="00:00:00";
    // Varible add hora y min alarm
    static Integer intHora=0;
    static String hora="00";
    static Integer intMin=0;
    static String min="00";
    // Variable para alterna el aumento de los minutos, si es para mover los minutos, van de 1 en 1, pero si pulsamos Snooer aumenta 5
    static Integer numAumentar=1;

    // Variable Sleep cuando ves la alarma
    static boolean USAR_SLEEP=false;

    /**
     * Metodo que muestra la Hora del sistema y lo repite cada segundo, a no ser
     * que se cumpla la condición y lo duerma 5 segundos. También analiza si la
     * alarma está activa y si es así hace llama al metodo que hace sonar la
     * Alarma
     */
    public static void mostrarHora() {
        //creamos un Timer
        timer=new Timer();
        //Con el Timer ejecutamos la tarea TicTac, con un retardo de 0sg y repetimos cada 1sg

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {

                try {
                    // Tiempo de espera cuando llamo al boton ver alarma,addHora y addMin
                    if (USAR_SLEEP) {
                        Thread.sleep(5000);
                        USAR_SLEEP=false;
                        // Llamada al metodo para cambiar
                        //texto del botón Hora/ Alarma
                        Display.cambiarTextHA(1);
                    } else {
                        // Acción que quiero que se produzca
                        date=new Date();
                        jDHora.setText(dateFormat.format(date));

                        // Llamada al metodo Sonar Alarma, para que vaya comparando
                        if (Display.alarmON==true) {
                            sonarAlarm();
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MTiempo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * Metodo que hace sonar la alarma si la hora del sistema es igual a la
     * alarma introducida
     */
    public static void sonarAlarm() {

        try {
            Alarm=dateFormat.parse(dateInString);
            if (dateFormat.format(date).equalsIgnoreCase(dateFormat.format(Alarm))) {

                //Implementar el sonido del reloj
                Sounds.escuchar();

            }
        } catch (ParseException ex) {
            Logger.getLogger(MTiempo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MTiempo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo que agrega 1 hora a la alarma y la formatea para que su
     * visualizado sea correcto
     */
    public static void addHora() {
        mSleep();
        intHora+=1;
        if (intHora<10) {

            hora="0"+String.valueOf(intHora);

        } else if (intHora<25&&intHora>=10) {

            hora=String.valueOf(intHora);

        } else {
            intHora=0;
            hora="00";

        }
        devTiempo();
    }

    /**
     * Metodo que agrega según convenga 1 o 5 min a la alarma y la formatea para
     * que su visualizado sea correcto. Agregará 5 minutos, cuando pulsemos el
     * botón Snoozer
     */
    public static void addMin() {
        mSleep();
        intMin+=numAumentar;
        if (intMin<10) {

            min="0"+String.valueOf(intMin);

        } else if (intMin<60&&intMin>=10) {

            min=String.valueOf(intMin);

        } else {
            if (numAumentar==5) {
                min="0"+String.valueOf(intMin-60);
                intMin=0;
            } else {
                intMin=0;
                min="00";
            }

        }
        devTiempo();
    }

    /**
     * Metodo que devuelve un string con la hora de la alarma
     *
     * @return String
     */
    public static String devTiempo() {
        dateInString=hora+":"+min+":00";
        return dateInString;
    }

    /**
     * Metodo que devuelve el valor de la hora de la alarma, para luego
     * visualizarla
     *
     * @return String
     */
    public static String verAlarm() {
        mSleep();
        return dateInString;

    }

    /**
     * Metodo que cambia la variable, para poner a dormir el metodo mostrarHora
     */
    public static void mSleep() {
        // Llamada al metodo para cambiar
        //texto del botón Hora/ Alarma
        Display.cambiarTextHA(2);
        USAR_SLEEP=true;
    }

    /**
     * Metodo con varias funciones, si está sonando la alarma, le quita el
     * sonido y pospone la alarma para que vuelva a sonar en 5 min.
     */
    public static void mSnooozer() {
        if (Sounds.sSuena==true) {
            Sounds.sonido.close();
        }
        numAumentar=5;
        addMin();
        numAumentar=1;

    }

}
