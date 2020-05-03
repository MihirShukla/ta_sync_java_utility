package com.sample.javaUtility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DatabaseManagerForAccess
{
	public static String queryStringToCreateTable_Pushed_Log_Info = "CREATE TABLE PushedLogInfo("
											         + "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, "
											         + "fromTime DATETIME, "
											         + "toTime DATETIME, "
											         + "offset INT, "
											         + "limit INT, "
											         + "noOfRecords INT, "
											         + "status BOOLEAN)";
	
	public static String queryStringToSelectFrom_Pushed_Log_Info = "select top 1 * from PushedLogInfo ORDER BY id DESC";
	
	public static String queryStringToInsertIn_Pushed_Log_Info = "insert into PushedLogInfo(fromTime,toTime,offset,limit,noOfRecords,status) values(?,?,?,?,?,?)";
	
	public static String prepareQueryToDeleteIn_Pushed_Log_Info(LocalDateTime fromTime)
	{
		String queryString = "";
		queryString = "DELETE FROM PushedLogInfo WHERE>"+fromTime;
		return queryString;
	}
	
	public static String prepareQueryFor_Device_Logs(LocalDateTime fromTime,LocalDateTime toTime,int offset,int limit)
	{
		Timestamp fromTimestamp = DateUtils.getTimestamp(ZonedDateTime.of(fromTime, ZoneId.systemDefault()));
		Timestamp toTimestamp = DateUtils.getTimestamp(ZonedDateTime.of(toTime, ZoneId.systemDefault()));
		LocalDateTime tempTime = DateUtils.getTempTimeForQuery(fromTime);
		String queryString = "";
		queryString = "SELECT * FROM(";
		do
		{
			int month = tempTime.getMonthValue();
			int year = tempTime.getYear();
			queryString += "SELECT * FROM DeviceLogs_"+month+"_"+year+" UNION ";
			tempTime = tempTime.plusMonths(1);
		}while(toTime.isAfter(tempTime));
		
		queryString = queryString.substring(0,queryString.length()-6);
		queryString += ")T WHERE DownloadDate>='"+fromTimestamp+"' AND DownloadDate<'"+toTimestamp+"'";
		queryString +=  " ORDER BY DownloadDate LIMIT "+offset+","+limit;
	
		return queryString;
	}
}
