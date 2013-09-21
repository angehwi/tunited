package com.ideabove.tunited.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;

import com.facebook.*;
import com.facebook.model.*;

import com.ideabove.tunited.R;


public class FacebookLoginActivity extends FragmentActivity {
	public static final int REQUEST_FACEBOOKLOGIN_ACTIVITY = 1;

//	private UserSettingsFragment userSettingsFragment;
	//	private FacebookLoginManager fbLoginManager = FacebookLoginManager.getInstance();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login_fragment_activity);
		// start Facebook Login
		Session.openActiveSession(this, true, new Session.StatusCallback() {

			// callback when session changes state
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				if (session.isOpened()) {
					// make request to the /me API
					Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

						// callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user, Response response) {
							if (user != null) {
//								TextView welcome = (TextView) findViewById(R.id.welcome);
//								welcome.setText("Hello " + user.getName() + "!");
							}
						}
					});
				}
			}
		});

		//		FragmentManager fragmentManager = getSupportFragmentManager();
		//		userSettingsFragment = (UserSettingsFragment) fragmentManager.findFragmentById(R.id.login_fragment);
		//		userSettingsFragment.setSessionStatusCallback(new Session.StatusCallback() {
		//			@Override
		//			public void call(Session session, SessionState state, Exception exception) {
		//				if(state == SessionState.CLOSED){
		//					Log.e("V", "CLOSED");
		////					fbLoginManager.onClickLogout();
		//				}
		//				else if(state == SessionState.OPENED){
		//					Log.e("V", "OPENED");
		//				}
		//				else if(state == SessionState.CLOSED_LOGIN_FAILED){
		//					Log.e("V", "CLOSED_LOGIN_FAILED");
		//				}
		//			}
		//		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		userSettingsFragment.onActivityResult(requestCode, resultCode, data);
//		super.onActivityResult(requestCode, resultCode, data);
//	}
}