package com.howtodoinjava.jersey;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
//Path utilizado para entrar en el servicio.
@Path("Tablaip/{message}")
public class Getips
{
	//Metodo rest utilizado en el servicio.
    @GET
    //Metodo de respuesta tras acabar el servicio.
    public Response getMsg(@PathParam("message") String msg) throws SQLException, ClassNotFoundException
	{
  
    	Class.forName("com.mysql.jdbc.Driver"); 
    	//Conexion a la base de datos.
    	Connection co= DriverManager.getConnection("jdbc:mysql://localhost:3306/placas?serverTimezone=UTC" , "root", "root");
    	Statement s = co.createStatement();
    	StringBuffer sb = new StringBuffer("");
    	//QUery de mysql para encontrar todos las ips de la base de datos.
    	ResultSet rs2 = s.executeQuery ("Select COUNT(*) FROM placasip where Placaid='"+msg+"'");
    	rs2.next();
    	//En el caso de no encontrar resultados mostrara nada.
    	if (rs2.getInt(1)!=0) {
    		sb.append(rs2.getInt(1)+" ");
		}
    	ResultSet rs = s.executeQuery ("select * from placasip where Placaid='"+msg+"'");
    	//loop para encontrar todos los resultados
    	 while(rs.next())
         {
             String temp = rs.getString(2) + " ";
             sb.append(temp);
             

         }
   
	String output2=sb.toString();
	//El retunrn de el html modificado con los resultados
		return Response.status(200).entity(output2).build();
	}
}
