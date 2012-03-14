package org.bridgejs.android.phonebridge.library.plugins.orientation;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import android.graphics.Canvas;


public class OrientationPlugin implements BridgeJSPlugin{
	
	private String loadingJS;
	
	public void init(PluginRequests requests) {
		
		loadingJS = requests.getBridgeJSAsset("orientationLoader.js");
		
		OrientationJS orientation = new OrientationJS(requests);
		requests.addJavascriptInterface(orientation, "__androidOrientation");
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
