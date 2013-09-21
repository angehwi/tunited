package com.ideabove.tunited.facebook;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.acl.Permission;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.Duration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest.permission_group;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Settings;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.internal.SessionTracker;
import com.facebook.model.GraphObject;
import com.facebook.widget.UserSettingsFragment;
import com.ideabove.tunited.IntroActivity;

public class FacebookManager {
	private String TAG = "FacebookManager";
	private String appId = "519037651516005";
	
	private static FacebookManager instance = new FacebookManager();	
	
	private FacebookLoginManager fbLoginManager = FacebookLoginManager.getInstance(); 

//	private ArrayList<FacebookMediaItem> facebookMediaList = new ArrayList<FacebookMediaItem>();

	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	
	public static FacebookManager getInstance(){	return instance;	}
	
	/** 
	 * @param activity FragmentActivity
	 */
	public boolean checkFacebookSession(IntroActivity context, Bundle savedInstanceState){
		Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);

        Session session = Session.getActiveSession();

		if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(context, null, statusCallback, savedInstanceState);
            }
            
            if (session == null) {
                session = new Session(context);
            }
            Session.setActiveSession(session);
            
            if (!session.isOpened() && session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(context).setCallback(statusCallback));
              
                return true;
            }
            else{
            	return false;
            }
        }
        else{
        	if(!session.isOpened()){
        		return false;
        	}
        	return true;
        }
	}

	public boolean isSessionOn(){
		Session session = Session.getActiveSession();
		if(session != null)
			return session.isOpened();
		else
			return false;
	}

	public Session.StatusCallback getStatusCallback() {
		return statusCallback;
	}

	public void setStatusCallback(Session.StatusCallback statusCallback) {
		this.statusCallback = statusCallback;
	}

	List<String> savedPermissions = null;
	
//	public ArrayList<FacebookMediaItem> executeMeRequest(Context context) {
//		facebookMediaList.clear();
//		
//		Session session = Session.getActiveSession();
//		List<String> PERMISSIONS = Arrays.asList("basic_info","user_photos", "user_status");
//
//		if(savedPermissions != null && !isSubsetOf(PERMISSIONS, savedPermissions)){
//			Log.e(TAG, "HERE");
//			Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest((Activity)context, PERMISSIONS);
//			session.requestNewReadPermissions(newPermissionsRequest);
//		}
//			
//		savedPermissions = PERMISSIONS;
//
//		Bundle bundle = new Bundle();
//		bundle.putString("fields", "source, picture");
//		bundle.putString("limit", "1000");
//
//		final Request request = new Request(session, "me/photos", bundle, HttpMethod.GET, new Request.Callback() {
//			@Override
//			public void onCompleted(Response response) {
//				GraphObject graphObject = response.getGraphObject();
//				try {
//					if(graphObject != null) {
//						JSONObject dataObject = graphObject.getInnerJSONObject();
//						JSONArray jsonMainArr = dataObject.getJSONArray("data");
//
//						for (int i = 0; i < jsonMainArr.length(); i++) {
//							JSONObject childJsonObject = jsonMainArr.getJSONObject(i);
//							String picture = childJsonObject.getString("picture");
//							String source  = childJsonObject.getString("source");
//							String created_time = childJsonObject.getString("created_time");
//
//							long timeMillis = 0;
//							Date date = null;
//							try {
//								date = new Date();
//								timeMillis = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSSS")
//								.parse(created_time)
//								.getTime();
//
//								date.setTime(timeMillis);
//
//								Calendar cal = Calendar.getInstance();
//								cal.setTime(date);
//
//								//								date.setYear(cal.get(Calendar.YEAR));
////								Log.e(TAG, "@@date : " +date + " , " + date.getTime());
//							} catch (ParseException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//							FacebookMediaItem fbMediaItem = new FacebookMediaItem("Camera", picture, source, date, false);
//							facebookMediaList.add(fbMediaItem);	
//						}
//					}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		//		Request.executeBatchAsync(request);
//		Request.executeBatchAndWait(request);
//
//		return facebookMediaList;
//	}

	private class SessionStatusCallback implements Session.StatusCallback {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
		}
	}
	
	private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
        for (String string : subset) {
            if (!superset.contains(string)) {
                return false;
            }
        }
        return true;
    }
}
