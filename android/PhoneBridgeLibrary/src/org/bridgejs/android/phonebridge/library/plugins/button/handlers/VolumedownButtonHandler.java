package org.bridgejs.android.phonebridge.library.plugins.button.handlers;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;

import android.view.KeyEvent;

public class VolumedownButtonHandler extends ButtonHandler {

	private PluginRequests requests;
	
	public VolumedownButtonHandler(PluginRequests requests) {
		super(requests, KeyEvent.KEYCODE_VOLUME_DOWN);
		
		this.requests = requests;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		requests.addToOnVolumedownButtonModifier(new ButtonRunnable() {

			public boolean run(KeyEvent event) {
				return VolumedownButtonHandler.super.callback(event);
			}
			
		});
	}

}
