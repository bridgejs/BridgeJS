package com.eas.android.libraries.rapidjs.browser;

import com.eas.android.libraries.rapidjs.pluginmanager.PluginManager;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RapidJSWebViewClient extends WebViewClient{

	private RapidJSBrowser browser;

	private PluginManager pluginManager;

	public RapidJSWebViewClient (PluginManager pluginManager, RapidJSBrowser browser){
		this.pluginManager = pluginManager;
		this.browser = browser;
	}

	@Override  
	public void onPageFinished(WebView view, String url){
		pluginManager.onPageFinishedLoading(view);
	}
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon){
		pluginManager.onPageStartedLoading(view);
	}
	@Override
	public boolean shouldOverrideUrlLoading(WebView  view, String  url)
	{
		browser.loadUrlWithPlugins(url);
		return true;
	}
}
