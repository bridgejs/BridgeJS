package com.eas.android.libraries.rapidjs.plugins.device;

import android.graphics.Canvas;
import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSWebViewPlugin;

public class DevicePlugin implements RapidJSWebViewPlugin {
	
	private String loadingJS;

	public void init(RapidJSRequests requests) {

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
