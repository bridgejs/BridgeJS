package com.eas.android.libraries.rapidjs.browser;


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
import android.webkit.WebSettings.RenderPriority;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

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

	public void init(final Activity activity, Handler handler){

		WebSettings settings = getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);

		settings.setSupportZoom(true);  
		settings.setBuiltInZoomControls(true);

		this.setVerticalScrollBarEnabled(true);
		this.setHorizontalScrollBarEnabled(true);

		settings.setJavaScriptEnabled(true);
		settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
		settings.setUseWideViewPort(true);
		
		settings.setAppCacheEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setGeolocationEnabled(true);
		settings.setRenderPriority(RenderPriority.HIGH);
		settings.setDomStorageEnabled(true);
		
	}

}
