/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PCI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bianca Santana e Luiz Alberto 
 */
public class Escrita extends Thread{

    private int num_aleatorio;
    private Controler e;
    
    public Escrita(int x, Controler y ){
        e = y;
        this.num_aleatorio = x;
    }

    @Override
    public void run(){ // método de execução da thread
        e.escrita(this.num_aleatorio); 
        try {
            sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Leitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
