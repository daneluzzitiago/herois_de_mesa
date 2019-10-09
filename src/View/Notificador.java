/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Observable;

/**
 *  CLASSE AUXILIAR PARA NOTIFICAR O OBSERVADOR (CONTROLLER) QUE HOUVE UMA SELEÇÃO
 * DE AÇÃO A SER REALIZADA.
 * @author LG
 */
public class Notificador extends Observable{
    
    private String selecao;

    public Notificador() {
    }
    
    public void selecionou(String s){
        this.selecao = s;
        setChanged();
        notifyObservers();
    }

    public String getSelecao() {
        return selecao;
    }
       
}
