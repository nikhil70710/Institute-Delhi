package com.development.nielit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/rest")
public class WebServices {
@POST
@Path("/insert")
@Consumes(MediaType.TEXT_HTML)
public String insertDb(@PathParam("name")String name,@PathParam("mobile")String mobile,@PathParam("email")String email,@PathParam("city")String city)
{
	try   
    {   
	   String query = "insert into enquiry "+"(name,mobile,email,city) VALUES"+"(?,?,?,?)";
       Class.forName("oracle.jdbc.driver.OracleDriver");        
       Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
       PreparedStatement st = con.prepareStatement(query);
       st.setString(1,name);
       st.setString(2,mobile);
       st.setString(3, email);
       st.setString(4, city);
       st.executeQuery();
       con.close();
    }     
    catch (Exception e)   
    {     
       System.out.println(e.getMessage());    
    } 
    return"Record inserted successfully";
 }
@GET
@Path("/show")
@Produces(MediaType.APPLICATION_JSON)
public String displayRecord(){
	PreparedStatement st = null;
	try 
     {
		Class.forName("oracle.jdbc.driver.OracleDriver");        
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
		String query = "select * from enquiry";
         st = con.prepareStatement(query);
         st.executeQuery();
         con.close();
     } 
     catch (Exception e) 
     {
         System.out.println(e.getMessage());
     }   
    return st.toString(); 
 }
}

