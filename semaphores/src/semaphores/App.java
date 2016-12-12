/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author abdullah-fadhel
 */
public class App {
  
   
    public static void main(String[] args) throws InterruptedException {
      Connection.getInstance().connect();
        
        
        ExecutorService ecexutor = Executors.newCachedThreadPool();
     
        for (int i = 0; i < 200; i++) {
            ecexutor.submit(new Runnable() {
                @Override
                public void run() {
              Connection.getInstance().connect();
                }
            });
        }
        ecexutor.shutdown();
        ecexutor.awaitTermination(1, TimeUnit.DAYS);
    }
}
