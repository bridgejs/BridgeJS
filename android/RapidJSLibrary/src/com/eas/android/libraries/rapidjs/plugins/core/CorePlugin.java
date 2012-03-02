package com.eas.android.libraries.rapidjs.plugins.core;

import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSWebViewPlugin;

import android.graphics.Canvas;

public class CorePlugin implements RapidJSWebViewPlugin {

	private String coreJS;
	
	public void init(RapidJSRequests requests) {
		
		coreJS = requests.getRapidJSAsset("core.js");
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
		return coreJS;
	}
	
}
