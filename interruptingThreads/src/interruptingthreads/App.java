/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interruptingthreads;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdullah-fadhel
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting ....");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
          
                Random rand = new Random();
                for (int i = 0; i < 1E8; i++) {
                    
                   /* if(Thread.currentThread().isInterrupted())
                    {
                        System.out.println("interrupted !!");
                        break;
                    }*/
                    Math.sin(rand.nextDouble());
                
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                         System.out.println("interrupted !!");
                         break;
                    }
                }
            }
        });
        t1.start();
        t1.interrupt();
        try {
            t1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finished ....");
    }
    
}
