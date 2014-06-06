package licenciapixel;

import java.sql.*;

public class conecta {
   static String bd = "dblicenciaspixel";
   static String login = "root";
   static String password = "ack82456";
   static String url = "jdbc:mysql://acklins.dyndns.org:3306/"+bd;

   Connection conn = null;

   /** Constructor de DbConnection */
   public conecta() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection(url,login,password);
         if (conn!=null){
            System.out.println("Conección a base de datos "+bd+". listo");
         }
      }catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexión*/
   public Connection getConnection(){
      return conn;
   }

   public void desconectar(){
      conn = null;
      System.out.println("La conexion a la  base de datos "+bd+" a terminado");
   }
}
