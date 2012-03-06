package org.bridgejs.android.phonebridge.library.browser;


import java.lang.reflect.InvocationTargetException;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginManager;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ActivityEventsModifier;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class BridgeJSBrowser extends FrameLayout{

	public BridgeJSBrowser(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public BridgeJSBrowser(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BridgeJSBrowser(Context context) {
		super(context);
	}


	private BridgeJSWebView webView;
	private ProgressBar progressBar;

	private boolean accelerateCanvas;

	private PluginManager pluginManager;

	public void init(Activity activity, Handler handler, boolean accelerateCanvas){
		this.accelerateCanvas = accelerateCanvas;
		this.webView = new BridgeJSWebView(activity.getApplicationContext());
		initPluginManager(activity, handler);
		createAndAddProgressBar(activity.getApplicationContext());
		createAndAddWebView(activity, handler);
	}

	private void initPluginManager(Activity activity, Handler handler){
		this.pluginManager = new PluginManager(webView, activity, handler, accelerateCanvas);
		pluginManager.initPlugins();
	}

	private void createAndAddProgressBar(Context context){
		this.progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);

		FrameLayout.LayoutParams progressBarLayout = 
				new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.FILL_PARENT, 
						FrameLayout.LayoutParams.WRAP_CONTENT);
		progressBar.setLayoutParams(progressBarLayout);
		this.addView(progressBar);
	}

	private void createAndAddWebView(Activity activity, Handler handler){
		webView.init(activity, handler);
		webView.setWebChromeClient(new BridgeJSWebChromeClient(progressBar));
		webView.setWebViewClient(new BridgeJSWebViewClient(pluginManager, this));
		this.addView(webView);
	}

	public void loadUrlWithPlugins(final String url){
		gotoBlankWebPage();
		pluginManager.loadUrlWithPlugins(webView, url);
	}

	public void gotoBlankWebPage(){
		webView.loadUrl("");
	}

	public void stopAndDestroyWebView() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
		Class.forName("android.webkit.WebView").getMethod("onPause", 
				(Class[]) null).invoke(webView, (Object[]) null);
	}

	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		if (accelerateCanvas)
			pluginManager.onDraw(canvas, 0, 0, webView.getScale());
	}

	public ActivityEventsModifier getActivityEventsModifier() {
		return pluginManager.getActivityEventsModifier();
	}
}