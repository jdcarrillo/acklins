/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package licenciapixel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jcarrillo
 */
public class datosLicencia {
    conecta con;
    
    public datosLicencia(){
        con = new conecta();
    }
    
     public void NuevaTienda(String tienda, boolean status){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("insert into " + 
                    "tienda(tienda,status) " +
                    " values(?,?)");            
            pstm.setString(1, tienda);
            pstm.setBoolean(2, status);                    
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }

     public void updateTienda(String idtienda, String tienda, boolean status){
       try { 
           String sql;
           sql = "update tienda " +
                 "set tienda = ? ," +
                 "status = ? " +                    
                 "where idtienda = ? ";
           try (PreparedStatement pstm = con.getConnection().prepareStatement(sql)) {
               pstm.setString(1, tienda);
               pstm.setBoolean(2, status);  
               pstm.setString(3, String.valueOf(idtienda));
               pstm.execute();
           }            
         }catch(SQLException e){
         System.out.println(e);
      }
   }
   
   public void deleteTienda(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from tienda where idtienda = ?");            
                pstm.setString(1, cod);                   
                pstm.execute();
                pstm.close();   
                updateIncrement(cod);
            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }    
   
      public void updateIncrement(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("ALTER TABLE tienda AUTO_INCREMENT=1");
                pstm.setString(1, cod);                   
                pstm.execute();
                pstm.close();            
            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }
   
   
    
 /*obtenemos todos los datos de la tabla*/
 public Object [][] getDatos(){
      int registros = 0;
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(1) as total FROM tienda ");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.out.println(e);
      }
      
    Object[][] data = new String[registros][5];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT " +
            " idtienda, tienda, status " +
            " FROM tienda" +
            " ORDER BY idtienda ");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String estCodigo = res.getString("idtienda");
            String estTienda = res.getString("tienda");
            String estStatus = res.getString("status");
            data[i][0] = estCodigo;            
            data[i][1] = estTienda;            
            data[i][2] = estStatus;            
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 } 
}
