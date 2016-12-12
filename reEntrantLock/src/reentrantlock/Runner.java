/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reentrantlock;


import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author abdullah-fadhel
 */
public class Runner {
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private int count = 0;
    
    private void increment() throws Exception
    {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        throw new Exception();
    }
    public  void firstThresd() throws InterruptedException
    {
        lock.lock();
        System.out.println("Waiting...");
        cond.await();
        System.out.println("Woken Up 1 ");
        try {
        increment();    
        } catch (Exception e) {
        lock.unlock();
        }
        
        
    }
    
    
    public  void secondThresd() throws InterruptedException
    {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("press Enter Key ..") ;
        new Scanner(System.in).nextLine();
        System.out.println("Got Return Key !!  ...");
        cond.signal();
        try {
        increment();    
        } catch (Exception e) {
        lock.unlock();
        }
    }
    
    
    public  void finished()
    {
        System.out.println("count is :   " +   count);
    }
    

}
