package com.eas.android.libraries.rapidjs.plugins.callback;

import android.graphics.Canvas;

import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSRequests;

public class CallbackPlugin implements RapidJSWebViewPlugin{

	private String loadingJS;
	
	public void init(RapidJSRequests requests) {
		
		loadingJS = requests.getRapidJSAsset("callbackManager.js");

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
