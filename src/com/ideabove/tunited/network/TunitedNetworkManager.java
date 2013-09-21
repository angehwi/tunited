package com.ideabove.tunited.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class TunitedNetworkManager {
	private static TunitedNetworkManager instance = new TunitedNetworkManager();
	public static TunitedNetworkManager getInstance(){		return instance;	}

	private String SERVER_URI = "http://192.168.0.99:1125/api/";
	
//	private String SERVER_URI = "http://ec2-54-200-76-105.us-west-2.compute.amazonaws.com/api/";
	/*
	 * TEST CODE
	 */
	
	private String REQUEST_LOGIN_URL = SERVER_URI + "users";

	public boolean requestLogin(){
		try
		{
			HttpClient client = new DefaultHttpClient();  
			HttpPost post = new HttpPost(REQUEST_LOGIN_URL);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("user_id", "1"));

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			post.setEntity(entity);
			HttpResponse responsePOST = client.execute(post);  
			HttpEntity resEntity = responsePOST.getEntity();

			if (resEntity != null)
			{    
				Log.i("RESPONSE", EntityUtils.toString(resEntity));
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
