/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodatos;

import javax.swing.table.DefaultTableModel;
import negocio.Producto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Duoc UC
 */
public class ProductoDAOIT {
    
    public ProductoDAOIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of grabar method, of class ProductoDAO.
     */
    @Test
    public void testGrabar() {
        //Producto prd = new Producto(55, "Cajita", "euro", (float)10.1);
        System.out.println("grabar");
        Producto producto = new Producto(55, "Cajita", "euro", (float)10.1);
        ProductoDAO instance = new ProductoDAO();
        boolean expResult = true;
        boolean result = instance.grabar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of mostrar method, of class ProductoDAO.
     */
    @Test
    public void testMostrar() {
        System.out.println("mostrar");
        ProductoDAO instance = new ProductoDAO();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.mostrar();
        System.out.println(expResult);
        System.out.println(result);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
