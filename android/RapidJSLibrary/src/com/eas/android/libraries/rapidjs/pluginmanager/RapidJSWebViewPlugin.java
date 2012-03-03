package com.eas.android.libraries.rapidjs.pluginmanager;


import android.graphics.Canvas;

public interface RapidJSWebViewPlugin {
	public void init(RapidJSRequests requests);
	public void onDraw(Canvas canvas, int left, int top, float scale);
	public void onPageFinishedLoading();
	public void onPageStartedLoading();
	public String getPluginJS();
}
