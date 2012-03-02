package com.eas.android.libraries.rapidjs.plugins.accelerometer;

import android.graphics.Canvas;

import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSRequests;

public class AccelerometerPlugin implements RapidJSWebViewPlugin{

	private String loadingJS;

	public void init(RapidJSRequests requests) {

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
