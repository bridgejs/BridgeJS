package org.bridgejs.android.phonebridge.library.plugins.accelerometer;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;


public class AccelerometerPlugin implements BridgeJSPlugin{

	private String loadingJS;

	public void init(PluginRequests requests) {

		loadingJS = requests.getRapidJSAsset("accelerometerLoader.js");

		AccelerometerJS accelerometer = new AccelerometerJS(requests);
		requests.addJavascriptInterface(accelerometer, "__androidAccelerometer");
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
