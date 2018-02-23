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

/**
 *
 * @author ablancoabalde
 */
public class MTiempo {

    Timer timer;

    SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
    Date date;

    // Variables para la alarma
    Date Alarm;
    static String dateInString="00:00:00";
    // Varible add hora y min alarm
    Integer intHora=0;
    String hora="00";
    Integer intMin=0;
    String min="00";

    public void mostrarHora() {
        //creamos un Timer
        timer=new Timer();
        //Con el Timer ejecutamos la tarea TicTac, con un retardo de 0sg y repetimos cada 1sg

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                // Acción que quiero que se produzca
                date=new Date();
                Inicio.jDHora.setText(dateFormat.format(date));

                // Llamada al metodo Sonar Alarma, para que vaya comparando
                sonarAlarm();
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public void sonarAlarm() {
        // Valor String que se le va a pasar, para alarma

        try {
            Alarm=dateFormat.parse(dateInString);
            if (dateFormat.format(date).equalsIgnoreCase(dateFormat.format(Alarm))) {
                System.out.println("hola");
            }
        } catch (ParseException ex) {
            Logger.getLogger(MTiempo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String addHora() {

        if (intHora<9) {
            intHora+=1;
            hora=String.valueOf(intHora);
            return dateInString="0"+hora+":"+min+":00";
        } else if (intHora<24&&intHora>=9) {
            intHora+=1;
            hora=String.valueOf(intHora);
            return dateInString=hora+":"+min+":00";
        } else {
            intHora=0;
            hora=String.valueOf(intHora);
            return dateInString=hora+":"+min+":00";
        }
    }

    public String addMin() {

        if (intMin<9) {
            intMin+=1;
            min=String.valueOf(intMin);
            return dateInString=hora+":0"+min+":00";
        } else if (intMin<60&&intMin>=9) {
            intMin+=1;
            min=String.valueOf(intMin);
            return dateInString=hora+":"+min+":00";
        } else {
            intMin=0;
            min=String.valueOf(intMin);
            return dateInString=hora+":0"+min+":00";
        }
    }

}
