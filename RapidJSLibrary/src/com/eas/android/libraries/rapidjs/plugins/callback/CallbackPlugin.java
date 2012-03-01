package com.eas.android.libraries.rapidjs.plugins.callback;

import android.graphics.Canvas;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.JSTools;

public class CallbackPlugin extends AcceleratedWebViewPlugin{

	private String loadingJS;
	
	@Override
	public void init(JSTools pluginApplyer, AcceleratedWebViewRequests requests) {
		
		loadingJS = requests.getRapidJSAsset("callbackManager.js");

		CallbackJS callback = new CallbackJS(requests);
		pluginApplyer.addJavascriptInterface(callback, "__androidCallback");
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
