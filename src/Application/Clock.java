package Application;



import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author overl
 */
public class Clock extends JLabel implements Runnable{

    @Override
    public void run() {
        while(true){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy : HH:mm:ss");
            Date date = new Date();
            this.setText(sdf.format(date));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    
}
