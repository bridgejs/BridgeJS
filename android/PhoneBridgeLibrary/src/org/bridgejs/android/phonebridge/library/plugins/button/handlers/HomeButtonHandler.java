package org.bridgejs.android.phonebridge.library.plugins.button.handlers;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;

import android.view.KeyEvent;

public class HomeButtonHandler extends ButtonHandler {

	private PluginRequests requests;
	
	public HomeButtonHandler(PluginRequests requests) {
		super(requests, KeyEvent.KEYCODE_HOME);
		
		this.requests = requests;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		requests.addToOnHomeButtonModifier(new ButtonRunnable() {

			public boolean run(KeyEvent event) {
				return HomeButtonHandler.super.callback(event);
			}
			
		});
	}

}
