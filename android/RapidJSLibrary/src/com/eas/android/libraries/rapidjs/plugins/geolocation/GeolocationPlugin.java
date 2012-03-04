package com.eas.android.libraries.rapidjs.plugins.geolocation;

import android.graphics.Canvas;

import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSWebViewPlugin;
import com.eas.android.libraries.rapidjs.plugins.accelerometer.AccelerometerJS;

public class GeolocationPlugin implements RapidJSWebViewPlugin {

	private String loadingJS;
	
	public void init(RapidJSRequests requests) {
		loadingJS = requests.getRapidJSAsset("geolocationLoader.js");

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
