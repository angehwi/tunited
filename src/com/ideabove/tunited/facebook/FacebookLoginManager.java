package com.ideabove.tunited.facebook;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.facebook.Session;
import com.facebook.SessionState;

public class FacebookLoginManager {
	private static FacebookLoginManager instance = new FacebookLoginManager();
	
	public static FacebookLoginManager getInstance(){	return instance;	}
	
	public void onClickLogin(Context context, Session.StatusCallback statusCallback) {
		Session session = Session.getActiveSession();
		if (!session.isOpened() && !session.isClosed()) {
			session.openForRead(new Session.OpenRequest((Activity)context).setCallback(statusCallback));
		} else {
			Session.openActiveSession((Activity)context, true, statusCallback);
		}
	}
	
	public void onClickLogout() {
		Session session = Session.getActiveSession();
		if (!session.isClosed()) {
			session.closeAndClearTokenInformation();
		}
	}
	
	private class SessionStatusCallback implements Session.StatusCallback {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
		}
	}
}
