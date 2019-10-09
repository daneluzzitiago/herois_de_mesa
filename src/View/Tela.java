/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Arma;
import Model.Armadura;
import Model.Monstro;
import java.awt.Color;
import javax.swing.text.DefaultCaret;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
/**
 *
 * @author LG
 */
public class Tela extends javax.swing.JFrame implements KeyListener{
    
    private int selecao = 1;
    private int quantasOpcoes;
    private final Notificador notificador;
    private boolean jogando = true;
    private final int casaDim = 60;
    private ArrayList<Casa> casa;
    private ArrayList<JLabel> n;
    private final Casa fundo;
    private final String bau = "imagens/bau.png"; 
    private final String monstro = "imagens/monstro.png"; 
    private final String desafio = "imagens/desafio.png"; 
    private final String vazio = "imagens/vazio.png"; 
    private final String mapa = "imagens/mapa.jpg";
    private final Menu statsMenu;
    private final Menu inventoryMenu;
    private final Menu skillsMenu;
    private final JLayeredPane telaPrincipal;
    private Carta carta;
    private int choose = 1;
    private Controller controller;
    

    public Tela(Controller controller) {
        initComponents();
        
        this.controller = controller;
        
        telaPrincipal = new JLayeredPane();
        telaPrincipal.setVisible(true);
        telaPrincipal.setSize(800,600);
        this.setSize(800,800);
        this.add(telaPrincipal);
          
        this.statsMenu = new Menu(10,10);
        this.inventoryMenu = new Menu(10,10);
        this.skillsMenu = new Menu(10,10);
        
        DefaultCaret caret = (DefaultCaret)jLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        notificador = new Notificador();
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        casa = new ArrayList<Casa>();
        n = new ArrayList<JLabel> ();

        fundo = new Casa(mapa);
        fundo.setVisible(true);
        fundo.setBounds(0, 0, 800, telaPrincipal.getHeight());

        telaPrincipal.add(fundo,6);
        
        this.jTime.setVisible(false);
    }
    
    /**
     * DEFINE IMAGEM DE FUNDO DA TELA.
     */
    
    public void setFundo(String s){
        fundo.muda(s);
    }
    
    /**
     * OPERAÇÕES COM O MENU DE STATUS DO JOGADOR.
     */
    
    public void showStatus(){
        telaPrincipal.add(statsMenu,1);
        statsMenu.setLocation(10,10);
    }
    
    public void hideStatus(){
        telaPrincipal.remove(statsMenu);
    }
    
    public void setStats(ArrayList<String> stats){
        statsMenu.setMenuOptions(stats);
    }
    
    /**
     * FAZ COM QUE TODAS AS CASAS DO TABULEIRO VOLTEM A POSIÇÃO INICIAL,
     * ÚTIL NOS LOAD GAME QUANDO PERSONAGEM MORRE.
     */
    
    public void resetaTabuleiro(){
        int i = 0;
        for (Casa casa1:casa){
            casa1.setLocation(23 + (i*65), casa1.getLocation().y);
            i++;
        }
        i = 0;
        for (JLabel n1:n){
            n1.setLocation(23 + (i*65), n1.getLocation().y);
            i++;
        }
    }
    
    /**
     * OPERAÇÕES DE EXIBIÇÃO DE CARTA
     * @param inimigo 
     */
    
    public void showCard(Monstro inimigo){
        this.carta = new Carta(inimigo);
        carta.setVisible(true);
        carta.setBounds(200,50,250,353);
        telaPrincipal.add(carta,0);
    }
    
    public void updateCardHp(int i){
        carta.setN2(""+i);
    }
    
    public void showCard(Armadura armadura){
        this.carta = new Carta(armadura);
        carta.setVisible(true);
        carta.setBounds(200,50,250,353);
        telaPrincipal.add(carta,0);
    }
    
    public void showCard(Arma arma){
        this.carta = new Carta(arma);
        carta.setVisible(true);
        carta.setBounds(200,50,250,353);
        telaPrincipal.add(carta,0);
    }
    
    public void removeCard(){
        if (carta != null)
            telaPrincipal.remove(carta);
    }
    
    /**
     * OPERAÇÕES DE EXIBIÇÃO DO TEMPO DE JOGO QUANDO O MENU É ABERTO.
     * @param s 
     */
    
    public void setjTime(String s){
        this.jTime.setText(s);
    }
    
    public void setTimeVisible(boolean bol){
        this.jTime.setVisible(bol);
    }
    
    /**
     * MÉTODO AUXILIAR QUE POSICIONA AS LABELS DO TABULEIRO NO INÍCIO DO JOGO
     * @param s
     * @param i 
     */

    public void casaAdjust(String s, int i){
        Casa c = new Casa();
        JLabel num = new JLabel(""+i,SwingConstants.CENTER);
        Color cor = new Color(23,64,217);
        
        num.setBorder(telaPrincipal.getBorder());
        num.setOpaque(true);
        num.setBackground(cor);
        num.setForeground(Color.WHITE);
        
        if ( "baú".equals(s)){
            c.muda("imagens/bau.png");
        }else if ( "monstro".equals(s)){
            c.muda("imagens/monstro.png");
        }else if ( "desafio".equals(s)){
            c.muda("imagens/desafio.png");
        }else{
            c.muda("imagens/vazio.png");
        }
        num.setBounds( (23+(i*65)), 320, 60, 20);
        c.setBounds( (23+(i*65)), 340, casaDim, casaDim);
        
        c.setVisible(false);
        num.setVisible(false);
        
        n.add(num);
        casa.add(c);
        telaPrincipal.add(c,0);
        telaPrincipal.add(num,0);
    }
    
    /**
     * FAZ COM QUE TODAS AS LABELS DO TABULEIRO SEJAM DESLOCADAS
     * @param x
     * @param y 
     */
    
    public void casaMove(int x,int y){
        for( Casa casa1:casa){
            casa1.setBounds(casa1.getX()-(x*65),casa1.getY()-y,casaDim,casaDim);
        }
        for( JLabel n1:n){
            n1.setBounds(n1.getX()-(x*65),n1.getY()-y,60,20);
        }        
    }
    
    
    public void setDimension(int x, int y){
        telaPrincipal.setBounds(0, 0, x, y);
    }
    
    public void setBackground(String path){
        fundo.muda(path);
        fundo.setVisible(true);
    }
    
    public void mostraMapa(){
        fundo.muda(mapa);
        fundo.setVisible(true);
    }
    
    public void setJogando(boolean bool){
        this.jogando = bool;
    }
    
    public void addObservador (Observer o){
        this.notificador.addObserver(o);
    }
    
    public String escolha(){
        return this.notificador.getSelecao();
    }
    
    /**
     * LISTENER DO TECLADO QUE MEXE AS SETAS DA SELEÇÃO
     * @param e 
     */
    
    @Override
    public void keyPressed(KeyEvent e){
        if (jogando){
            int c = e.getKeyCode();
            switch (c) {
                case KeyEvent.VK_UP:
                    if (this.quantasOpcoes == 2){
                        selecionaPrimeiro();
                        this.selecao = 1;
                        break;
                    }
//                    teste.up();
                    break;
                case KeyEvent.VK_DOWN:
                    if (this.quantasOpcoes == 2){
                        selecionaSegundo();
                        this.selecao = 2;
                        break;
                    }
//                    teste.down();
                    break;
                case KeyEvent.VK_ENTER:
                    if (this.quantasOpcoes == 1){
                        this.notificador.selecionou(this.jOpcao1.getText());
                        break;
                    }
                    else if (this.selecao == 2){
                        this.notificador.selecionou(this.jOpcao2.getText());
                        break;
                    }                
                    else{
                        this.notificador.selecionou(this.jOpcao1.getText());
                        break;
                    }
                }
    }
 }

    public void setSelecao(int selecao) {
        this.selecao = selecao;
    }

    public void jLogAppend(String s){
        this.jLog.append(s+"\n");
    }
    
    /**
     * MOSTRA OU NÃO O TABULEIRO
     * @param modo 
     */
    public void switchTabuleiro(boolean modo){
        for (JLabel n1 : n) {
            n1.setVisible(modo);
        }
        for (Casa c : casa) {
            c.setVisible(modo);            
        }
    }
    
    /**
     * MÉTODOS AUXILIARES PARA O MENU INTERATIVO DE OPÇÕES PARA O USUÁRIO ESCOLHER.
     */
    
    public void mostraSelecao(){
        selecao = 1;
        quantasOpcoes = 1;
        jOpcao1.setText(" ");
        this.notificador.selecionou(" ");
    }
    
    public void mostraSelecao(String nome){
        selecao = 1;
        quantasOpcoes = 1;
        seta1.setVisible(true);
        jOpcao1.setText(nome);
        jOpcao1.setVisible(true);
        jOpcao2.setText(" ");
        jOpcao2.setVisible(true);
    }
    
    public void mostraSelecao(String nome1, String nome2){
        selecao = 1;
        quantasOpcoes = 2;
        seta1.setVisible(true);
        jOpcao1.setText(nome1);
        jOpcao2.setText(nome2);
        jOpcao1.setVisible(true);
        jOpcao2.setVisible(true);
    }
    
    public void escondeSelecao(){
        jOpcao1.setVisible(false);
        jOpcao2.setVisible(false);
        seta1.setVisible(false);
        seta2.setVisible(false);
    }
    
    public void selecionaPrimeiro(){
        this.seta1.setVisible(true);
        this.seta2.setVisible(false);
    }
    
    public void selecionaSegundo(){
        this.seta1.setVisible(false);
        this.seta2.setVisible(true);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLog = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jOpcao1 = new javax.swing.JLabel();
        jOpcao2 = new javax.swing.JLabel();
        seta1 = new javax.swing.JLabel();
        seta2 = new javax.swing.JLabel();
        jTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));

        jLog.setEditable(false);
        jLog.setBackground(new java.awt.Color(119, 150, 231));
        jLog.setColumns(20);
        jLog.setFont(new java.awt.Font("Ubuntu Mono", 1, 13)); // NOI18N
        jLog.setRows(5);
        jScrollPane1.setViewportView(jLog);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jOpcao1.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jOpcao1.setForeground(new java.awt.Color(255, 255, 255));
        jOpcao1.setText("jLabel1");

        jOpcao2.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jOpcao2.setForeground(new java.awt.Color(255, 255, 255));
        jOpcao2.setText("jLabel1");

        seta1.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        seta1.setForeground(new java.awt.Color(255, 255, 255));
        seta1.setText(">");

        seta2.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        seta2.setForeground(new java.awt.Color(255, 255, 255));
        seta2.setText(">");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seta2)
                    .addComponent(seta1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jOpcao1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(jOpcao2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOpcao1)
                    .addComponent(seta1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOpcao2)
                    .addComponent(seta2))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTime.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTime.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 332, Short.MAX_VALUE)
                        .addComponent(jTime, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTime, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea jLog;
    private javax.swing.JLabel jOpcao1;
    private javax.swing.JLabel jOpcao2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTime;
    private javax.swing.JLabel seta1;
    private javax.swing.JLabel seta2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
