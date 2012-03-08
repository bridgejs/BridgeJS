package org.bridgejs.android.phonebridge.library.plugins.core;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;

public class CorePlugin implements BridgeJSPlugin {

	private String coreJS;

	private PluginRequests requests;

	public void init(PluginRequests requests) {
		this.requests = requests;
		coreJS = requests.getBridgeJSAsset("core.js");
	}

	public void onDraw(Canvas canvas, int left, int top, float scale) {
		// TODO Auto-generated method stub

	}

	public void onPageFinishedLoading() {
		
	}

	public void onPageStartedLoading() {

	}

	public String getPluginJS() {
		return coreJS;
	}

}
