/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lowlevelsync;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author abdullah-fadhel
 */
public class Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int limit = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                System.out.println("Added");
                while (list.size() == limit) {                    
                    lock.wait();
                }
                list.add(value);
                lock.notify();
            }
            value++;
        }
    }

    public void consume() throws InterruptedException {
        int value = 0;
        Random rand = new Random();
        while (true) {
            System.out.println("list size is : " + list.size());
            synchronized (lock) {
                while (list.size() == 0) {                    
                    lock.wait();
                }
            value = list.removeFirst();
            lock.notify();
            
            }
            System.out.println("value  is  : " + value);
        Thread.sleep(1000);
        }
    }

}
