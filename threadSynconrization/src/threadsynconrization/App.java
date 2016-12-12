/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsynconrization;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class processor extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {

        while (running) {
            System.out.println("Heloo");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(processor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public  void shutDown()
    {
        running = false;
    }

}

/**
 *
 * @author abdullah-fadhel
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        processor pro = new processor();
        pro.start();
        
        System.out.println("print return to stop..");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        pro.shutDown();
    }

}
