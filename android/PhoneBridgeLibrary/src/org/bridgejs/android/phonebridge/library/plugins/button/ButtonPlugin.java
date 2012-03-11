package org.bridgejs.android.phonebridge.library.plugins.button;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;

public class ButtonPlugin implements BridgeJSPlugin {

	private String loadingJS;
	
	public void init(PluginRequests requests) {
		// TODO Auto-generated method stub
		loadingJS = requests.getBridgeJSAsset("button.js");
		
		ButtonJS button = new ButtonJS(requests);
		requests.addJavascriptInterface(button, "__androidButton");
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
		// TODO Auto-generated method stub
		return loadingJS;
	}

}
