package org.bridgejs.android.phonebridge.library.pluginmanager;

import java.util.ArrayList;

import org.bridgejs.android.phonebridge.library.browser.ProgressBarUpdater;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ActivityEventsModifier;


import android.app.Activity;
import android.graphics.Canvas;
import android.os.Handler;
import android.webkit.WebView;

public class PluginManager {

	private ArrayList<BridgeJSPlugin> plugins;

	private PluginRequests requests;

	private PluginLoader pluginLoader;
	
	private ProgressBarUpdater progressBarUpdater;

	public PluginManager(WebView webView, Activity activity, Handler handler, ProgressBarUpdater progressBarUpdater, boolean accelerateCanvas){
		plugins = new ArrayList<BridgeJSPlugin>();
		requests = new PluginRequests(webView, progressBarUpdater, activity, handler, this);
		pluginLoader = new PluginLoader(this, webView, requests, handler);
		
		PluginInitializer.init(plugins, this, webView, accelerateCanvas);
		
	}

	public PluginRequests getRequestMaker() {
		return requests;
	}

	public ActivityEventsModifier getActivityEventsModifier() {
		return requests.getActivityEventsModifier();
	}

	public void initPlugins(){
		for (BridgeJSPlugin plugin: plugins){
			plugin.init(requests);
		}
	}

	public void onPageFinishedLoading(WebView webView){
		for (BridgeJSPlugin plugin: plugins){
			plugin.onPageFinishedLoading();
		}
	}

	public void onPageStartedLoading(WebView webView){
		for (BridgeJSPlugin plugin: plugins){
			plugin.onPageStartedLoading();
		}
	}

	public String getPluginsJS(){
		String str = "";
		for (BridgeJSPlugin plugin: plugins){
			str += plugin.getPluginJS();
		}
		return str;
	}

	public void loadUrlWithPlugins(WebView webView, String url){
		pluginLoader.loadUrl(url);
	}

	public void onDraw(Canvas canvas, int left, int top, float scale){
		for (BridgeJSPlugin plugin: plugins){
			plugin.onDraw(canvas, left, top, scale);
		}
	}

}
