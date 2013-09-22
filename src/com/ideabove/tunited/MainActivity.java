package com.ideabove.tunited;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class MainActivity extends Activity{
	
	private SlidingMenu slidingMenu;
	private MainFragment mainFragment;
	private MenuFragment menuFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState == null) {
			// set the Above View
			mainFragment = new MainFragment();
			setContentView(R.layout.fragment_main);
			getFragmentManager()
			.beginTransaction()
			.add(R.id.fragment_main, mainFragment)
			.commit();
			
			menuFragment = new MenuFragment();
			getFragmentManager()
	        .beginTransaction()
	        .add(R.id.fragment_menu, menuFragment)
	        .commit();
        }
		
        // configure the SlidingMenu
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.fragment_menu);
        
        ActionBar actionbar = getActionBar();
        actionbar.setHomeButtonEnabled(true);

	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		Log.d("menuItem's ID", item.getItemId() + ".");
		switch (item.getItemId()) {
	        case android.R.id.home:
	            if(slidingMenu.isMenuShowing()){
					slidingMenu.showContent();
				} else{
					slidingMenu.showMenu();
				}
	        default:
        }
		return super.onMenuItemSelected(featureId, item);
	}
}
