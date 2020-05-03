package com.sample.javaUtility;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class AttendanceSync 
{	
	public static void main(String[] args)throws Exception
	{
		UIConfigureJson configJson = new UIConfigureJson();
		String queryStringToCreateTable = null;
		Utils utility = new Utils();
		HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
		AttendanceUtilsInterface attendanceUtils;
		System.out.println("Processing started");
		String javaHome = System.getProperty("java.home");
		int index = javaHome.indexOf("jdk");
		if(index==-1)
		{
			ProcessBuilder processBuilder = new ProcessBuilder("../src/requirement.bat");
			processBuilder.start();
		}
		
		Connection connection = ConnectionManager.getConnection();
		
		if(connection == null)
		{
			System.out.println("Connection could not established");
			return;
		}
		else
		{
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			Statement statement = connection.createStatement();
			ResultSet tables = null;
			boolean status = false;
			int counter = 5;
			if(Utils.databaseType.equals("MICROSOFT_ACCESS"))
			{
				attendanceUtils = new AccessManager();
				try
				{
					tables = databaseMetaData.getTables(null, null, "PushedLogInfo", null);
					if(!(tables.next()))
					{
						queryStringToCreateTable = DatabaseManagerForAccess.queryStringToCreateTable_Pushed_Log_Info;	
						statement.executeUpdate(queryStringToCreateTable);
						status = true;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
			    }
				finally
				{
					if(status == false)
					{
						while(counter>0)
						{
							try 
							{
								TimeUnit.SECONDS.sleep(5);
								statement.executeUpdate(queryStringToCreateTable);
								status = true;
							}
							catch (Exception ex) 
							{
								status = false;
								System.out.println(ex);
							}
							if(status == false)
							{
								counter--;
							}
							else
							{ 
								counter = 0;
							}
						}
					}
					try
					{
						ConnectionManager.closeConnection(connection);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			else
			{
				attendanceUtils = new SQLManager();
				try
				{
					tables = databaseMetaData.getTables(null, null, "dbo.PushedLogInfo", null);
					if(!(tables.next()))
					{
						queryStringToCreateTable = DatabaseManagerForSQLServer.queryStringToCreateTable_dbo_Pushed_Log_Info;
						statement.executeUpdate(queryStringToCreateTable);
						status = true;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
			    }
				finally
				{
					if(status == false)
					{
						while(counter>0)
						{
							try 
							{
								TimeUnit.SECONDS.sleep(5);
								statement.executeUpdate(queryStringToCreateTable);
								status = true;
							}
							catch (Exception ex) 
							{
								status = false;
								System.out.println(ex);
							}
							if(status == false)
							{
								counter--;
							}
							else
							{ 
								counter = 0;
							}
						}
					}
					try
					{
						ConnectionManager.closeConnection(connection);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		LocalDateTime returnData[] = new LocalDateTime[2];
		returnData = attendanceUtils.getSyncRange();
		LocalDateTime fromTime = returnData[0];
		LocalDateTime toTime = returnData[1];
		LocalDateTime nowTime = LocalDateTime.now();
		System.out.println(nowTime);
		
		while(toTime.isBefore(nowTime))
		{
			System.out.println(fromTime);
			System.out.println(toTime);
			int offset = 0;
			int limit = 200;
			System.out.println("NOW:"+nowTime+"  Range:"+fromTime+"__TO__"+toTime);
			JSONArray punchList = null;
			boolean status = false;
			punchList = attendanceUtils.fetchPunchRecords(fromTime, toTime, offset, limit);			
			System.out.println(punchList);
			if(punchList.length() == 0)
			{
				System.out.println("No Data");
				attendanceUtils.addSyncRecord(fromTime, toTime, offset, limit, 0,status);
	            fromTime = toTime;
	            toTime = fromTime.plusSeconds(Utils.timeIntervalForSyncInSeconds);
			}
			else
			{
				while(punchList.length() > 0)
				{
					status = httpConnectionManager.postDataToServer(punchList);
					System.out.println("Status of data push "+status);
					attendanceUtils.addSyncRecord(fromTime, toTime, offset, limit, punchList.length(),status);
					offset = offset + limit;
					punchList =attendanceUtils.fetchPunchRecords(fromTime, toTime, offset, limit);
					System.out.println(punchList);
				}
				fromTime = toTime;
				toTime = fromTime.plusSeconds(Utils.timeIntervalForSyncInSeconds);
			}
		}	
	}
}
