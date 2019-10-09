/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herois_de_mesa;

import Controller.Controller;

/**
 *
 * @author LG
 */
public class Herois_de_Mesa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller control = new Controller();
        control.levelUp();
        control.iniciaTurno();
    }
    
}
