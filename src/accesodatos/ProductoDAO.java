/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import negocio.Producto;
import servicio.sql;

/**
 *
 * @author Jazna
 */
public class ProductoDAO {
    private Producto p;

    public ProductoDAO() {
    }

    public ProductoDAO(Producto p) {
        this.p = p;
    }
    
    sql conectara = new sql();
    
    /**
     * Método que usted debe implementar
     */
    public boolean grabar(){
        boolean valor = false;
        //Se arma la consulta
        String q=" INSERT INTO examen7.producto(pr_id,pr_nombre,pr_moneda,pr_valormoneda)"
                + "VALUES (9999,'aaaaa','dolar',111.11);";
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            if (pstm.executeUpdate() == 1){
                valor = true;
            }
                     
        }catch(SQLException e){
            //System.err.println( e.getMessage() );
        }
        return valor;
    }
    
    public DefaultTableModel mostrar(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID Producto","Nombre","Moneda","Precio"};
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM examen7.producto");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][9];
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM examen7.producto");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){ 
                data[i][0] = res.getString( "pr_id" );
                data[i][1] = res.getString( "pr_nombre" );
                data[i][2] = res.getString( "pr_moneda" );
                data[i][3] = res.getString( "pr_valormoneda" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    } 
}
