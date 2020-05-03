package com.sample.javaUtility;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import java.util.concurrent.TimeUnit;

public class HTTPConnectionManager 
{
	public boolean postDataToServer(JSONArray punchData)
	{
		boolean status = false;
		int i=5;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(Utils.attendancePushUrl);
	    System.out.println(punchData);
	    System.out.println(punchData.toString());
	    request.addHeader("content-type", "application/json");
	    request.addHeader("authorization",Utils.authorization);
		try
		{
			 StringEntity params = new StringEntity(punchData.toString());
			 System.out.println("Params "+params);
			 request.setEntity(params);
		     httpClient.execute(request);
		     status = true;    
		} 
		catch (Exception ex) 
		{
			System.out.println(ex);		
		}
		
		finally
		{
			if(status == false)
			{
				while(i>0)
				{
					try 
					{
						TimeUnit.SECONDS.sleep(5);
						httpClient.execute(request);
						status = true; 
					} 
					catch (Exception ex) 
					{
						status = false;
						System.out.println(ex);
					}
					if(status == false)
					{
						i--;
					}
					else
					{ 
						i=0;
					}
				}
			}
			try
			{	
				httpClient.close();
			}
			catch (Exception ex) 
			{
				System.out.println(ex);
			}
		    return status;
		}	
	}
}
