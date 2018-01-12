/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import accesodatos.ProductoDAO;
import java.io.IOException;

/**
 *
 * @author Duoc UC
 */
public class Principal {
    
     public static void main(String[] args) throws IOException {
        
        new Controlador().iniciar();
        
        //ProductoDAO test  = new ProductoDAO();
        //test.grabar();
        
        
    }
    
}
