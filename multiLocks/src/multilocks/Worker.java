/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdullah-fadhel
 */
public class Worker {

    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private Random random = new Random();
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();

    public void stageOne() {

        synchronized(lock1){
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        list1.add(random.nextInt(100));    
        }
        
    }

    public  void stageTwo() {

        synchronized(lock2){
         try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        list2.add(random.nextInt(100));
        }
    }

    public  void  process()
    {
        for(int i =  0 ; i < 1000 ; i++)
        {
            stageOne();
            stageTwo();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Worker work = new Worker();
            System.out.println("starting .....");
            long start = System.currentTimeMillis();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    work.process();
                }
            });
             Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    work.process();
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            long end = System.currentTimeMillis();
            
            System.out.println("time is : " + (end-start));
            System.out.println("List1 : " + work.list1.size());
            System.out.println("List2 : " + work.list2.size());
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
