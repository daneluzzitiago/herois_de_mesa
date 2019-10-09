/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LG
 */
public class Timer extends Thread{
    
    private int segundos;
    private int minutos;
    private int horas;
    private String time;
    private boolean running;
    
    public Timer(){
        this.segundos = 0;
        this.minutos = 0;
        this.horas = 0;
        this.time = "00:00:00";
        this.running = true;
    }
    
    @Override
    public void run(){
        while(running){
            try {
                Thread.sleep(1000);
                segundos++;
                if (segundos == 60){
                    segundos = 0;
                    minutos++;
                    if (minutos == 60){
                        minutos = 0;
                        horas++;
                    }
                }
                clock();
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
    }
    
    /**
     * CONVERTE OS INTS DE HORA MINUTO E SEGUNDO NUMA STRING MAIS BONITA
     */
    
    public void clock(){
        DecimalFormat s = new DecimalFormat("00");
        DecimalFormat m = new DecimalFormat("00");
        DecimalFormat h = new DecimalFormat("00");
        time = (h.format(horas)+":"+h.format(minutos)+":"+h.format(segundos));
    }
    
    /**
     * DEFINE HORA MINUTO E SEGUNDO A PARTIR DE UM INT
     * @param total VALOR DO TEMPO EM SEGUNDOS
     */
    
    public void setTime(int total){
        horas = total/3600;
        minutos = (total%3600)/60;
        segundos = total%60;
    }
    
    /**
     * CALCULA QUANTOS SEGUNDOS JÁ FORAM PASSADOS
     * @return HORAS + MINUTOS + SEGUNDOS TUDO EM SEGUNDOS
     */
    
    public int getTotalSeconds(){
        return ( segundos + (minutos*60) + (horas*3600));
    }
    
    /**
     * DEFINE O ESTADO DA BOOLEANA QUE CONTROLA A THREAD
     * @param bol 
     */
    
    public void setRunning(boolean bol){
        this.running = bol;
    }
    
    /**
     * RETORNA O TEMPO TOTAL EM FORMATO DE STRING
     * @return 
     */
    
    public String getTime(){
        return time;
    }

}
