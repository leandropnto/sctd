/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lpinto
 */
public class Utilities {
 
    public static boolean isInt(String v) {  
      try {  
         Integer.parseInt(v);  
         return true;  
      } catch (Exception e) {  
         return false;  
      }  
   }  

    
    public static boolean validaDDD(String ddd){
        Pattern pattern = Pattern.compile("^(10)$|^([1-9]{2})$");
        Matcher matcher = pattern.matcher(ddd);
        return matcher.find();
        
    }
    
    public static boolean validaEmail(String email){
        Pattern pattern = Pattern.compile("^([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();        
    }

    public static boolean validaTelefoneCelular(String telefone) {
        Pattern pattern = Pattern.compile("^[6-9]{1}[0-9]{3}[0-9]{4}$");
        Matcher matcher = pattern.matcher(telefone);
        return matcher.find();          
    }
    
    public static boolean validaTelefoneFixo(String telefone) {
        Pattern pattern = Pattern.compile("^[2-5][0-9]{3}[0-9]{4}$");
        Matcher matcher = pattern.matcher(telefone);
        return matcher.find();          
    }
    
    

}
