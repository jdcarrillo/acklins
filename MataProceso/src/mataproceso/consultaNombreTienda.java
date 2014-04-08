/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mataproceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcarrillo
 */
public class consultaNombreTienda {
   int tamano=0;
   int cont=0;
     
//  @SuppressWarnings("empty-statement")
//    public void con() throws SQLException {
//        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://acklins.dyndns.org/dblicenciaspixel", "root", "ack82456")) {
//            Statement s = conexion.createStatement();
//            ResultSet rs = s.executeQuery("select * from tienda");
//            for (tamano=0; rs.next (); tamano++);
//            String[] estado= new String[tamano];
//            System.out.println(tamano);
//            
//            rs.beforeFirst();
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
//                estado[cont]=rs.getString(3);
//                System.out.println("valor: "+estado[cont]);
//                //cont=cont+1;
//            }
//            //if (d=="0") System.out.println("valor: "+d);
//            conexion.close();
//        }
//        
//    }
    public boolean encuentraTienda(String nombre) {
        ComprobarConexion comprobar = new ComprobarConexion();
        boolean existeTienda=false;
        conecta conexion = new conecta();
        if (comprobar.ComprobarConexion()==true && conexion.conn!=null) {        
            
            Statement query;
            
            try {
                query = conexion.conn.createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM tienda WHERE tienda="+"'"+nombre+"'");
                existeTienda = rs.next();
                conexion.desconectar();

            } catch (SQLException ex) {
                Logger.getLogger(consultaNombreTienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            //conexion.desconectar();
        
        return existeTienda;
}
    public String encuentraStatusTienda(String nombre) {
        ComprobarConexion comprobar = new ComprobarConexion();
        String status = "";
        conecta conexion = new conecta();
        if (comprobar.ComprobarConexion() == true && conexion.conn!=null) {
            
            try {
                Statement query = conexion.conn.createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM tienda WHERE tienda=" + "'" + nombre + "'");
                rs.next();
                status = rs.getString(3);
                //      while (rs.next()){
                //        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
                //  }
                conexion.desconectar();

            } catch (SQLException ex) {
                Logger.getLogger(consultaNombreTienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //conexion.desconectar();
        return status;
    }
    
    public String encuentraNombreTienda(String nombre) {
        ComprobarConexion comprobar = new ComprobarConexion();
        
        conecta conexion = new conecta();
        if (comprobar.ComprobarConexion() == true && conexion.conn!=null) {
            
            try {
                Statement query = conexion.conn.createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM tienda WHERE tienda=" + "'" + nombre + "'");
                rs.next();
                nombre = rs.getString(3);
                //      while (rs.next()){
                //        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
                //  }
                conexion.desconectar();

            } catch (SQLException ex) {
                Logger.getLogger(consultaNombreTienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //conexion.desconectar();
        return nombre;
    }
      
      
}

    