/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.util;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class Funcionalidades {

    public String contrasenaAleatoria() {
        char[] nuevaClave;
        nuevaClave = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','!','#','$','%','&','+','-','*','~','^','<','>','@','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String pass = "";
        for (int i = 0; i < 12; i++) {
            pass += nuevaClave[new Random().nextInt(23)];
        }
        return pass;
        
      }
      
        
    
}
