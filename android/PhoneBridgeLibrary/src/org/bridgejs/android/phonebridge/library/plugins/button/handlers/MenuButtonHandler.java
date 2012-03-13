package org.bridgejs.android.phonebridge.library.plugins.button.handlers;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;

import android.view.KeyEvent;

public class MenuButtonHandler extends ButtonHandler {

	private PluginRequests requests;
	
	public MenuButtonHandler(PluginRequests requests) {
		super(requests, KeyEvent.KEYCODE_MENU);
		
		this.requests = requests;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		requests.addToOnMenuButtonModifier(new ButtonRunnable() {

			public boolean run(KeyEvent event) {
				return MenuButtonHandler.super.callback(event);
			}
			
		});
	}

}
