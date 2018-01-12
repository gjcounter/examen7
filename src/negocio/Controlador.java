/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import accesodatos.ProductoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import presentacion.Vista;
import presentacion.VistaAgregar;
import servicio.Indicadores;

/**
 *
 * @author Duoc UC
 */
public class Controlador implements ActionListener  {

    Vista interfaz = new Vista();
    public static VistaAgregar interfazagregar = new VistaAgregar();
    
    //modelo
    private ProductoDAO modelo = new ProductoDAO();
    
    //valores monedas
    public static float valordolar;
    public static float valoreuro;
    
    
    
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
    
    public void iniciar() throws IOException{
    
        Indicadores mindicador = new Indicadores();
        valoreuro = mindicador.getValorDia("euro");
        valordolar = mindicador.getValorDia("dolar");
       
       //Escuchamos los botones
        interfazagregar.boton_guardar.setActionCommand( "boton_guardar" );
        interfazagregar.boton_guardar.addActionListener(this);
        interfaz.boton_refrescar.setActionCommand( "boton_refrescar" );
        interfaz.boton_refrescar.addActionListener(this);
        interfaz.boton_agregar.setActionCommand( "boton_agregar" );
        interfaz.boton_agregar.addActionListener(this);
       
        
        //Interactuar con el combobox
        ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = interfaz.cb_monedas.getSelectedItem().toString();

                switch (s) {//check for a match
                    case "Dólar":
                       
                        interfaz.tf_valor.setText(String.valueOf(valordolar));
                       
                        break;
                    case "Euro":
                        interfaz.tf_valor.setText(String.valueOf(valoreuro));
                        break;
                   
                    default:
                         interfaz.tf_valor.setText("");
                    break;
                }
            }
        };
        interfaz.cb_monedas.addActionListener(cbActionListener);
       
        //Interactuar con la tabla
       // interfaz.tabla.addMouseListener(this);
    }

    //asignamos acciones a los clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        switch ( Accion.valueOf( e.getActionCommand() ) ){
            case boton_guardar:
                
                int idproducto = 0;
                String nombreproducto = "";
                String moneda = "";
                float precio = 0;
                
                String errormsg = "";
                
                idproducto = Integer.parseInt(interfazagregar.tf_idproducto.getText());
                nombreproducto = interfazagregar.tf_nombreproducto.getText();
                precio = Float.parseFloat(interfazagregar.tf_precioproducto.getText());
                
                if (interfazagregar.cb_monedaproducto.getSelectedItem().toString().equals("Dólar")){
                    moneda = "dolar";
                } else if (interfazagregar.cb_monedaproducto.getSelectedItem().toString().equals("Euro")){
                    moneda = "euro";
                }
                
                if (idproducto <= 0 ) {
                    errormsg = "La ID del Producto debe ser mayor a 0";
                }
                
                if (precio <= 0 ) {
                    errormsg = "El precio debe ser mayor a 0";
                }
                
                if (nombreproducto.length() <= 2 ) {
                    errormsg = "El nombre debe tener al menos 3 caracteres";
                }
                
                if (moneda == "" ) {
                    errormsg = "Debe escoger una moneda";
                }  
                
                //validados los datos armamos el objeto y enviamos la info
                if (errormsg == "") {
                     Producto nuevoproducto = new Producto(idproducto, nombreproducto, moneda, precio);
                      modelo.grabar(/*nuevoproducto*/);
                } else {
                    JOptionPane.showMessageDialog(null, errormsg, "Error", JOptionPane.WARNING_MESSAGE);
                }
                         
            break;
            case boton_refrescar:
                //llamar tabla
                interfaz.tabla.setModel(this.modelo.mostrar());
            break;
            
            case boton_agregar:
                interfazagregar.setVisible(true);
            break;
        }  
    }
    
    
}
