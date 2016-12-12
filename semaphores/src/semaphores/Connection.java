/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphores;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdullah-fadhel
 */
public class Connection {

    private static Connection instance = new Connection();
    private int connections = 0;
    private Semaphore sem = new Semaphore(10);

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {

        try {
            sem.acquire();
            doconnect();
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sem.release();
        }
    }

    public void doconnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current Connections : " + connections);

        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        synchronized (this) {
            connections--;
        }

    }
}
