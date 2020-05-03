package com.sample.javaUtility;
import java.time.LocalDateTime;
import org.json.JSONArray;

public interface AttendanceUtilsInterface
{
	public  LocalDateTime[] getSyncRange();
	
	public  JSONArray fetchPunchRecords(LocalDateTime fromTime,LocalDateTime toTime,int offset,int limit);
	
	public  void addSyncRecord(LocalDateTime fromDateTime, LocalDateTime toDateTime, int offset, int limit , int noOfRecords,boolean status);
}
