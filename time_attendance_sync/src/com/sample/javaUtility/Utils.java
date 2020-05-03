package com.sample.javaUtility;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils 
{
	public static String connectionUrl = null;
	public static String databasePath = null;
	public static int timeIntervalForSyncInSeconds;
	public static String attendancePushUrl = null;
	public static String databaseType = null;
	public static String serverName = null;
	public static String userName = null;
	public static String password = null;
	public static String databaseName = null;
	public static String authorization = null;
	public static String syncDate = null;
	public static String tempDate = null;
	public Utils()
	{
		prerequisite();
	}
	public static void prerequisite()
	{
		JSONParser parser = new JSONParser();
		try 
		{
			JSONObject jsonObjectForParsing = (JSONObject) parser.parse(new FileReader("../src/Utils.json"));
			
			if(jsonObjectForParsing.containsKey("MICROSOFT_SQL_SERVER"))
			{
				JSONObject sqlServerObj = (JSONObject)jsonObjectForParsing.get("MICROSOFT_SQL_SERVER");
				connectionUrl = (String) sqlServerObj.get("connectionUrl");
				serverName = (String) sqlServerObj.get("serverName");
				userName = (String) sqlServerObj.get("userName");
			    password = (String) sqlServerObj.get("password");
			    databaseName = (String) sqlServerObj.get("databaseName");
			    databaseType = "MICROSOFT_SQL_SERVER";
			    syncDate = (String) sqlServerObj.get("syncDate");
			}
			else if(jsonObjectForParsing.containsKey("MICROSOFT_ACCESS"))
			{
				JSONObject accessObj = (JSONObject)jsonObjectForParsing.get("MICROSOFT_ACCESS");
				connectionUrl = (String) accessObj.get("connectionUrl");
				databasePath = (String) accessObj.get("databasePath");
				databaseType = "MICROSOFT_ACCESS";
			    syncDate = (String) accessObj.get("syncDate");
			}
			String temp = (String) jsonObjectForParsing.get("timeIntervalForSyncInSeconds");
		    timeIntervalForSyncInSeconds = Integer.parseInt(temp);
		    authorization = (String) jsonObjectForParsing.get("authorization");
		    attendancePushUrl = (String) jsonObjectForParsing.get("attendancePushUrl");	
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
	