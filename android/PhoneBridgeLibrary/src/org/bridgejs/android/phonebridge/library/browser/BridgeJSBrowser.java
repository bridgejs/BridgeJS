package org.bridgejs.android.phonebridge.library.browser;


import java.lang.reflect.InvocationTargetException;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginManager;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ActivityEventsModifier;
import org.bridgejs.android.phonebridge.library.ui.StylizedProgressBarFactory;


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
	private ProgressBarUpdater progressBarUpdater;

	private boolean accelerateCanvas;

	private PluginManager pluginManager;

	public void init(Activity activity, Handler handler, boolean accelerateCanvas){

		FrameLayout.LayoutParams layout = 
				new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.FILL_PARENT, 
						FrameLayout.LayoutParams.FILL_PARENT);
		this.setLayoutParams(layout);

		this.accelerateCanvas = accelerateCanvas;
		this.webView = new BridgeJSWebView(activity.getApplicationContext());
		createAndAddProgressBar(activity.getApplicationContext());
		initPluginManager(activity, handler);
		createAndAddWebView(activity, handler);
		this.addView(webView);
		this.addView(progressBar);
	}

	private void initPluginManager(Activity activity, Handler handler){
		this.pluginManager = new PluginManager(webView, activity, handler, progressBarUpdater, accelerateCanvas);
		pluginManager.initPlugins();
	}

	private void createAndAddProgressBar(Context context){
		this.progressBar = StylizedProgressBarFactory.build(context);
		this.progressBarUpdater = new ProgressBarUpdater(progressBar, new Handler());
	}

	private void createAndAddWebView(Activity activity, Handler handler){
		webView.init(activity, handler);
		webView.setWebChromeClient(new BridgeJSWebChromeClient(progressBarUpdater));
		webView.setWebViewClient(new BridgeJSWebViewClient(pluginManager, this));

		FrameLayout.LayoutParams webViewLayout = 
				new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.FILL_PARENT, 
						FrameLayout.LayoutParams.FILL_PARENT);
		webView.setLayoutParams(webViewLayout);
	}

	public void loadUrlWithPlugins(final String url){
		gotoBlankWebPage();
		pluginManager.loadUrlWithPlugins(webView, url);
	}

	public void gotoBlankWebPage(){
		webView.loadUrl("");
	}

	public void stopAndDestroyWebView() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
		Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(webView, (Object[]) null);
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