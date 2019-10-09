/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Arma;
import Model.Armadura;
import Model.Monstro;
import java.awt.image.BufferedImage;

/**
 *
 * @author LG
 */
public class Carta extends javax.swing.JPanel {
    
    private BufferedImage imagem;
    
    /**
     * EXISTEM 3 CONSTRUTORES, UM PARA CADA TIPO DE CARTA, QUE SÃO:
     * -MONSTRO QUE O HEROI ESTÁ DESAFIANDO
     * -ARMA ENCONTRADA NUM BAÚ
     * -ARMADURA ENCONTRADA NUM BAÚ
     * @param inimigo
     */
    public Carta(Monstro inimigo){
        initComponents();
        nome.setText(inimigo.getNome());
        info1.setText("Dano");
        n1.setText(inimigo.getDano().x+"d"+inimigo.getDano().y);
        info2.setText("HP");
        n2.setText(""+inimigo.getHp());
        linha2.setText("");
        linha3.setText("Bônus: +"+inimigo.getBonusDano()+"/+"+inimigo.getBonusArmadura());
        Casa foto = new Casa(inimigo.getImgPath());
        foto.setBounds(20,35,210,180);
        foto.setVisible(true);
        this.add(foto);
    }
    
    public Carta(Armadura armadura){
        initComponents();
        nome.setText(armadura.getNome());
        info1.setText("Força");
        n1.setText(""+armadura.getForca());
        info2.setText("Bônus");
        n2.setText(""+armadura.getBonus());
        linha2.setText("");
        linha3.setText("");
        Casa foto = new Casa(armadura.getImgPath());
        foto.setBounds(20,35,210,180);
        foto.setVisible(true);
        this.add(foto);
    }
    
    public Carta(Arma arma){
        initComponents();
        nome.setText(arma.getNome());
        info1.setText("Força");
        n1.setText(""+arma.getForca());
        info2.setText("Bônus");
        n2.setText(""+arma.getBonus());
        linha2.setText("");
        linha3.setText("");
        Casa foto = new Casa(arma.getImgPath());
        foto.setBounds(20,35,210,180);
        foto.setVisible(true);
        this.add(foto);
    }
    
    /**
     * DEFINE O TEXTO DE DESCIÇÃO DA CARTA.
     * @param s 
     */
    public void setN2(String s){
        n2.setText(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nome = new javax.swing.JLabel();
        n2 = new javax.swing.JLabel();
        n1 = new javax.swing.JLabel();
        info1 = new javax.swing.JLabel();
        info2 = new javax.swing.JLabel();
        linha3 = new javax.swing.JLabel();
        linha2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 204, 153));
        setBorder(new javax.swing.border.MatteBorder(null));
        setForeground(new java.awt.Color(153, 153, 0));

        nome.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nome.setText("nome");

        n2.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        n2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n2.setText("n2");

        n1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        n1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n1.setText("n1");

        info1.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        info1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info1.setText("info1");

        info2.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        info2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info2.setText("info2");

        linha3.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        linha3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linha3.setText("linha3");

        linha2.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        linha2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linha2.setText("linha2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(n1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(info1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(n2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(info2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(linha3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(linha2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(info1)
                    .addComponent(info2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel info1;
    private javax.swing.JLabel info2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel linha2;
    private javax.swing.JLabel linha3;
    private javax.swing.JLabel n1;
    private javax.swing.JLabel n2;
    private javax.swing.JLabel nome;
    // End of variables declaration//GEN-END:variables
}
