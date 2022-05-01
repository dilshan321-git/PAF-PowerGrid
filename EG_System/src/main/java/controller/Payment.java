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

@Path("/payment")
public class Payment {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_txt)
	{
		PaymentModel pay =new PaymentModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("payment_method").getAsString()!=""&&app.get("address").getAsString()!=""&&app.get("nic").getAsString()!=""&&app.get("amount").getAsString()!="") {

			pay.add_Payment(app.get("payment_method").getAsString(),app.get("address").getAsString(),app.get("nic").getAsString(),Double.parseDouble(app.get("amount").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(pay.getResults()));
			
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

		PaymentModel pay =new PaymentModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();

		if(app.get("id").getAsString()!=""&&app.get("payment_method").getAsString()!=""&&app.get("address").getAsString()!=""&&app.get("nic").getAsString()!=""&&app.get("amount").getAsString()!="") {

			pay.edit_Payment(Integer.parseInt(app.get("id").getAsString()),app.get("payment_method").getAsString(),app.get("address").getAsString(),app.get("nic").getAsString(),Double.parseDouble(app.get("amount").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(pay.getResults()));
	
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

		PaymentModel pay =new PaymentModel();
		JsonObject app = new JsonParser().parse(json_txt).getAsJsonObject();
		if(app.get("id").getAsString()!="") {
	
			pay.delete_Payment(Integer.parseInt(app.get("id").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(pay.getResults()));
	
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
		PaymentModel pay =new PaymentModel();
		return pay.get_Payment();
	}
	
}
