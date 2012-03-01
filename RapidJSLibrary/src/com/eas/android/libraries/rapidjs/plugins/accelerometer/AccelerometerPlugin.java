package com.eas.android.libraries.rapidjs.plugins.accelerometer;

import android.graphics.Canvas;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.JSTools;

public class AccelerometerPlugin extends AcceleratedWebViewPlugin{

	private String loadingJS;

	@Override
	public void init(JSTools pluginApplyer, AcceleratedWebViewRequests requests) {

		loadingJS = requests.getRapidJSAsset("accelerometerLoader.js");

		AccelerometerJS accelerometer = new AccelerometerJS(requests);
		pluginApplyer.addJavascriptInterface(accelerometer, "__androidAccelerometer");
	}

	@Override
	public String getPluginJS() {
		return loadingJS;
	}

	@Override
	public void onPageFinishedLoading() {
	}

	@Override
	public void onDraw(Canvas canvas, int left, int top, float scale) {

	}

	@Override
	public void onPageStartedLoading() {

	}

}
