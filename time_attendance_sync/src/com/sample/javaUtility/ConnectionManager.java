package com.sample.javaUtility;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager
{
	public static Connection connection=null;
	
	public static Connection getConnection()
	{
		try
		{ 
			if(Utils.databaseType.equals("MICROSOFT_ACCESS"))
			{
				String databaseUrl = Utils.connectionUrl.replace("DATABASE_PATH", Utils.databasePath);
				connection = DriverManager.getConnection(databaseUrl);
			}
			else if(Utils.databaseType.equals("MICROSOFT_SQL_SERVER")) 
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				String databaseUrl = Utils.connectionUrl.replace("SERVER_NAME", Utils.serverName);
				connection = DriverManager.getConnection(databaseUrl,Utils.userName,Utils.password);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		} 
		return connection;
	}
	
	public static void closeConnection(Connection connection)
	{
		try 
		{
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
				