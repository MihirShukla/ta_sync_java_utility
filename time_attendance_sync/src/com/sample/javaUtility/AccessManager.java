package com.sample.javaUtility;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public class AccessManager implements AttendanceUtilsInterface
{
	public LocalDateTime[] getSyncRange()
	{
		LocalDateTime returnData[] = new LocalDateTime[2];
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;
		String queryString = null;
		Statement statement = null;
		ResultSet resultSet = null;
		boolean status = false;
		int counter =5;
		//String tempDate = null;
		if(Utils.tempDate.equals(""))
		{
			Utils.tempDate = Utils.syncDate;
		}
		Connection connection = ConnectionManager.getConnection();
		try 
		{
			if(Utils.syncDate.equals(Utils.tempDate))
			{
				queryString = DatabaseManagerForAccess.queryStringToSelectFrom_Pushed_Log_Info;
				if(connection != null)
				{	
					try
					{
						statement = connection.createStatement();
						resultSet = statement.executeQuery(queryString);
						status = true;
					}
					finally
					{
						this.executeExponentialBlock(queryString,statement,resultSet,status,counter);
					} 
				}
				
				if(resultSet != null)
				{
					while(resultSet.next())
					{ 
						fromDate = resultSet.getTimestamp("toTime").toLocalDateTime();
						System.out.println("Inside function "+fromDate);
					}
				}
				if(fromDate == null)
				{
					fromDate = DateUtils.getDateTimeIntoLocalDateTime(Utils.syncDate);
					System.out.println("from "+fromDate);
				}
			}
	        
			else
			{
				fromDate = DateUtils.getDateTimeIntoLocalDateTime(Utils.syncDate);
				System.out.println("from "+fromDate);
				queryString = DatabaseManagerForAccess.prepareQueryToDeleteIn_Pushed_Log_Info(fromDate);
				try
				{
					statement = connection.createStatement();
					resultSet = statement.executeQuery(queryString);
					Utils.tempDate = Utils.syncDate;
					status = true;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					this.executeExponentialBlock(queryString,statement,resultSet,status,counter);
				}
			}
			ConnectionManager.closeConnection(connection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		toDate = fromDate.plusSeconds(Utils.timeIntervalForSyncInSeconds);
        System.out.println("from "+fromDate);
        System.out.println("to "+toDate);
		returnData[0] = fromDate;
		returnData[1] = toDate;
	
		return returnData;
	}
	
	public JSONArray fetchPunchRecords(LocalDateTime fromTime,LocalDateTime toTime,int offset,int limit)
	{
		JSONArray jsonArrayForFetchRecords = new JSONArray(); 
		String queryString = DatabaseManagerForAccess.prepareQueryFor_Device_Logs(fromTime, toTime, offset, limit);
		System.out.println(queryString);
		Connection connection = ConnectionManager.getConnection();
		try
		{
			Statement statement = null;
			ResultSet resultSet = null;
			boolean status = false;
			int counter =5;
			if(connection != null)
			{
				try
				{
					statement = connection.createStatement();
					resultSet = statement.executeQuery(queryString);
					status=true;
				}
				finally
				{
					this.executeExponentialBlock(queryString,statement,resultSet,status,counter);
				}
			    int i=1;
				while(resultSet.next())
				{
		    		JSONObject formDetailsJson = new JSONObject();
	    			formDetailsJson.put("Employeeid", resultSet.getString("UserId").trim());
	    			String LogDateeString = DateUtils.getDateTimeStringFromTimestamp(resultSet.getTimestamp("LogDate"));
		    		formDetailsJson.put("eventtime",LogDateeString);
		    		try 
		    		{
		    			String downloadDateString = DateUtils.getDateTimeStringFromTimestamp(resultSet.getTimestamp("DownloadDate"));
		    			formDetailsJson.put("downloadDate", downloadDateString);
		    		}
		    		catch(Exception e)
		    		{
		    			System.out.println(e);
		    			String downloadDateString = DateUtils.getDateTimeStringIntoMillis();
		    			formDetailsJson.put("downloadDate", downloadDateString);
		    		}
		    		formDetailsJson.put("DeviceId", resultSet.getInt("DeviceId"));
	    			formDetailsJson.put("id", resultSet.getString("DeviceLogId"));
	    			i += 1;
		    		jsonArrayForFetchRecords.put(formDetailsJson);
		    	}
				ConnectionManager.closeConnection(connection); 
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return jsonArrayForFetchRecords;
	}
	
	public void addSyncRecord(LocalDateTime fromDateTime, LocalDateTime toDateTime, int offset, int limit , int noOfRecords,boolean status)
	{
		String insertQueryString = null;
		insertQueryString = DatabaseManagerForAccess.queryStringToInsertIn_Pushed_Log_Info;
		PreparedStatement preparedStatement = null;
		boolean statusInfo = false;
		int counter =5;
		Connection connection = ConnectionManager.getConnection();
		try
		{
			if(connection != null)
			{
				preparedStatement =connection.prepareStatement(insertQueryString);
				Timestamp fromTimestamp = DateUtils.getTimestamp(ZonedDateTime.of(fromDateTime, ZoneId.systemDefault()));
				preparedStatement.setTimestamp(1, fromTimestamp);
				Timestamp toTimestamp = DateUtils.getTimestamp(ZonedDateTime.of(toDateTime, ZoneId.systemDefault()));
				preparedStatement.setTimestamp(2, toTimestamp);
				preparedStatement.setInt(3,offset);//1 specifies the firesultSett parameter in the query  
				preparedStatement.setInt(4,limit);
				preparedStatement.setInt(5,noOfRecords);
				int updatedRecords=preparedStatement.executeUpdate();
				statusInfo = true;
				System.out.println("Records added "+updatedRecords);
			} 
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			if(statusInfo == false)
			{
				while(counter>0)
				{
					try 
					{
						TimeUnit.SECONDS.sleep(5);
						int updatedRecords=preparedStatement.executeUpdate();
						statusInfo = true;
					}
					catch (Exception ex) 
					{
						statusInfo = false;
						System.out.println(ex);
					}
					if(statusInfo == false)
					{
						counter--;
					}
					else
					{ 
						counter = 0;
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
	}
	
	public void executeExponentialBlock(String queryString,Statement statement,ResultSet resultSet,boolean status,int counter)
	{
		if(status == false)
		{
			while(counter>0)
			{
				try 
				{
					TimeUnit.SECONDS.sleep(5);
					resultSet = statement.executeQuery(queryString);
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
	}
}
