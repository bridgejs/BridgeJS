package org.bridgejs.android.phonebridge.library.plugins.geolocation;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;

public class GeolocationPlugin implements BridgeJSPlugin {

	private String loadingJS;
	
	public void init(PluginRequests requests) {
		loadingJS = requests.getBridgeJSAsset("geolocationLoader.js");

		GeolocationJS geolocation = new GeolocationJS(requests);
		requests.addJavascriptInterface(geolocation, "__androidGeolocation");
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
		return loadingJS;
	}

}
