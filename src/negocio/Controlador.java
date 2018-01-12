/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import accesodatos.ProductoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Controlador implements ActionListener,MouseListener {

    Vista interfaz = new Vista();
    public static VistaAgregar interfazagregar = new VistaAgregar();
    
    //modelo
    private ProductoDAO modelo = new ProductoDAO();

   /* borrar esto */
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
  
    
    public enum Accion{
        boton_guardar,
        boton_eliminar,
        boton_mostrar,
        boton_modificar,
        boton_limpiar,
        boton_redes,
        boton_bruto,
        boton_aumentar
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
       /* //Escuchamos los botones
        interfazagregar.boton_guardar.setActionCommand( "boton_guardar" );
        interfazagregar.boton_guardar.addActionListener(this);
        interfazeliminar.boton_eliminar.setActionCommand( "boton_eliminar" );
        interfazeliminar.boton_eliminar.addActionListener(this);
        interfazmostrar.boton_mostrar.setActionCommand( "boton_mostrar" );
        interfazmostrar.boton_mostrar.addActionListener(this);
        interfazmostrar.boton_modificar.setActionCommand( "boton_modificar" );
        interfazmostrar.boton_modificar.addActionListener(this);
        interfazagregar.boton_limpiar.setActionCommand( "boton_limpiar" );
        interfazagregar.boton_limpiar.addActionListener(this);
        interfazoperaciones.boton_redes.setActionCommand( "boton_redes" );
        interfazoperaciones.boton_redes.addActionListener(this);
        interfazoperaciones.boton_bruto.setActionCommand( "boton_bruto" );
        interfazoperaciones.boton_bruto.addActionListener(this);
        interfazoperaciones.boton_aumentar.setActionCommand( "boton_aumentar" );
        interfazoperaciones.boton_aumentar.addActionListener(this);
        
        //Interactuar con la tabla
        interfazmostrar.tabla.addMouseListener(this);
    */}

    //asignamos acciones a los clicks
    @Override
    public void actionPerformed(ActionEvent e) {
       /* switch ( Accion.valueOf( e.getActionCommand() ) ){
            case boton_guardar:             
                //codigo
                int codigoempleado = 0;
                if (interfazagregar.tf_codigo.getText().length() > 0){
                    codigoempleado = Integer.valueOf(interfazagregar.tf_codigo.getText());
                }
                
                // nombre
                String nombreempleado = interfazagregar.tf_nombre.getText();
                
                // apellido
                String apellidoempleado = interfazagregar.tf_apellido.getText();
                
                 //celular
                int celularempleado = 0;
                if (interfazagregar.tf_celular.getText().length() > 0){
                    celularempleado = Integer.valueOf(interfazagregar.tf_celular.getText());
                }
                
                 //rut
                String rutempleado = interfazagregar.tf_rut.getText();
                
                // email
                String emailempleado = interfazagregar.tf_email.getText();
                
                //sueldobruto
                int sueldoempleado = 0;
                if (interfazagregar.tf_sueldo.getText().length() > 0){
                    sueldoempleado = Integer.valueOf(interfazagregar.tf_sueldo.getText());
                }
                
                //departamento
                String departamentoempleado = null;
                if (!interfazagregar.combo_departamento.getSelectedItem().toString().equals("Departamento")){
                    departamentoempleado = interfazagregar.combo_departamento.getSelectedItem().toString();
                }
                //System.out.println(departamentoempleado);
               
                //estado civil
                String estadocivilempleado = interfazagregar.combo_estadocivil.getSelectedItem().toString();
                
                //System.out.println(estadocivilempleado);
                if (estadocivilempleado.equals("Soltero")){
                    estadocivilempleado = "S";
                } else if (estadocivilempleado.equals("Casado")){
                     estadocivilempleado = "C";
                } else if (estadocivilempleado.equals("Viudo")){
                    estadocivilempleado = "V";
                } else {
                    estadocivilempleado = null;
                }
                //System.out.println(estadocivilempleado);
                
                
                //si estan llenos todos los campos ejecutamos la query
                
                //agregar reglas de negocio
                String textoerror = null;
                if (departamentoempleado == null){
                    textoerror = "Debe escoger un Departamento";
                }
                if (estadocivilempleado == null){
                    textoerror = "Debe escoger un Estado Civil";
                }
                if (codigoempleado > 100 || codigoempleado <= 0){
                    textoerror = "El código debe ser mayor a 0 y menor que 100";
                }
                if (interfazagregar.tf_celular.getText().length() != 9){
                    textoerror = "El celular debe contener 9 dígitos";
                }
                if (sueldoempleado < 120000){
                    textoerror = "El sueldo debe ser mayor a 120000";
                }
                
                
                if (departamentoempleado != null &&
                    estadocivilempleado != null &&
                    codigoempleado > 0 &&
                    sueldoempleado > 0 &&
                    nombreempleado.length() > 0 &&
                    apellidoempleado.length() > 0 &&
                    celularempleado > 0 &&
                    emailempleado.length() > 0 &&
                    rutempleado.length() > 0) {
                    // hacer la query
                    // int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo, String estadocivil, String departamento
                    if (this.modelo.agregar(codigoempleado, rutempleado, nombreempleado, apellidoempleado, celularempleado, emailempleado, sueldoempleado, estadocivilempleado, departamentoempleado)){
                        JOptionPane.showMessageDialog(null, "Se ha agregado el registro", "Agregar Empleado", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "El código debe ser único", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } else {
                    if (textoerror == null){
                        JOptionPane.showMessageDialog(null, "Por favor llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, textoerror, "Error", JOptionPane.ERROR_MESSAGE);          
                    }
                }

            break;
            case boton_eliminar:
               if (interfazeliminar.tf_eliminar.getText().length() > 0){
                    //asdf
                   if(this.modelo.eliminar(Integer.valueOf(interfazeliminar.tf_eliminar.getText())) == true) {
                       //eliminado
                       JOptionPane.showMessageDialog(null, "Se ha eliminado el empleado "+interfazeliminar.tf_eliminar.getText(), "Empleado Eliminado", JOptionPane.INFORMATION_MESSAGE);
                   } else {
                       JOptionPane.showMessageDialog(null, "No existe un empleado con ese código", "Error", JOptionPane.WARNING_MESSAGE);
                   }
                }
            break;
            case boton_mostrar:
                if (interfazmostrar.tf_buscar.getText().length() > 0){
                    //aa
                    interfazmostrar.tabla.setModel(this.modelo.buscar(Integer.valueOf(interfazmostrar.tf_buscar.getText())));
                    if (this.modelo.buscar(Integer.valueOf(interfazmostrar.tf_buscar.getText())).getValueAt(0, 0) == null){
                        JOptionPane.showMessageDialog(null, "No existe una emplado con ese código", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    interfazmostrar.tabla.setModel(this.modelo.mostrar());
                }
                
            break;
            case boton_modificar:
                //codigo
                int codigoempleado1 = 0;
                if (interfazmostrar.tf_codigo1.getText().length() > 0){
                    codigoempleado1 = Integer.valueOf(interfazmostrar.tf_codigo1.getText());
                }
                
                // nombre
                String nombreempleado1 = interfazmostrar.tf_nombre1.getText();
                
                // apellido
                String apellidoempleado1 = interfazmostrar.tf_apellido1.getText();
                
                 //celular
                int celularempleado1 = 0;
                if (interfazmostrar.tf_celular1.getText().length() > 0){
                    celularempleado1 = Integer.valueOf(interfazmostrar.tf_celular1.getText());
                }
                
                 //rut
                String rutempleado1 = interfazmostrar.tf_rut1.getText();
                
                // email
                String emailempleado1 = interfazmostrar.tf_email1.getText();
                
                //sueldobruto
                int sueldoempleado1 = 0;
                if (interfazmostrar.tf_sueldo1.getText().length() > 0){
                    sueldoempleado1 = Integer.valueOf(interfazmostrar.tf_sueldo1.getText());
                }
                
                //departamento
                String departamentoempleado1 = null;
                if (!interfazmostrar.combo_departamento1.getSelectedItem().toString().equals("Departamento")){
                    departamentoempleado1 = interfazmostrar.combo_departamento1.getSelectedItem().toString();
                }
               
                //estado civil
                String estadocivilempleado1 = interfazmostrar.combo_estadocivil1.getSelectedItem().toString();
                
                //System.out.println(estadocivilempleado);
                if (estadocivilempleado1.equals("Soltero")){
                    estadocivilempleado1 = "S";
                } else if (estadocivilempleado1.equals("Casado")){
                     estadocivilempleado1 = "C";
                } else if (estadocivilempleado1.equals("Viudo")){
                    estadocivilempleado1 = "V";
                } else {
                    estadocivilempleado1 = null;
                }
                
                
                //si estan llenos todos los campos ejecutamos la query
                
                //agregar reglas de negocio
                String textoerror1 = null;
                if (departamentoempleado1 == null){
                    textoerror1 = "Debe escoger un Departamento";
                }
                if (estadocivilempleado1 == null){
                    textoerror1 = "Debe escoger un Estado Civil";
                }
                if (codigoempleado1 > 100 || codigoempleado1 <= 0){
                    textoerror = "El código debe ser mayor a 0 y menor que 100";
                }
                if (interfazmostrar.tf_celular1.getText().length() != 9){
                    textoerror1 = "El celular debe contener 9 dígitos";
                }
                if (sueldoempleado1 < 120000){
                    textoerror1 = "El sueldo debe ser mayor a 120000";
                }
                
                
                if (departamentoempleado1 != null &&
                    estadocivilempleado1 != null &&
                    codigoempleado1 > 0 &&
                    sueldoempleado1 > 0 &&
                    nombreempleado1.length() > 0 &&
                    apellidoempleado1.length() > 0 &&
                    celularempleado1 > 0 &&
                    emailempleado1.length() > 0 &&
                    rutempleado1.length() > 0) {
                    // hacer la query
                    // int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo, String estadocivil, String departamento
                    this.modelo.modificar(codigoempleado1, rutempleado1, nombreempleado1, apellidoempleado1, celularempleado1, emailempleado1, sueldoempleado1, estadocivilempleado1, departamentoempleado1);
                    JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "Modificar Empleado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (textoerror1 == null){
                        JOptionPane.showMessageDialog(null, "Por favor llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, textoerror1, "Error", JOptionPane.ERROR_MESSAGE);          
                    }
                }
                interfazmostrar.tabla.setModel(this.modelo.mostrar());
                
            break;
            case boton_limpiar: // consulta 3
                interfazagregar.tf_apellido.setText(null);
                interfazagregar.tf_celular.setText(null);
                interfazagregar.tf_codigo.setText(null);
                interfazagregar.tf_email.setText(null);
                interfazagregar.tf_nombre.setText(null);
                interfazagregar.tf_rut.setText(null);
                interfazagregar.tf_sueldo.setText(null);
                interfazagregar.combo_departamento.setSelectedIndex(0);
                interfazagregar.combo_estadocivil.setSelectedIndex(0);
                interfazagregar.tf_codigo.grabFocus();
            break;
            case boton_redes:
                interfazmostrar.setVisible(true);
                interfazmostrar.tabla.setModel(this.modelo.mostrar_redes());
                JOptionPane.showMessageDialog(null, "Mostrando empleados del departamento de Redes", "Mostrar Empleados", JOptionPane.INFORMATION_MESSAGE);
            break;
            case boton_bruto:
               if (modelo.eliminar_120()){
                   JOptionPane.showMessageDialog(null, "Se han eliminado los empleados", "Eliminar Empleado", JOptionPane.INFORMATION_MESSAGE);
               } else {
                    JOptionPane.showMessageDialog(null, "No hay empleados con sueldo igual a 120000", "Error", JOptionPane.ERROR_MESSAGE);  
               }
            break;
            case boton_aumentar:
                if (modelo.aumentar_10()){
                   JOptionPane.showMessageDialog(null, "Se han aumentado los sueldos en 10%", "Modificar Sueldos", JOptionPane.INFORMATION_MESSAGE);
               } else {
                    JOptionPane.showMessageDialog(null, "No se han modificado los sueldos", "Error", JOptionPane.ERROR_MESSAGE);  
               }
            break; 
        }  
    }
    
    //asignamos acciones cuando se clickea la tabla
    @Override
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1){
            //Muestro datos de producto a modificar
             int fila = interfazmostrar.tabla.rowAtPoint(e.getPoint());
             if (fila > -1){
                /* aqui poblamos los campos segun el modelo de la tabla */
                
                /*
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "apellido" );
                data[i][3] = res.getString( "rut" );
                data[i][4] = res.getString( "celular" );
                data[i][5] = res.getString( "email" );
                data[i][6] = res.getString( "sueldo_bruto" );
                data[i][7] = res.getString( "est_civil" );
                data[i][8] = res.getString( "nom_depto" );
               */ 
               
               /* interfazmostrar.tf_codigo1.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 0) ));
                interfazmostrar.tf_nombre1.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 1) ));
                interfazmostrar.tf_apellido1.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 2) )); 
                interfazmostrar.tf_rut1.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 3) ));
                interfazmostrar.tf_celular1.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 4) ));
                interfazmostrar.tf_email1.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 5) ));
                interfazmostrar.tf_sueldo1.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 6) ));
                */
                /* estado civil */
               /* if (interfazmostrar.tabla.getValueAt(fila, 7).equals("S")){
                    interfazmostrar.combo_estadocivil1.setSelectedItem("Soltero");
                } else if (interfazmostrar.tabla.getValueAt(fila, 7).equals("V")) {
                    interfazmostrar.combo_estadocivil1.setSelectedItem("Viudo");
                }  else if (interfazmostrar.tabla.getValueAt(fila, 7).equals("C")) {
                    interfazmostrar.combo_estadocivil1.setSelectedItem("Casado");
                }*/
                
                /* DEPARTAMENTO */
                /*interfazmostrar.combo_departamento1.setSelectedItem(interfazmostrar.tabla.getValueAt(fila, 8));*/
                
                /*
             }
                
        }*/
    
    
    }
    
    /* de aqui para abajo no se usa */
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
