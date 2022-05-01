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

@Path("/billing")
public class Billing {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_txt)
	{

		BillingModel bill =new BillingModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("cname").getAsString()!=""&&app.get("type").getAsString()!=""&&app.get("description").getAsString()!=""&&app.get("date").getAsString()!=""&&app.get("total").getAsString()!="") {

			bill.add_Billing(app.get("cname").getAsString(),app.get("type").getAsString(),app.get("description").getAsString(),app.get("date").getAsString(),Double.parseDouble(app.get("total").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(bill.getResults()));
			
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

		BillingModel bill =new BillingModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("id").getAsString()!=""&&app.get("cname").getAsString()!=""&&app.get("type").getAsString()!=""&&app.get("description").getAsString()!=""&&app.get("date").getAsString()!=""&&app.get("total").getAsString()!="") {

			bill.edit_Billing(Integer.parseInt(app.get("id").getAsString()),app.get("cname").getAsString(),app.get("type").getAsString(),app.get("description").getAsString(),app.get("date").getAsString(),Double.parseDouble(app.get("total").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(bill.getResults()));
	
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

		BillingModel bill =new BillingModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();
		if(app.get("id").getAsString()!="") {
	
			bill.delete_Billing(Integer.parseInt(app.get("id").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(bill.getResults()));
	
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
		BillingModel bill =new BillingModel();
		return bill.get_Billing();
	}
	
}
