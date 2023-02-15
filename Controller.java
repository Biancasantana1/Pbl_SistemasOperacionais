/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PCI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 *
 * @author Bianca Santana e Luiz Alberto 
 */
public class Controler {
    
    private boolean estaExecutando = false;
    
    /**
     * Esse método realiza a leitura no arquivo de texto e está sendo sincronizado com o método de 
     * escrita, este método deverá ser executado antes do método de escrita.
     * @param nomeArquivo
     */
    public  synchronized void leitura(int nomeArquivo){
        while (estaExecutando == true) { // enquanto a variável estaExecutando for verdadeira este método terá que esperar o método de escrita
            try {                  // terminar de executar, para poder ser executado
                wait(); // modo de espera
            }catch (InterruptedException e) { 
            }
        }    
        System.out.println("--- Entrei em leitura ---       " + "Arquivo:" + Thread.currentThread().getName());
        try{ 
            FileInputStream file = new FileInputStream(nomeArquivo+".txt");
            InputStreamReader input = new InputStreamReader(file, "ISO-8859-1");
            BufferedReader br = new BufferedReader(input);
            String linha ="";
            while(true){
                if(linha!=null){ 
                    System.out.println(linha); // printando cada linha do arquivo de texto
                }
                else
                    break;
                linha = br.readLine();   
            }
            br.close();
            file.close();
            input.close();
        }catch (Exception e){
        }
        System.out.println("*** Passei por leitura ***      " + "Arquivo:" + Thread.currentThread().getName()+ "\n"); 
        estaExecutando = true; // modificando a variável estaExecutando para liberar a execução do método escrita
        notifyAll();
    }  
    
    /**
     * Esse método realiza a escrita no arquivo de texto pegando as entradas do teclado, ele está sendo sincronizado com o método
     * de leitura, além disto, deverá ser executado após o método de leitura.
     * @param nomeArquivo
     */
    public synchronized void escrita(int nomeArquivo ){    
        while (estaExecutando == false) { // enquanto a variável estaExecutando for falsa este método terá que esperar o método de leitura
            try {                   // terminar de executar, para poder ser executado
                wait(); // modo de espera
            } catch (InterruptedException e) { }
        }  
        System.out.println("--- Entrei em Escrita ---       " + "Arquivo:" + Thread.currentThread().getName());
        try{
            FileWriter fw = new FileWriter( nomeArquivo+".txt", true );
            BufferedWriter bw = new BufferedWriter(fw);
            String linha ="";
            Scanner teclado = new Scanner(System.in);
            System.out.println("Digite ...");
            linha = teclado.nextLine(); // ler a entrada do teclado
            bw.append("\n"+linha); // escreve no arquivo de texto
            bw.close();
            fw.close();
        }catch (Exception e){
        }
        System.out.println("*** Passei por Escrita ***      " + "Arquivo:" + Thread.currentThread().getName() + "\n");
        estaExecutando = false; // modificando a variável estaExecutando para liberar a execução do método leitura
        notifyAll();
        System.out.println("--- Entrei em atualizar ---");
        att(nomeArquivo); // chama o método de atualizar os arquivos
        System.out.println("*** Passei Atualizar ***");
    }
    
    /**
     * Para execução deste método é necessário a execução do método de escrita, pois para atualizar um arquivo precisamos
     * saber em qual arquivo o método escrita está escrevendo para identificar quais arquivos devemos atualizar, 
     * portanto, este método está sendo chamado dentro do método de escrita
     * @param nomeArquivo
     */
    public void att(int nomeArquivo){
        switch(nomeArquivo){
            case 0:
                this.ler_escrever(nomeArquivo, 1);
                this.ler_escrever(nomeArquivo, 2);
            break;
            
            case 1:
                this.ler_escrever(nomeArquivo, 0);
                this.ler_escrever(nomeArquivo, 2);
            break;
            
            case 2:
                this.ler_escrever(nomeArquivo, 0);
                this.ler_escrever(nomeArquivo, 1);
            break;
        }
    }
    
    /**
     *
     * @param nomeArquivo
     * @param x
     */
    public  void ler_escrever(int nomeArquivo, int x){
        //Path caminho = Paths.get(nomeArquivo+".txt"); 
        try{
//            byte[] texto = Files.readAllBytes(caminho);
//            String leitura = new String(texto);
            Files.copy(Paths.get(nomeArquivo+".txt"), Paths.get(x+".txt"),StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e){    
        }
    }
    
}
