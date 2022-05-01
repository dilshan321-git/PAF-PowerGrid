package controller;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import org.json.simple.*;

import model.*;

import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/user")
public class User {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_txt)
	{

		UserModel user =new UserModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("name").getAsString()!=""&&app.get("email").getAsString()!=""&&app.get("address").getAsString()!="") {

			user.add_User(app.get("name").getAsString(),app.get("email").getAsString(),app.get("address").getAsString());
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(user.getResults()));
			
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", 0);
			
			return json.toString();
			
		}
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String json_txt)
	{

		UserModel user =new UserModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("id").getAsString()!=""&&app.get("name").getAsString()!=""&&app.get("email").getAsString()!=""&&app.get("address").getAsString()!="") {

			user.edit_User(Integer.parseInt(app.get("id").getAsString()),app.get("name").getAsString(),app.get("email").getAsString(),app.get("address").getAsString());
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(user.getResults()));
	
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", 0);
			
			return json.toString();
			
		}
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String json_txt)
	{

		UserModel user =new UserModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();
		if(app.get("id").getAsString()!="") {
	
			user.delete_User(Integer.parseInt(app.get("id").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(user.getResults()));
	
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", 0);
			
			return json.toString();
			
		}
		
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String json_txt)
	{
		UserModel user =new UserModel();
		return user.get_User();
	}
	
}
