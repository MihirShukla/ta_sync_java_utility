package com.sample.javaUtility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DatabaseManagerForSQLServer 
{
	public static String queryStringToCreateTable_dbo_Pushed_Log_Info = "CREATE TABLE dbo.PushedLogInfo("
											         + "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, "
											         + "fromTime DATETIME, "
											         + "toTime DATETIME, "
											         + "offset INT, "
											         + "limit INT, "
											         + "noOfRecords INT, "
											         + "status BOOLEAN)"; 
	
	public static String queryStringToSelectFrom_dbo_Pushed_Log_Info = "select top 1 * from dbo.PushedLogInfo ORDER BY id DESC";
	
	public static String queryStringToInsertIn_dbo_Pushed_Log_Info = "insert into dbo.PushedLogInfo(fromTime,toTime,offset,limit,noOfRecords,status) values(?,?,?,?,?,?)";
	
	public static String prepareQueryToDeleteIn_dbo_Pushed_Log_Info(LocalDateTime fromTime)
	{
		String queryString = "";
		queryString = "DELETE FROM dbo.PushedLogInfo WHERE>"+fromTime;
		return queryString;
	}
	
	public static String prepareQueryFor_Device_Logs(LocalDateTime fromTime,LocalDateTime toTime,int offset,int limit)
	{
		Timestamp fromTimestamp = DateUtils.getTimestamp(ZonedDateTime.of(fromTime, ZoneId.systemDefault()));
		Timestamp toTimestamp = DateUtils.getTimestamp(ZonedDateTime.of(toTime, ZoneId.systemDefault()));
		LocalDateTime tempTime = DateUtils.getTempTimeForQuery(fromTime);
		String queryString = "";
		queryString = "SELECT LogDate,UserId,DeviceId,DownloadDate,ROW_NUMBER() OVER (order by LogDate) ROW_NUM FROM(";
		do{
			int month = tempTime.getMonthValue();
			int year = tempTime.getYear();
			queryString += "SELECT * FROM DeviceLogs_"+month+"_"+year+" UNION ALL";
			tempTime = tempTime.plusMonths(1);
		}while(toTime.isAfter(tempTime));
		
		queryString += " )A";
		queryString += "WHERE DownloadDate>='"+fromTimestamp+"' AND DownloadDate<'"+toTimestamp+"'";
		queryString += "ORDER BY LogDate";
		queryString += "OFFSET"+offset+"ROWS";
		queryString += "FETCH NEXT "+limit+"ROWS ONLY";
		System.out.println(queryString);
		
		return queryString;
	}
}
