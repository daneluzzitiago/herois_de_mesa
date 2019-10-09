/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author LG
 */
public class Jogador extends Alvo implements Serializable{
    private int nivel;
    private int forca;
    private int inteligencia;
    private int experiencia ;
    private int dificuldade ;
    private int maxMana;
    private int mana;
    private int posicao;
    private int tempo;
    private Arma arma;
    private Armadura armadura;
    
    public Jogador() {
        super(" ",10,1,6,0,0);
        this.nivel = 0;
        this.forca = 0;
        this.inteligencia = 0;
        this.experiencia = 0;
        this.dificuldade = 0;
        this.maxMana = 5;
        this.mana = 5;
        this.arma = new Arma();
        this.armadura = new Armadura();
    }
    
    /**
     * DEIXA O HERÓI COM VIDA MÁXIMA E RETORNA ELE AO INÍCIO DO MAPA;
     */
    @Override
    public void ressussita(){
        this.setHp(this.getMaxhp());
        this.posicao = 0 ;
        this.dificuldade = 0;
    }
    
    public int getMana(){
        return this.mana;
    }
    
    public int getMaxMana(){
        return this.maxMana;
    }
    
    public void setTempo(int t){
        this.tempo = t;
    }
    
    public int getTempo(){
        return tempo;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    
    /**
     * MÉTODOS QUE TRABALHAM COM O ATRIBUTO DIFICULDADE, O QUAL DEFINE QUÃO
     * FORTE SERÁ O MONSTRO ENFRENTADO.
     */
    
    public void aumentaDificuldade(){
        dificuldade++;
    }
    public int getDificuldade() {
        return dificuldade;
    }
    
    public void aumentaExperiencia(){
        experiencia++;
    }

    public int getExperiencia() {
        return experiencia;
    }

    

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
        
    public int getNivel() {
        return nivel;
    }

    public int getForca() {
        return forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Arma getArma() {
        return arma;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }
    
}
