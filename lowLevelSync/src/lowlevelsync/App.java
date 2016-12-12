/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lowlevelsync;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdullah-fadhel
 */
public class App {

    public Processor processor= new Processor();
    private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(10);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.processor.produce();
                } catch (InterruptedException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.processor.consume();
                } catch (InterruptedException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
         t1.start();
         t2.start();
        try {
            t1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void producer() throws InterruptedException
    {
        Random random = new Random();
        while (true) {            
            blockingQueue.put(random.nextInt(100));
            System.out.println("added  " +blockingQueue.size() );
        }
    }
    
    private void consumer() throws InterruptedException
    {
        Random random = new Random();
        while (true) {            
            Thread.sleep(100);
            if(random.nextInt(10) == 0)
            {
              Integer value =  blockingQueue.take();
                System.out.println("taken Value : " + value + " queueSize  : " + blockingQueue.size());
            }
        }
    }
    
}
