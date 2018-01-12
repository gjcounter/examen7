/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Duoc UC
 */
public class Indicadores  {
    

    public JsonObject objeto;

   public Indicadores () throws MalformedURLException, IOException {
      
         URL url = new URL("http://mindicador.cl/api/");
        //ahora obtenemos el objeto json a partir de esa url
        InputStream entrada = url.openStream();
        JsonReader reader = Json.createReader(entrada);
        this.objeto = reader.readObject();
    }

    
    public Float getValorDia(String moneda) throws MalformedURLException, IOException{
        //seleccionaremos la parte del objeto que nos interesa e imprimiremos su valor
        float valor = Float.parseFloat(this.objeto.getJsonObject(moneda).get("valor").toString());
        System.out.println("Valor: "+ valor);
        System.out.println("JSON consumido :)");
        return valor;
    }

}
