package org.bridgejs.android.phonebridge.library.plugins.core;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;

public class CorePlugin implements BridgeJSPlugin {

	private String coreJS;
	
	public void init(PluginRequests requests) {
		
		coreJS = requests.getBridgeJSAsset("core.js");
	}

	public void onDraw(Canvas canvas, int left, int top, float scale) {
		// TODO Auto-generated method stub
		
	}

	public void onPageFinishedLoading() {
		// TODO Auto-generated method stub
		
	}

	public void onPageStartedLoading() {
		// TODO Auto-generated method stub
		
	}

	public String getPluginJS() {
		return coreJS;
	}
	
}
