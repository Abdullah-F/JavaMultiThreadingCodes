/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdullah-fadhel
 */
public class Runner {

    private Account ac1 = new Account();
    private Account ac2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void aquireLocks(Lock firstLock, Lock secondLock) {

        while (true) {
            try {
                boolean gotFirstLock  = false;
                boolean gotSecondLock  = false;
                try {
                    gotFirstLock = firstLock.tryLock();
                    gotSecondLock = secondLock.tryLock();
                } finally{
                    if (gotSecondLock && gotFirstLock) {
                        return;
                    }
                    
                    if(gotFirstLock)
                        firstLock.unlock();
                    if(gotSecondLock)
                        secondLock.unlock();
                }
                
                Thread.sleep(1000);
            }   catch (InterruptedException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void firstThresd() throws InterruptedException {

        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {

            aquireLocks(lock1, lock2);
            try {
                Account.transfer(ac1, ac2, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        }

    }

    public void secondThresd() throws InterruptedException {
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            aquireLocks(lock2, lock1);
            try {
                Account.transfer(ac2, ac1, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Acount 1 Balance  :  " + ac1.getBalance());
        System.out.println("Acount 2 Balance  :  " + ac2.getBalance());
        System.out.println("total balance     :  " + (ac1.getBalance() + ac2.getBalance()));
    }

}
