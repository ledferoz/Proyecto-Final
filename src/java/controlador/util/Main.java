/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.util;

/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Funcionalidades fun = new Funcionalidades();
        fun.contrasenaAleatoria();
        System.out.println( fun.contrasenaAleatoria());
    }
    
}
