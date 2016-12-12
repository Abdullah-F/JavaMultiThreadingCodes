/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

class Processor implements Runnable{
    private int id ;
    public Processor(int id)
    {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting :  " + id);
            Thread.sleep(5000);
            System.out.println("Finishing :  " + id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            ExecutorService executor = Executors.newFixedThreadPool(3);
            for(int i = 0 ; i < 5 ; i++)
            {
                executor.submit(new Processor(i));
            }
            System.out.println("All Tasks Submited");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("All Tasks Completed");
    }
    
}
