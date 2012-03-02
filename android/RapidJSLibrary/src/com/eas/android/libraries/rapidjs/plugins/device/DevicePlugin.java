package com.eas.android.libraries.rapidjs.plugins.device;

import android.graphics.Canvas;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.JSTools;

public class DevicePlugin extends AcceleratedWebViewPlugin{
	
	private String loadingJS;

	@Override
	public void init(JSTools pluginApplyer, AcceleratedWebViewRequests requests) {

		loadingJS = requests.getRapidJSAsset("deviceLoader.js");
		
		DeviceJS device = new DeviceJS(requests);
		pluginApplyer.addJavascriptInterface(device, "__androidDevice");
		
	}

	@Override
	public void onDraw(Canvas canvas, int left, int top, float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageFinishedLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageStartedLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPluginJS() {
		return loadingJS;
	}

}
