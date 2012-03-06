package org.bridgejs.android.phonebridge.library.plugins.callback;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;


public class CallbackPlugin implements BridgeJSPlugin{

	private String loadingJS;
	
	public void init(PluginRequests requests) {
		
		loadingJS = requests.getBridgeJSAsset("callbackManager.js");

		CallbackJS callback = new CallbackJS(requests);
		requests.addJavascriptInterface(callback, "__androidCallback");
	}

	public String getPluginJS() {
		return loadingJS;
	}
	
	public void onPageFinishedLoading() {
	}

	public void onDraw(Canvas canvas, int left, int top, float scale) {

	}

	public void onPageStartedLoading() {
		
	}

}
