/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock;

/**
 *
 * @author abdullah-fadhel
 */
public class Account {
    private int balance = 10000;
    
    
    
    public void deposite(int amount)
    {
        balance += amount;
    }
    
    public void withDraw(int amount)
    {
        balance-= amount;
    }

    public int getBalance() {
        return balance;
    }
    
    public static  void transfer(Account ac1 , Account ac2 , int amount)
    {
        ac1.withDraw(amount);
        ac2.deposite(amount);
    }
}
