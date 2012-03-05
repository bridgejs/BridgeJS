package org.bridgejs.android.phonebridge.library.pluginmanager;


import android.graphics.Canvas;

public interface BridgeJSPlugin {
	public void init(PluginRequests requests);
	public void onDraw(Canvas canvas, int left, int top, float scale);
	public void onPageFinishedLoading();
	public void onPageStartedLoading();
	public String getPluginJS();
}
