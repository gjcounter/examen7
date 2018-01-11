/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Duoc UC
 */
public class sql {
    Connection conectar=null;
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection(""
                    + "jdbc:mysql://taller4.mysql.database.azure.com:3306/examen7?useSSL=true&requireSSL=false","fcovvb@taller4","Tabla22#");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conectar;
    }
}