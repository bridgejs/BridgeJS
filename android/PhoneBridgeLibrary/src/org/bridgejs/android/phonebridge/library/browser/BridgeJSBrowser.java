package org.bridgejs.android.phonebridge.library.browser;


import java.lang.reflect.InvocationTargetException;

import org.bridgejs.android.phonebridge.library.DroidBridge;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginManager;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ActivityEventsModifier;
import org.bridgejs.android.phonebridge.library.ui.HandlerWithLog;
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

	public void destroyWebView(){
		if (webView != null)
			webView.destroy() ;
	}
	
	
	private BridgeJSWebView webView;
	private ProgressBar progressBar;
	private ProgressBarUpdater progressBarUpdater;

	private boolean accelerateCanvas;

	private PluginManager pluginManager;

	public void init(DroidBridge activity, HandlerWithLog handler, boolean accelerateCanvas){

		FrameLayout.LayoutParams layout = 
				new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.FILL_PARENT, 
						FrameLayout.LayoutParams.FILL_PARENT);
		this.setLayoutParams(layout);

		this.accelerateCanvas = accelerateCanvas;
		this.webView = new BridgeJSWebView(activity.getApplicationContext());
		createAndAddProgressBar(activity.getApplicationContext(), handler);
		initPluginManager(activity, handler);
		createAndAddWebView(activity);
		this.addView(webView);
		this.addView(progressBar);
		
	}

	private void initPluginManager(DroidBridge activity, HandlerWithLog handler){
		this.pluginManager = new PluginManager(webView, activity, handler, progressBarUpdater, accelerateCanvas);
		pluginManager.initPlugins();
	}

	private void createAndAddProgressBar(Context context, HandlerWithLog handler){
		this.progressBar = StylizedProgressBarFactory.build(context);
		this.progressBarUpdater = new ProgressBarUpdater(progressBar, handler);
	}

	private void createAndAddWebView(Activity activity){
		webView.init(activity);
		webView.setWebChromeClient(new BridgeJSWebChromeClient(activity, progressBarUpdater));
		webView.setWebViewClient(new BridgeJSWebViewClient(pluginManager, this));

		FrameLayout.LayoutParams webViewLayout = 
				new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.FILL_PARENT, 
						FrameLayout.LayoutParams.FILL_PARENT);
		webView.setLayoutParams(webViewLayout);
	}

	public void loadUrlWithPlugins(final String url){
		gotoBlankWebPage();
		
		WebHistoryStack webHistoryStack = webView.getWebHistoryStack();
		pluginManager.loadUrlWithPlugins(webView, url, webHistoryStack);
	}

	public void gotoBlankWebPage(){
		webView.loadUrl("");
	}

	public void loadUrlWithoutPlugins(String url){
		gotoBlankWebPage();
		webView.loadUrl(url);
		
		WebHistoryStack webHistoryStack = webView.getWebHistoryStack();
		webHistoryStack.push(new WebContent("", url));
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