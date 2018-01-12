/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import accesodatos.ProductoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JOptionPane;
import presentacion.Vista;
import presentacion.VistaAgregar;

/**
 *
 * @author Duoc UC
 */
public class Controlador implements ActionListener {

    Vista interfaz = new Vista();
    public static VistaAgregar interfazagregar = new VistaAgregar();
    
    //modelo
    private ProductoDAO modelo = new ProductoDAO();
    
    public enum Accion{
        boton_refrescar,
        boton_agregar,
        boton_guardar,
        cb_monedas,
    }

    public Controlador() {
         try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(interfaz);
            SwingUtilities.updateComponentTreeUI(interfazagregar);
            this.interfaz.setTitle("Productos");
            interfazagregar.setTitle("Agregar Producto");
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        interfaz.setVisible(true);
    }
    
    public void iniciar(){
       //Escuchamos los botones
        interfazagregar.boton_guardar.setActionCommand( "boton_guardar" );
        interfazagregar.boton_guardar.addActionListener(this);
        interfaz.boton_refrescar.setActionCommand( "boton_refrescar" );
        interfaz.boton_refrescar.addActionListener(this);
        interfaz.boton_agregar.setActionCommand( "boton_agregar" );
        interfaz.boton_agregar.addActionListener(this);
       
        
        //Interactuar con el combobox
        //interfaz.cb_monedas.addFocusListener((FocusListener) this);
       
        //Interactuar con la tabla
       // interfaz.tabla.addMouseListener(this);
    }

    //asignamos acciones a los clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        switch ( Accion.valueOf( e.getActionCommand() ) ){
            case boton_guardar:
                
                int idproducto;
                String nombreproducto;
                String moneda = "";
                float precio;
                
                idproducto = Integer.parseInt(interfazagregar.tf_idproducto.getText());
                nombreproducto = interfazagregar.tf_nombreproducto.getText();
                precio = Float.parseFloat(interfazagregar.tf_precioproducto.getText());
                
                if (interfazagregar.cb_monedaproducto.getSelectedItem().toString().equals("Dólar")){
                    moneda = "dolar";
                } else if (interfazagregar.cb_monedaproducto.getSelectedItem().toString().equals("Euro")){
                    moneda = "euro";
                }
                
                Producto nuevoproducto = new Producto(idproducto, nombreproducto, moneda, precio);
                
                //modelo.grabar(nuevoproducto);
                
               
            break;
            case boton_refrescar:
            break;
            
            case boton_agregar:
                interfazagregar.setVisible(true);
            break;
        }  
    }
    
    
}
