/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waitandnotify;

import java.util.Scanner;



/**
 *
 * @author abdullah-fadhel
 */
public class Processor {
    public void produce() throws InterruptedException
    {
     synchronized(this){
         System.out.println("producer is running ... ");
         wait(3000);
         System.out.println("Resumed");
     }   
    }
    
    public void consume() throws InterruptedException
    {
        Scanner scan = new Scanner(System.in);
        Thread.sleep(2000);
         synchronized(this){
             System.out.println("Waiting for retyrun key");
             scan.nextLine();
             System.out.println("return key pressed");
             notify();
             Thread.sleep(5000);
     }   
    }
}
