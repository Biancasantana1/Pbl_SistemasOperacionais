/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PCI;

import java.util.Random;

/**
 *
 * @author Bianca Santana e Luiz Alberto 
 */
public class Pbl_SO {
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        // TODO code application logic here
       
        Controler c = new Controler();
        
        Random gerador = new Random();
        int x ;
        String i;

            x = gerador.nextInt(3);
            i = Integer.toString(x);
            Leitura lei = new Leitura(x,c); lei.setName(i);
            lei.start();
            x = gerador.nextInt(3);
            i = Integer.toString(x);
            Escrita esc = new Escrita(x,c); esc.setName(i);
            esc.start();
        
            x = gerador.nextInt(3);
            i = Integer.toString(x);
            Leitura lei1 = new Leitura(x,c); lei1.setName(i);
            lei1.start();
            x = gerador.nextInt(3);
            i = Integer.toString(x);
            Escrita esc1 = new Escrita(x,c); esc1.setName(i);
            esc1.start();
        
            x = gerador.nextInt(3);
            i = Integer.toString(x);
            Leitura lei2 = new Leitura(x,c); lei2.setName(i);
            lei2.start();
            x = gerador.nextInt(3);
            i = Integer.toString(x);
            Escrita esc2 = new Escrita(x,c); esc2.setName(i);
            esc2.start();
        

    }

}
