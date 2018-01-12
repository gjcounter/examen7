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
    
    private JsonObject objeto;
    private JsonObject objeto2;

    public Indicadores () {}

   public Indicadores (String nombre) throws MalformedURLException, IOException {
        URL url = new URL("http://mindicador.cl/api/"+nombre); // nombre = dolar / euro
       
        InputStream entrada = url.openStream();
        JsonReader reader = Json.createReader(entrada);
        this.objeto = reader.readObject();
        
        
         URL url2 = new URL("http://mindicador.cl/api");
         
         InputStream entrada2= url2.openStream();
         JsonReader reader2 = Json.createReader(entrada2);
         this.objeto2= reader2.readObject();
    }

    public JsonObject getObjeto() {
        return objeto;
    }
    
    public float[] getValores (){
        float[] valores;
        valores = new float[objeto.getJsonArray("serie").size()];
        
        for (int i = 0; i < objeto.getJsonArray("serie").size(); i++){
            valores[i] = Float.parseFloat(objeto.getJsonArray("serie").getJsonObject(i).get("valor").toString());
        }
         return valores;
    }
    
    public String[] getFechas(){
        String fechas[];
        fechas = new String[objeto.getJsonArray("serie").size()];
        
        for (int i = 0; i < objeto.getJsonArray("serie").size(); i++){
            fechas[i] = objeto.getJsonArray("serie").getJsonObject(i).get("fecha").toString();
        }
         return fechas;
    }
    
    public float getValorDia(String moneda){
        float valor = 0;
        valor = Float.parseFloat(objeto2.getJsonObject(moneda).get("valor").toString());

    return valor;
    }

}
