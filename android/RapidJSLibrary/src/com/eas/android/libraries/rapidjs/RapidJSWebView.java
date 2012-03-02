package com.eas.android.libraries.rapidjs;


import com.eas.android.libraries.rapidjs.pluginmanager.PluginManager;
import com.eas.android.libraries.rapidjs.pluginmanager.activitymodifiers.ActivityEventsModifier;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RapidJSWebView extends WebView {

	public RapidJSWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public RapidJSWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public RapidJSWebView(Context context) {
		super(context);
	}

	public boolean accelerate = true;

	private PluginManager pluginManager;

	public void init(final Activity activity, Handler handler, boolean accelerateCanvas){

		this.pluginManager = new PluginManager(this, activity, handler, accelerateCanvas);

		pluginManager.initPlugins();

		WebSettings settings = getSettings();
		settings.setJavaScriptEnabled(true);

		settings.setBuiltInZoomControls(true);

		this.setWebChromeClient(new WebChromeClient());

		this.setWebViewClient(new  WebViewClient() {  
			@Override  
			public void onPageFinished(WebView view, String url){
				if (accelerate){
					pluginManager.onPageFinishedLoading(view);
				}
			} 
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon){
				if (accelerate)
					pluginManager.onPageStartedLoading(view);
			}
			@Override
			public boolean shouldOverrideUrlLoading(WebView  view, String  url)
			{
				loadUrlWithPlugins(url);
				return true;
			}
		}); 

	}

	public void loadUrlWithPlugins(final String url){
		gotoBlankWebPage();
		pluginManager.loadUrlWithPlugins(this, url);
	}

	public void gotoBlankWebPage(){
		this.loadUrl("");
	}

	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		if (accelerate)
			pluginManager.onDraw(canvas, 0, 0, this.getScale());
	}

	public ActivityEventsModifier getActivityEventsModifier() {
		return pluginManager.getActivityEventsModifier();
	}

}
