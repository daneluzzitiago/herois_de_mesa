/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author LG
 */
public class Armaria {
    private ArrayList<Armadura> armadura;
    private ArrayList<Arma> arma;
    
    public Armaria(){
        this.armadura = new ArrayList<Armadura>();
        this.arma = new ArrayList<Arma>();
        criaItens();
    }
    
    /**
     * ADICIONA UMA SÉRIE DE ARMAS E ARMADURAS QUE SERÃO REPRESENTADAS COMO CARTAS
     */
    
    public void criaItens(){
        armadura.add(new Armadura("Camisa",1,1,"imagens/camisa.jpg"));
        armadura.add(new Armadura("Couro Acolchoado",2,2,"imagens/couroacolchoado.jpg"));
        armadura.add(new Armadura("Couro",3,3,"imagens/couro.jpg"));
        armadura.add(new Armadura("Couro Duro",4,4,"imagens/couroduro.jpg"));
        armadura.add(new Armadura("Couro Cravejado",5,5,"imagens/courocravejado.jpg"));
        armadura.add(new Armadura("Armadura de Anéis",6,6,"imagens/armaduradeaneis.jpg"));
        armadura.add(new Armadura("Armadura de Escamas",7,7,"imagens/armaduradeescamas.jpg"));
        armadura.add(new Armadura("Armadura de Cavaleiro",8,8,"imagens/armaduradecavaleiro.jpg"));
        armadura.add(new Armadura("Armadura de Paladino",9,9,"imagens/armaduradepaladin.jpg"));
        armadura.add(new Armadura("Placas de Ferro",10,10,"imagens/placasdeferro.jpg"));
        armadura.add(new Armadura("Capa",3,1,"imagens/capa.jpg"));
        armadura.add(new Armadura("Manto",4,2,"imagens/manto.jpg"));
        armadura.add(new Armadura("Armadura Escarlate",5,3,"imagens/armaduraescarlate.jpg"));
        armadura.add(new Armadura("Placas de Bronze",6,4,"imagens/armaduradebronze.jpg"));
        armadura.add(new Armadura("Placas de Ouro",7,5,"imagens/armaduradeouro.jpg"));
        armadura.add(new Armadura("Escamas de Dragão",8,6,"imagens/escamasdedragao.jpg"));
        armadura.add(new Armadura("Armadura Templária",9,7,"imagens/armaduratemplaria.jpg"));
        arma.add(new Arma("Adaga",1,1,"imagens/adaga.jpg"));
        arma.add(new Arma("Taco",2,2,"imagens/taco.jpg"));
        arma.add(new Arma("Espada Curta",3,3,"imagens/espadacurta.jpg"));
        arma.add(new Arma("Machado de Mão",4,4,"imagens/machadodemao.jpg"));
        arma.add(new Arma("Cimitarra",5,5,"imagens/cimitarra.jpg"));
        arma.add(new Arma("Machado",6,6,"imagens/machado.jpg"));
        arma.add(new Arma("Taco Espinhoso",7,7,"imagens/tacoespinhoso.jpg"));
        arma.add(new Arma("Sabre",8,8,"imagens/sabre.jpg"));
        arma.add(new Arma("Machado Duplo",9,9,"imagens/machadoduplo.jpg"));
        arma.add(new Arma("Martelo de Guerra",10,10,"imagens/martelodeguerra.jpg"));
        arma.add(new Arma("Cajado",2,1,"imagens/cajado.jpg"));
        arma.add(new Arma("Espada de Cristal",3,2,"imagens/espadadecristal.jpg"));
        arma.add(new Arma("Lança",4,3,"imagens/lanca.jpg"));
        arma.add(new Arma("Cetro",5,4,"imagens/cetro.jpg"));
        arma.add(new Arma("Cajado encantado",7,5,"imagens/cetroencantado.jpg"));
        arma.add(new Arma("Espada de Guerra",9,6,"imagens/espadadeguerra.jpg"));
        arma.add(new Arma("Machado de Guerra",10,7,"imagens/machadodeguerra.jpg"));
    }
    
    /**
     * RETORNA UMA ARMA ALEATÓRIA COM MÁXIMO DE FORÇA BASEADO NO PARÂMETRO
     * @param forca
     * @return 
     */
    
    public Arma lootArma(int forca){
        Random random = new Random();
        double sorte = arma.size() * random.nextDouble();
        if (arma.get((int)sorte).getForca() <= forca){
            return arma.get((int)sorte);
        }
        return lootArma(forca);
    }
    
    /**
     * ANÁLOGO
     * @param forca
     * @return 
     */
    
    public Armadura lootArmadura(int forca){
        Random random = new Random();
        double sorte = armadura.size() * random.nextDouble();
        if (armadura.get((int)sorte).getForca() <= forca){
            return armadura.get((int)sorte);
        }
        return lootArmadura(forca);
    }
}
