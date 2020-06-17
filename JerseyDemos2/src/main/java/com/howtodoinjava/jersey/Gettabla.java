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
@Path("Tablaplaca/{message}")
public class Gettabla
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
    	//Mysql statement que recibe la id de la placa que se quiere buscar por la url y busca el resultado.
    	ResultSet rs = s.executeQuery ("select * from placastopic where IDPlaca="+"'"+msg+"'");
    	rs.next();
    	String output =rs.getString(2)+" "+ rs.getBoolean(3) +" "+ rs.getBoolean(4) +" "+ rs.getBoolean(5) +" "+ rs.getBoolean(6)+" "+ rs.getBoolean(7);
	//Respuesta de el html mostrando asi el resultado de mysql por pantalla.
		return Response.status(200).entity(output).build();
	}
}
