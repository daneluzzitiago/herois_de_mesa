/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Point;

/**
 *
 * @author LG
 */
public abstract class Alvo {
    
    private  String nome;
    private int hp;
    private int maxhp;
    private Point dano;
    private int bonusDano;
    private int bonusArmadura;
    
    public Alvo(){
        
    }
    
    public Alvo(String nome, int hp, int qntDados, int lados, int bonusDano, int bonusArmadura){
        this.nome = nome;
        this.hp = hp;
        this.maxhp = hp;
        this.dano = new Point(qntDados,lados);
        this.bonusDano = bonusDano;
        this.bonusArmadura = bonusArmadura;
    }
        
//    GETTERS E SETTERS
    
    public String getNome(){
        return nome;
    }
    
    public int getHp() {
        return hp;
    }
    
    public int getMaxhp() {
        return maxhp;
    }
    
    public Point getDano(){
        return dano;
    }
    
    public int getBonusDano() {
        return bonusDano;
    }

    public int getBonusArmadura() {
        return bonusArmadura;
    }
       
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp < 0){
            this.hp = 0;
        }
    }
    
    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }
    
    public void setDano(int qntDados, int lados){
        this.dano.x = qntDados;
        this.dano.y = lados;
    }
    
    public void setBonusDano(int bonusDano) {
        this.bonusDano = bonusDano;
    }

    public void setBonusArmadura(int bonusArmadura) {
        this.bonusArmadura = bonusArmadura;
    }
    
    public void ressussita(){
        
    }
  
}
