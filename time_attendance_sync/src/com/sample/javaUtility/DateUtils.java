package com.sample.javaUtility;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils 
{
	public static Date manageDate;
	public static Timestamp timeStamp;
	public static Date dateTimes;
	public static LocalDateTime dateInLocalDateTime;
	public static String convertedDate=null;
	public static String dateTimeInMillis=null;
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static String getDateTimeStringFromTimestamp(Timestamp timeStamp)
	{
		manageDate = new Date(timeStamp.getTime());
		convertedDate = dateFormat.format(manageDate);
		return convertedDate;
	}
	
	public static Timestamp getTimestamp(ZonedDateTime zonedDateTime)
	{
		timeStamp = new Timestamp(zonedDateTime.toInstant().toEpochMilli());
		return timeStamp;
	}
	
	public static String getDateTimeStringIntoMillis()
	{
		long millis=System.currentTimeMillis();
		Date dateTimes=new Date(millis);
		dateTimeInMillis= dateFormat.format(dateTimes);
		return dateTimeInMillis;	
	}
	
	public static LocalDateTime getDateTimeIntoLocalDateTime(String userInputDate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dateInLocalDateTime = LocalDateTime.parse(userInputDate,formatter);
		return dateInLocalDateTime;
	}
	
	public static LocalDateTime getTempTimeForQuery(LocalDateTime fromTime)
	{
		LocalDateTime tempTime = fromTime.minus(1, ChronoUnit.MONTHS);
		return tempTime;
	}
}
