package org.bridgejs.android.phonebridge.library.plugins.device;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;


public class DevicePlugin implements BridgeJSPlugin {
	
	private String loadingJS;

	public void init(PluginRequests requests) {

		loadingJS = requests.getRapidJSAsset("deviceLoader.js");
		
		DeviceJS device = new DeviceJS(requests);
		requests.addJavascriptInterface(device, "__androidDevice");
		
	}

	public String getPluginJS() {
		return loadingJS;
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

}
