/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PCI;


import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bianca Santana e Luiz Alberto 
 */
public class Leitura extends Thread {
    private int num_aleatorio;
    private Controler l;
    
    public Leitura(int x,Controler y ){
        l = y;
        this.num_aleatorio = x;
        
    }
  
    
    @Override
    public void run(){ // método de execução da thread
        l.leitura(this.num_aleatorio); 
        try {
            sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Leitura.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }      
              
}
