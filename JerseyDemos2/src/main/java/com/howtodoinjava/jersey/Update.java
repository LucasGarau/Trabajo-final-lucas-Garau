package com.howtodoinjava.jersey;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
//Path utilizado para acceder a la aplicacion
@Path("Update/{message}")
public class Update
{
	//metodo utilizado rest.
    @GET
    public Response getMsg(@PathParam("message") String msg) throws SQLException, ClassNotFoundException
	{
  
    	Class.forName("com.mysql.jdbc.Driver"); 
    	Connection co= DriverManager.getConnection("jdbc:mysql://localhost:3306/placas?serverTimezone=UTC" , "root", "root");
    	//En este caso la aplicacion simplemente ejecuta desde el plain text comandos de mysql a la base de datos.
    	Statement s = co.createStatement();
    	s.executeUpdate(msg);
        s.close();
    	String output = "Cambio :" + msg;
		return Response.status(200).entity(output).build();
	}
}
