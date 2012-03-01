package com.eas.android.libraries.rapidjs.pluginmanager;


import android.graphics.Canvas;

public abstract class AcceleratedWebViewPlugin {
	public abstract void init(JSTools pluginApplyer, AcceleratedWebViewRequests requests);
	public abstract void onDraw(Canvas canvas, int left, int top, float scale);
	public abstract void onPageFinishedLoading();
	public abstract void onPageStartedLoading();
	public abstract String getPluginJS();
}
