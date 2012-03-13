package org.bridgejs.android.phonebridge.library.plugins.compass;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import android.graphics.Canvas;


public class CompassPlugin implements BridgeJSPlugin{
	
	private String loadingJS;
	
	public void init(PluginRequests requests) {
		
		loadingJS = requests.getBridgeJSAsset("compassLoader.js");
		
		CompassJS compass = new CompassJS(requests);
		requests.addJavascriptInterface(compass, "__androidCompass");
	}

	public void onDraw(Canvas canvas, int left, int top, float scale) {
	}

	public void onPageFinishedLoading() {
	}

	public void onPageStartedLoading() {	
	}

	public String getPluginJS() {
		return loadingJS;
	}

}
