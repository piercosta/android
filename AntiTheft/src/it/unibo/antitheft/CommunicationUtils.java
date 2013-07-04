package it.unibo.antitheft;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.location.Location;
import android.util.Log;

public class CommunicationUtils {
	
	

	public static void sendCoordinates(Location location)
	{
		
		long timestamp = System.currentTimeMillis()/1000;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
	    
		
		nameValuePairs.add(new BasicNameValuePair("Device", "113"));
		nameValuePairs.add(new BasicNameValuePair("Time", ""+timestamp));
		nameValuePairs.add(new BasicNameValuePair("Lat", ""+location.getLatitude()));
	    nameValuePairs.add(new BasicNameValuePair("Lon", ""+location.getLongitude()));
	    

	    //http post
	    try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new      
	        HttpPost("http://www.altervista.mobilewd2013.com/test.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        Log.i("postData", response.getStatusLine().toString());
	    }

	    catch(Exception e)
	    {
	        Log.e("log_tag", "Error in http connection "+e.toString());
	    }           
	}

}
