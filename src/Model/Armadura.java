/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author LG
 */
public class Armadura implements Serializable{
    private String nome;
    private int bonus;
    private int forca; 
    private String imgPath;   

    public Armadura(){
        this.nome = "";
    }
    
    public Armadura(String nome, int bonus, int forca, String imgPath) {
        this.nome = nome;
        this.bonus = bonus;
        this.forca = forca;
        this.imgPath = imgPath;
    }
    
    public String getImgPath(){
        return this.imgPath;
    }

  
    public String getNome() {
        return nome;
    }

    public int getBonus() {
        return bonus;
    }

    public int getForca() {
        return forca;
    }

}
