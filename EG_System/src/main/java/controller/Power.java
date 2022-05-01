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

@Path("/power")
public class Power {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_txt)
	{

		PowerConsumption power =new PowerConsumption();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("usage").getAsString()!=""&&app.get("unit_type").getAsString()!=""&&app.get("description").getAsString()!="") {

			power.add_Power(Integer.parseInt(app.get("usage").getAsString()),app.get("unit_type").getAsString(),app.get("description").getAsString());
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(power.getResults()));
			
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

		PowerConsumption power =new PowerConsumption();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("id").getAsString()!=""&&app.get("usage").getAsString()!=""&&app.get("unit_type").getAsString()!=""&&app.get("description").getAsString()!="") {

			power.edit_Power(Integer.parseInt(app.get("id").getAsString()),Integer.parseInt(app.get("usage").getAsString()),app.get("unit_type").getAsString(),app.get("description").getAsString());
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(power.getResults()));
	
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

		PowerConsumption power =new PowerConsumption();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();
		if(app.get("id").getAsString()!="") {
	
			power.delete_Power(Integer.parseInt(app.get("id").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(power.getResults()));
	
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
		PowerConsumption power =new PowerConsumption();
		return power.get_Power();
	}
	
}
