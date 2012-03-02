package com.eas.android.libraries.rapidjs.pluginmanager;


import android.graphics.Canvas;

public interface RapidJSWebViewPlugin {
	public abstract void init(RapidJSRequests requests);
	public abstract void onDraw(Canvas canvas, int left, int top, float scale);
	public abstract void onPageFinishedLoading();
	public abstract void onPageStartedLoading();
	public abstract String getPluginJS();
}
