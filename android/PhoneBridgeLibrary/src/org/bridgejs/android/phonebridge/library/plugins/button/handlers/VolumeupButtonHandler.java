package org.bridgejs.android.phonebridge.library.plugins.button.handlers;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;

import android.view.KeyEvent;

public class VolumeupButtonHandler extends ButtonHandler {

	private PluginRequests requests;
	
	public VolumeupButtonHandler(PluginRequests requests) {
		super(requests, KeyEvent.KEYCODE_VOLUME_UP);
		
		this.requests = requests;
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		requests.addToOnVolumeupButtonModifier(new ButtonRunnable() {

			public boolean run(KeyEvent event) {
				return VolumeupButtonHandler.super.callback(event);
			}
			
		});
	}

}
