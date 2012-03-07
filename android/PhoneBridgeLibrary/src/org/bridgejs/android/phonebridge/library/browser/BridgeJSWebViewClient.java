package org.bridgejs.android.phonebridge.library.browser;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginManager;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BridgeJSWebViewClient extends WebViewClient{

	private BridgeJSBrowser browser;

	private PluginManager pluginManager;
	
	private PluginRequests request;

	public BridgeJSWebViewClient (PluginManager pluginManager, BridgeJSBrowser browser){
		this.pluginManager = pluginManager;
		this.browser = browser;
		this.request = pluginManager.getRequestMaker();
	}

	@Override  
	public void onPageFinished(WebView view, String url){
		pluginManager.onPageFinishedLoading(view);
		request.setProgressBar(100);
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
