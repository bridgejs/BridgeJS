package org.bridgejs.android.phonebridge.library.plugins.button.handlers;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;

import android.view.KeyEvent;

public class BackButtonHandler extends ButtonHandler {

	private PluginRequests requests;
	
	public BackButtonHandler(PluginRequests requests) {
		super(requests, KeyEvent.KEYCODE_BACK);
		
		this.requests = requests;
		init();
	}
	
	@Override
	public boolean callback(KeyEvent event) {
		
		super.callCallbacks(event);
		boolean shouldDoSuper = super.getIsDoSuper();
		super.resetState();
		
		if (shouldDoSuper) {
			if (requests.canWebViewGoBack()) {
		        requests.webViewGoBack();
		        return true;
		    }
			return requests.callSuperOnKeyDown(KeyEvent.KEYCODE_BACK, event);
		}
		else
			return true;
	}

	private void init() {
		requests.addToOnBackButtonModifier(new ButtonRunnable() {

			public boolean run(KeyEvent event) {
				return callback(event);
			}
			
		});
	}

}
