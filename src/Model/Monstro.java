/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author LG
 */
public class Monstro extends Alvo{
    
    private final String imgPath;

    public Monstro(String nome, String imgPath, int hp, int qntDados, int lados, int bDano, int bArmadura) {
        super(nome,hp,qntDados,lados,bDano,bArmadura);
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    /**
     * REVIVE O MONSTRO, EVITANDO QUE NUMA PRÓXIMA BATALHA ELE APAREÇA COM MENOS
     * HP QUE O NORMAL.
     */
    
    @Override
    public void ressussita(){
        this.setHp(this.getMaxhp());
    }
}
