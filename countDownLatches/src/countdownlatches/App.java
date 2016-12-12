/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

class Processor implements Runnable{

    private CountDownLatch latch ;

    public Processor(CountDownLatch latch) {
    this.latch = latch;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Started ...");
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        latch.countDown();
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
            CountDownLatch latch = new CountDownLatch(3);
            ExecutorService executor = Executors.newFixedThreadPool(3);
            
            for(int i = 0 ; i < 3 ; i++)
            {
                executor.submit(new Processor(latch));
            }
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Completed !!");
    }
    
}
