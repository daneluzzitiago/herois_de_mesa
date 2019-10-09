/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Alvo;
import Model.Arma;
import Model.Armadura;
import Model.Armaria;
import Model.Jogador;
import Model.Monstro;
import View.Tela;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LG
 */
public class Controller implements Observer{
    private final Tela tela;
    private Jogador jogador;
    
    private int tamTabuleiro;
    private int dado;
    
    private String[] casa;
    
    private Monstro inimigo;
    private final Armaria armaria;
    private final ArrayList<Monstro> monstro;
    private final ArrayList<Monstro> desafio;
    
    
    private Armadura lootArmadura;
    private Arma lootArma;
    
    private final Timer timer;
    
    
    public Controller(){
        
        armaria = new Armaria();
        lootArmadura = new Armadura();
        lootArma = new Arma();        
        tela = new Tela(this);
        jogador = new Jogador();
        monstro = new ArrayList<Monstro>();
        desafio = new ArrayList<Monstro>();
        
        tela.setLocationRelativeTo(null);
        tela.switchTabuleiro(false);
        tela.setDimension(800,600);
        tela.setResizable(false);
        tela.setVisible(true);
        tela.addObservador(this);
        
        addMonstros();
        
        criaTabuleiro();
        
        for ( int i = 0 ; i < casa.length ; i++){
            tela.casaAdjust(casa[i],i);
        }
        
        this.timer = new Timer();
        timer.start();
        
    }
    

        
//  INTERAÇÃO COM O JOGADOR E INTERFACE
    
    /**
     * Quando o jogador encontra o baú.
     *  A visão do mapa é retirada e é sorteado um valor, e dependendo do resultado
     *  o jogador recebe, através de um auxiliar, uma arma ou uma armadura, também
     *  sorteados pelo método lootArma ou lootArmadura da classe Armaria, usando a
     *  força do personagem como parâmetro. O jogador opta  por equipar ou largar
     *  o item. Nem sempre é melhor que o anterior.
     */
    
    public void abreBau(){
        exibeTabuleiro(false);
        Random random = new Random();
        double sorte = random.nextDouble() * 2;
        if ((int)sorte == 1){
            lootArmadura = armaria.lootArmadura(jogador.getForca());
            diz("Item: "+lootArmadura.getNome()+". Bônus: "+lootArmadura.getBonus()+".");
            tela.showCard(lootArmadura);
            exibeSelecao("Equipar armadura","Largar");
        }else{
            lootArma = armaria.lootArma(jogador.getForca());
            diz("Item: "+lootArma.getNome()+". Bônus: "+lootArma.getBonus()+".");
            tela.showCard(lootArma);
            exibeSelecao("Equipar arma","Largar");
        }
    }
    
    /**
     * Chama método da classe Tela que exibe as Casas, representando o tabuleiro.
     * @param modo define se será ou não visível.
     */
    
    public void exibeTabuleiro(boolean modo){
        tela.switchTabuleiro(modo);
    }
    
    /**
     * Exibe uma tela de seleção simples de uma opção. Serve pra diminuir o fluxo
     * de informações e melhorar a experiência do jogador.
     * @param a é a informação que será exibida para ele.
     */
    
    public void exibeSelecao(String a){
        tela.escondeSelecao();
        tela.mostraSelecao(a);
    }
    
    /**
     * Análogo ao método anterior, porém com seleção dupla de opções.
     * @param a é a primeira opção, normalmente a mais usada usada.
     * @param b é a segunda opção.
     */
    
    public void exibeSelecao(String a, String b){
        tela.escondeSelecao();
        tela.mostraSelecao(a , b);
    }
    
    /**
     * Método que coloca no menu à direita todas as informações do jogador, que
     * podem ser alteradas depois de equipar um item, lutar, tomar golpes, etc.
     */
    
    public void atualizaTela(){
        ArrayList<String> status = new ArrayList<String>();
        status.add("-Status-");
        status.add("Nivel: "+jogador.getNivel());
        status.add("Força: "+jogador.getForca());
        status.add("Inteligencia: "+jogador.getInteligencia());
        status.add("Dano: 1d6+"+jogador.getBonusDano());
        status.add("Equipado: "+jogador.getArma().getNome());
        status.add("Armadura: "+jogador.getBonusArmadura());
        status.add("Equipado: "+jogador.getArmadura().getNome());
        status.add("HP: "+jogador.getHp()+"/"+jogador.getMaxhp());
        status.add("Mana: "+jogador.getMana()+"/"+jogador.getMaxMana());
        tela.setStats(status);
    }
    
    /**
     * Escreve coisas no jText.
     * @param s é a frase
     */
    
    public void diz(String s){

        tela.jLogAppend(s);

        
    }
    
    /**
     * Como num RPG de mesa, o jogo é baseado em resultado de dados para coisas
     * como andar,bater,soltar uma magia, etc.
     * @param quantidade quantidade de dados que serão "jogados"
     * @param dado total de lados do dado
     * @return um total de "quantidade" dados de "dado" lados gerados aleatória-
     * mente.
     */
    
    public int rolarDado(int quantidade,int dado){
        int total = 0;
        int i;
        Random r = new Random();
        for ( i = 0; i < quantidade ; i++){
            total += r.nextInt((dado - 1) + 1)+ 1;
        }
        return total;
    }
    
    /**
     *  SAVE E LOAD GAME
     */
    
    public void save(){
        try {
            FileOutputStream out = new FileOutputStream("file.txt");
            ObjectOutputStream arquivo = new ObjectOutputStream(out);
            arquivo.writeObject(this.jogador);
            arquivo.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        diz("Os status de seu herói foram salvos!");
        iniciaTurno();

    }
    
    public void load(){
        try {
            FileInputStream in = new FileInputStream("file.txt");
            ObjectInputStream arquivo = new ObjectInputStream (in);
            
            this.jogador = (Jogador) (Alvo) arquivo.readObject();
            jogador.setPosicao(0);
            tela.resetaTabuleiro();
            diz("Status recuperados!! Retornando ao início do tabuleiro...");
            iniciaTurno();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * Quando a Tela exibe alguma seleção para o usuário, ela chamará um método
     * de uma classe sua chamada Notificador, a qual é observada pelo Controller,
     * e esse Notificador, através desse método sobrescrito de Update, avisa que
     * é hora de pegar uma string (preenchida com o que o jogador selecionou) e,
     * baseado nessa string, fazer alguma coisa.
     * @param o é o objeto observado, o Notificador
     * @param arg não sei que isso aqui ainda
     */
        
    @Override
    public void update(Observable o, Object arg) {
        tela.escondeSelecao();
        String selecao = tela.escolha();
        switch (selecao){ 
            case "Andar":
                dado = rolarDado(1,6);
                for ( int i = 0 ; i < dado ; i ++){
                    jogador.setPosicao(1 + jogador.getPosicao());
                    if ("desafio".equals(casa[jogador.getPosicao()]) ){
                        dado = i+1;
                        break;
                    }
                }
                diz("Você chegou na casa "+jogador.getPosicao());
                System.out.println("1");
                tela.casaMove(dado, 0);

                diz("Você encontrou um "+casa[jogador.getPosicao()]+"!");

                String aux = casa[jogador.getPosicao()];
                if ("baú".equals(aux)){
                    exibeSelecao("Abrir baú");
                }
                if ("monstro".equals(aux)){
                    exibeSelecao("Lutar");
                }
                if ("desafio".equals(aux)){
                    exibeSelecao("Enfrentar desafio");
                }
                break;    
            case "Menu":
                timer.setRunning(false);
                tela.switchTabuleiro(false);
                tela.showStatus();
                tela.setjTime(timer.getTime());
                tela.setTimeVisible(true);
                exibeSelecao("Salvar","Voltar");
                break;
            case "Voltar":
                iniciaTurno();
                break;
            case "Enfrentar desafio":
                iniciaDesafio();
                break;
            case "Lutar":
                iniciaLuta();
                break;
            case "Abrir baú":
                abreBau();
                break;
            case "Equipar armadura":
                diz("Você equipou um novo item: "+lootArmadura.getNome());
                jogador.setArmadura(lootArmadura);
                jogador.setBonusArmadura(lootArmadura.getBonus());
                atualizaTela();
                iniciaTurno();
                break;
            case "Largar":
                diz("Item abandonado.");
                iniciaTurno();
                break;
            case "Equipar arma":
                diz("Você equipou um novo item: "+lootArma.getNome());
                jogador.setArma(lootArma);
                jogador.setBonusDano(lootArma.getBonus());
                atualizaTela();
                iniciaTurno();
                break;
            case "Bater":
                atacar();
                break;
            case "Magia":
                exibeSelecao("Bola de fogo", "Cura");
                break;
            case "Bola de fogo":
                if( jogador.getMana() < 5){
                    diz("Mana insuficiente...");
                    combate();
                }
                else{
                    jogador.setMana(jogador.getMana() - 5);
                    atacar( (jogador.getInteligencia()) + rolarDado(1,4) );
                }
                break;
            case "Cura":
                if(jogador.getMana() < 4){
                    diz("Mana insuficiente...");
                    combate();

                }
                else{
                    jogador.setHp(jogador.getHp() + jogador.getInteligencia() + rolarDado(1,6) );
                    if (jogador.getHp() > jogador.getMaxhp()){
                        jogador.setHp(jogador.getMaxhp());
                    }
                    jogador.setMana(jogador.getMana() - 4);
                    atualizaTela();
                    diz("Você se curou ");
                    apanhar();
                }
                break;
            case "Continuar":      
                iniciaTurno();
                break;
            case "Sair":
                this.gameOver();
                break;
            case "Salvar":
                this.save();
                jogador.setTempo(timer.getTotalSeconds());
                break;
            case "Carregar":
                this.load();
                timer.setTime(jogador.getTempo());
                break;
        }
    }

    /**
     * Finaliza o jogo, tchau.
     */
   
    public void gameOver(){
        System.exit(0);
    }
    
//    BANCO DE DADOS
    
    /**
     * Esse método faz o vetor de String "casa" ter um certo tamanho, e insere
     * as três opções de casas:
     *  Baú, onde o jogador vai receber um item aleatório
     *  Monstro, onde o jogador arranjar briga com um monstro aleatório
     *  Desafio, onde o jogador enfrenta um chefão
     * 
     * Os dois primeiros tem as casas sorteadas aleatóriamente, Mas o desafio
     * está a cada 1/5 do tabuleiro.
     */
    
    public void criaTabuleiro(){
        tamTabuleiro = 251;
        casa = new String[tamTabuleiro];
        int i;
        int randomizador;
        Random r = new Random();
        casa[0] = "vazio";
        for (i = 1 ; i < tamTabuleiro ;i++){
            if ( (i)%(tamTabuleiro/5) == 0 ){
                casa[i] = "desafio";
            }
            else{
//                randomizador = r.nextInt((2 - 1) + 1)+ 1;
//                if(randomizador%2 == 0){
                if(i%2 == 0){
                    casa[i] = "baú";
                }
                else{
                    casa[i] = "monstro";
                }
            }
        }
    }

    /**
     * Alimenta os vetores de monstro Monstros e Desafios. Há uma divisão entre
     * os grupos de monstros para deixar claro em que parte do mapa eles aparecem.
     * Nessa caso, cada grupo deve aparecer antes/depois de cada desafio.
     */
    
    public void addMonstros(){
        monstro.add(new Monstro("Esqueleto","imagens/esqueleto.png", 3, 1, 4, 1, 0));
        monstro.add(new Monstro("Gnoll","imagens/gnoll.png", 4, 1, 4, 0, 0));
        monstro.add(new Monstro("Lobo","imagens/lobo.png", 4, 1, 4, 0, 0));
        monstro.add(new Monstro("Goblin","imagens/goblin.jpg", 5, 1, 4, 0, 0));
//        PRIMEIRO DESAFIO
        monstro.add(new Monstro("Fantasma","imagens/fantasma.png", 7, 1, 10, 1, 0));
        monstro.add(new Monstro("Elfo","imagens/elfo.jpg", 8, 2, 6, 0, 0));
        monstro.add(new Monstro("Escopião","imagens/escorpiao.jpg", 10, 2, 4, 1, 0));
        monstro.add(new Monstro("Orc","imagens/orc.png", 11, 1, 8, 1, 1));
//        SEGUNDO DESAFIO
        monstro.add(new Monstro("Vampiro","imagens/vampiro.jpg", 12, 1, 12, 2, 0));
        monstro.add(new Monstro("Druida","imagens/druida.jpg", 15, 2, 6, 1, 1));
        monstro.add(new Monstro("Basilisco","imagens/basilisco.jpg", 18, 3, 4, 1, 1));
        monstro.add(new Monstro("Minotauro","imagens/minotauro.png", 20, 2, 4, 2, 1));
//        TERCEIRO DESAFIO
        monstro.add(new Monstro("Faraó","imagens/farao.jpg", 18, 2, 8, 2, 1));
        monstro.add(new Monstro("Elemental","imagens/elemental.jpg", 20, 2, 6, 4, 0));
        monstro.add(new Monstro("Grifo","imagens/grifo.jpg", 22, 1, 12, 2, 1));
        monstro.add(new Monstro("Centauro","imagens/centauro.jpg", 25, 3, 4, 2, 2));
//        QUARTO DESAFIO
        monstro.add(new Monstro("Necromante","imagens/necromancer.jpg", 20, 1, 20, 3, 0));        
        monstro.add(new Monstro("Golem","imagens/golem.jpg", 30, 2, 6, 1, 4));
        monstro.add(new Monstro("Wyvern","imagens/wyvern.jpg", 35, 2, 6, 2, 2));
        monstro.add(new Monstro("Berserk","imagens/berserk.jpg", 38, 2, 8, 2, 3));
//        DESAFIOS
        desafio.add(new Monstro("Elefante","imagens/elefante.jpg", 15, 2, 6, 1, 1));
        desafio.add(new Monstro("Troll","imagens/troll.jpg", 25, 3, 6, 1, 1));
        desafio.add(new Monstro("Esfinge","imagens/esfinge.jpg", 35, 3, 8, 2, 2));
        desafio.add(new Monstro("Demônio","imagens/capeta.jpg", 50, 3, 8, 4, 3));
        desafio.add(new Monstro("Dragão","imagens/dragao.jpg", 75, 4, 6, 5, 5));
    }

//    COMBATE
    
    /**
     * No início do turno o jogador vê as casas que pode cair. Elas devem estar
     * atualizadas (método atualiza garante isso). Tela da um switch para True
     * nas casas do tabuleiro e exibe a opção "Andar", onde o jogador joga um
     * dado de 6 lados e cai em algum lugar com alguma coisa.
     */
    
    public void iniciaTurno(){
        tela.setTimeVisible(false);
        timer.setRunning(true);
        atualizaTela();
        tela.removeCard();
        tela.hideStatus();
        tela.mostraMapa(); 
        tela.switchTabuleiro(true);
        if(jogador.getDificuldade() == 5){
            diz("Fim de jogo!");
            exibeSelecao("Sair");
        }
        else
            exibeSelecao("Andar","Menu");
    }
    
    /**
     * O personagem ganha um nível. Caso ele seja um guerreiro, subira 1 de força
     * por nível e 1 de inteligência a cada 2 níveis, além de receber 5 de hp.
     * O mago recebe 1 de força a cada 2 níveis e 1 de inteligência e 3 de hp
     * por cada nível. Em qualquer um dos casos, o hp é restaurado ao máximo.
     * Depois disso chama o método que atualiza a tela com os status do jogador.
     * É dito "Subiu de nível" caso tenha subido para nível maior que 1. Isso
     * porque todo jogador começa nível 0 e sobe de nível antes do jogo começar.
     */
    
    public void levelUp(){
        jogador.setNivel(jogador.getNivel()+1);
        jogador.setForca(jogador.getForca()+1);
        jogador.setInteligencia(jogador.getInteligencia()+1);
        jogador.setMaxhp(jogador.getMaxhp()+5);
        jogador.setHp(jogador.getMaxhp());
        jogador.setMaxMana(jogador.getMaxMana() + 3);
        jogador.setMana(jogador.getMaxMana());
        if (jogador.getNivel() > 1){
            diz("Subiu para o nível "+jogador.getNivel());
        }
        atualizaTela();
    }
    
    /**
     * Depois que fez o seu ataque, é hora do herói tomar uma porrada 
     * do bixo com um cálculo envolvendo o bonus de dano e armadura dos dois, além
     * de uma jogada de dados que depende do bixo (seu construtor já recebe os
     * dados que devem ser jogados no ataque). Se o hp do heroi for pra menos que
     * 1, ele morre, e é exibida a opção de ressucitar. Caso contrário, apenas é
     * dito que ele tomou X de dano do inimigo. O combate continua rolando.
     */
    
    public void apanhar(){
        int total = inimigo.getBonusDano() + rolarDado(inimigo.getDano().x,inimigo.getDano().y) - jogador.getBonusArmadura();
        if (total <= 1){
            total = 1; // O dano nunca pode ser negativo ou zero
        }
        jogador.setHp( jogador.getHp() - total);
        if (jogador.getHp() <= 0){
            jogador.setHp(0);
            atualizaTela();
            diz("Você morreu.");
            inimigo.setHp(inimigo.getMaxhp());
            exibeSelecao("Sair","Carregar");
        }
        else{
            diz("Você recebeu "+total+" de dano de "+inimigo.getNome()+".");
            atualizaTela();
            combate();
        }
    }
    
    /**
     * Heroi bate com sua arma no monstro, dando dano calculado nos bônus dos dois.
     * Caso o hp do inimigo fique menor que 1, ele irá morrer. No caso de ser um
     * monstro, o heroi ganha 1 de experiência (a cada 3 ele sobe de nível), mas
     * se for um desafio, ele sobe de nível automaticamente sem mexer na experi-
     * ência. Quando o desafio é vencido, um atributo do jogador é atualizado,
     * indicando que é hora de aparecer monstros mais fortes e desafios maiores.
     * Se o bixo não morreu, o heroi vai apanhar.
     */
    
    public void atacar(){
//        int total = jogador.getBonusDano() + rolarDado(jogador.getDano().x,jogador.getDano().y) - inimigo.getBonusArmadura();
        int total = 3;
        if (total <= 1){
            total = 1; // O dano nunca pode ser negativo ou zero
        }
        diz("Você atacou "+inimigo.getNome()+". "+inimigo.getNome()+" recebeu "+total+" de dano.");
        inimigo.setHp( inimigo.getHp() - total);
        tela.updateCardHp(inimigo.getHp());
        if (inimigo.getHp() <= 0){
            diz(inimigo.getNome()+" morreu.");
            inimigo.ressussita();
            if ("desafio".equals(casa[jogador.getPosicao()])){
                levelUp();
                jogador.aumentaDificuldade();
            }
            else{
                jogador.aumentaExperiencia();
                if (jogador.getExperiencia() == 3){
                    levelUp();
                    jogador.setExperiencia(0);
                }
            }
            exibeSelecao("Continuar");
        }
        else{
            apanhar();
        }
    }
    
    /**
     * Esse tipo de ataque acontece quando uma magia é soltada. Magias ignoram
     * a defesa do monstro e dão dano puro pré definido, que é o
     * @param i. Contece exatamente a mesma coisa que o ataque normal, só não
     * há cálculos de dano.
     */
    
    public void atacar(int i){
        inimigo.setHp(inimigo.getHp() - i);
        
        diz("Você soltou uma magia. "+inimigo.getNome()+" recebeu "+i+" de dano.");
        if (inimigo.getHp() <= 0){
            diz(inimigo.getNome()+" morreu.");
            inimigo.ressussita();
            if ("desafio".equals(casa[jogador.getPosicao()])){
                levelUp();
                jogador.aumentaDificuldade();
            }
            else{
                jogador.aumentaExperiencia();
                if (jogador.getExperiencia() == 3){
                    levelUp();
                    jogador.setExperiencia(0);
                }
            }
            exibeSelecao("Continuar");
        }
        else{
            apanhar();
        }
    }
    
    /**
     * Método que começa a luta, selecionando um monstro baseado na dificuldade
     * que o jogador está, randomizando entre um certo range do vetor de monstros.
     * O tabuleiro deixa de ser exibido, e a imagem do inimigo é exibida no fundo.
     * O jogador é informado que achou o tal bixo, e que ele tem tanto de hp.
     */
    
    public void iniciaLuta(){
        Random random = new Random();
        int sorte = random.nextInt(4);
        tela.switchTabuleiro(false);
        inimigo = monstro.get( ( jogador.getDificuldade() * 4 ) + sorte );
        tela.showCard(inimigo);
        diz("Inimigo: "+inimigo.getNome()+". HP: "+inimigo.getMaxhp()+".");
        tela.showStatus();
        combate();
    }
    
    /**
     * Análogo ao método de luta com monstro, mas com acesso direto e certeiro
     * ao desafio.
     */
    
     public void iniciaDesafio(){
        inimigo = desafio.get(jogador.getDificuldade());
        tela.switchTabuleiro(false);
        diz("Inimigo: "+inimigo.getNome()+". HP: "+inimigo.getMaxhp()+".");
        tela.showCard(inimigo);
        
        tela.showStatus();
        combate();
    }
     
     /**
      * Começa o turno, onde o jogador pode optar por bater ou soltar uma magia.
      */
    
    public void combate(){
        exibeSelecao("Bater","Magia");
    }
    
}
