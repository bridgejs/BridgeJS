package com.eas.android.libraries.rapidjs.pluginmanager;

import java.util.ArrayList;

import com.eas.android.libraries.rapidjs.pluginmanager.activitymodifiers.ActivityEventsModifier;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Handler;
import android.webkit.WebView;

public class PluginManager {

	private ArrayList<AcceleratedWebViewPlugin> plugins;

	private JSTools jsTools;

	private AcceleratedWebViewRequests requests;

	private PluginLoader pluginLoader;

	public PluginManager(WebView webView, Activity activity, Handler handler, boolean accelerateCanvas){
		plugins = new ArrayList<AcceleratedWebViewPlugin>();
		
		jsTools = new JSTools(webView);
		requests = new AcceleratedWebViewRequests(webView, activity, handler, this);
		pluginLoader = new PluginLoader(this, webView, requests, jsTools, handler);
		
		PluginInitializer.init(plugins, this, webView, accelerateCanvas);
	}

	public ActivityEventsModifier getActivityEventsModifier() {
		return requests.getActivityEventsModifier();
	}

	public void initPlugins(){
		for (int i = 0; i < plugins.size(); i++){
			AcceleratedWebViewPlugin plugin = plugins.get(i);
			plugin.init(jsTools, requests);
		}
	}

	public void onPageFinishedLoading(WebView webView){
		for (int i = 0; i < plugins.size(); i++){
			AcceleratedWebViewPlugin plugin = plugins.get(i);
			plugin.onPageFinishedLoading();
		}
	}

	public void onPageStartedLoading(WebView webView){
		for (int i = 0; i < plugins.size(); i++){
			AcceleratedWebViewPlugin plugin = plugins.get(i);
			plugin.onPageStartedLoading();
		}
	}

	public String getPluginsJS(){
		String str = "";
		for (int i = 0; i < plugins.size(); i++){
			AcceleratedWebViewPlugin plugin = plugins.get(i);
			str += plugin.getPluginJS();
		}
		return str;
	}

	public void loadUrlWithPlugins(WebView webView, String url){
		pluginLoader.loadUrl(url);
	}

	public void onDraw(Canvas canvas, int left, int top, float scale){
		for (int i = 0; i < plugins.size(); i++){
			AcceleratedWebViewPlugin plugin = plugins.get(i);
			plugin.onDraw(canvas, left, top, scale);
		}
	}
}
