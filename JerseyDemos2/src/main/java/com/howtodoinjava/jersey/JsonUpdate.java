package com.howtodoinjava.jersey;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.json.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


//Path para haceder la aplicacion.
@Path("Jsonpost")

public class JsonUpdate {
	//Metodo rest utilizado en este caso post por obtener informacion de el usuario para hacer cambios en una base de datos.
	@POST
	//Consume un Json para introducirlo en la base de datos.
	 @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	    @Path("/{param}")
	//Una vez introducido responde por pantalla si ha funcionado o no y lo que ha cambiado.
	    public Response postMsg(@PathParam("param") String msg) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver"); 
			Class.forName("org.json.JSONObject"); 
	    	Connection co= DriverManager.getConnection("jdbc:mysql://localhost:3306/placas?serverTimezone=UTC" , "root", "root");
	    	Statement s = co.createStatement();
	    	String statement = null;
	  
	    	JSONObject obj = new JSONObject(msg);
//En este condicional se ve si requiere ir a la tabla Placasip la cual tiene solo 2 columnas o la tabla placastopic con varias pero para no
//crear una clase adicional las he puesto en el mismo servicio.
	    if (obj.length()==2) {
	    	statement = "Insert into placasip(placaid, computerip) VALUES ('"+obj.getString("idPlaca")+"','"+ obj.getString("ip")+"')";
	    	
		} else {
			statement = "Insert into placastopic VALUES ("+"'"+obj.getString("idPlaca")+"'"+","+ 
		"'"+obj.getString("topic")+"'"+","+obj.getBoolean("luz")+","+obj.getBoolean("presencia")+","+obj.getBoolean("temperatura")+","+obj.getBoolean("humedad")+","+obj.getBoolean("ordenadores")+")";
		
		}
	//una vez se crea el statement de insertar con el json se procede a ejecutarlo a la base de datos y despues cerrarla.
	    	s.executeUpdate(statement);
	        s.close();
	        
	        // Si el insert es exitoso se muestra por pantalla que ha funcionado.
	        String output = "POST:Jersey say : " + statement  ;
	        return Response.status(200).entity(output).build();
	    }

}
