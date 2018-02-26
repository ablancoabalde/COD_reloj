/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj.alarma;

import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ablancoabalde
 */
public class MTiempo {

    Timer timer;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date;

    // Variables para la alarma
    Date Alarm;
    static String dateInString = "00:00:00";
    // Varible add hora y min alarm
    static Integer intHora = 0;
    static String hora = "00";
    static Integer intMin = 0;
    static String min = "00";

    static Integer numAumentar = 1;

    // Variable Sleep cuando ves la alarma
    static boolean USAR_SLEEP = false;

    public void mostrarHora() {
        //creamos un Timer
        timer = new Timer();
        //Con el Timer ejecutamos la tarea TicTac, con un retardo de 0sg y repetimos cada 1sg

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try {
                    // Tiempo de espera cuando llamo al boton ver alarma
                    if (USAR_SLEEP) {
                        Thread.sleep(5000);
                        USAR_SLEEP = false;
                    } else {
                        // Acción que quiero que se produzca
                        date = new Date();
                        Inicio.jDHora.setText(dateFormat.format(date));

                        // Llamada al metodo Sonar Alarma, para que vaya comparando
                        if (Display.alarmON == true) {
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

    public void sonarAlarm() {

        try {
            Alarm = dateFormat.parse(dateInString);
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

    public void addHora() {
        this.mSleep();
        intHora += 1;
        if (intHora < 10) {

            hora = "0" + String.valueOf(intHora);

        } else if (intHora < 25 && intHora >= 10) {

            hora = String.valueOf(intHora);

        } else {
            intHora = 0;
            hora = "00";

        }
        this.devTiempo();
    }

    public void addMin() {
        this.mSleep();
        intMin += numAumentar;
        if (intMin < 10) {

            min = "0" + String.valueOf(intMin);

        } else if (intMin < 60 && intMin >= 10) {

            min = String.valueOf(intMin);

        } else {
            if (numAumentar == 5) {
                min = "0" + String.valueOf(intMin - 60);
                intMin = 0;
            } else {
                intMin = 0;
                min = "00";
            }

        }
        this.devTiempo();
    }

    public String devTiempo() {
        dateInString = hora + ":" + min + ":00";
        return dateInString;

    }

    public String verAlarm() {
        this.mSleep();
        return dateInString;

    }

    public void mSleep() {
        USAR_SLEEP = true;
    }

    public void mSnooozer() {
        Sounds.sonido.close();
        numAumentar = 5;
        this.addMin();
        numAumentar = 1;

    }

}
