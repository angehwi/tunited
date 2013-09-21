package com.ideabove.tunited;

import com.ideabove.tunited.facebook.FacebookLoginActivity;
import com.ideabove.tunited.network.TunitedNetworkManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import com.facebook.*;
import com.facebook.model.*;

public class IntroActivity extends Activity implements OnClickListener{
	private Button btnLogin;
	
	private TunitedNetworkManager mNetworkManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);

		btnLogin = (Button)findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(this);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();

			StrictMode.setThreadPolicy(policy);
		}
		mNetworkManager = TunitedNetworkManager.getInstance();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.btn_login:
			login();			
			break;
		}
	}

	public void login(){
		boolean isLoginOn = mNetworkManager.requestLogin();
		if(isLoginOn){
			SystemClock.sleep(1000);
			Intent mainIntent = new Intent(IntroActivity.this, MainActivity.class);
			startActivity(mainIntent);
		}
	}
}


//public class MainActivity extends Activity implements OnClickListener {
//	private Button btnLogin;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		
//		btnLogin = (Button)findViewById(R.id.btn_login);
//		btnLogin.setOnClickListener(this);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public void onClick(View viewId) {
//		// TODO Auto-generated method stub
//		switch(viewId.getId()){
//		case R.id.btn_login:
//			Intent intent = new Intent(this, FacebookLoginActivity.class);
//			startActivityForResult(intent, FacebookLoginActivity.REQUEST_FACEBOOKLOGIN_ACTIVITY);
//			break;
//		}
//	}
//}
