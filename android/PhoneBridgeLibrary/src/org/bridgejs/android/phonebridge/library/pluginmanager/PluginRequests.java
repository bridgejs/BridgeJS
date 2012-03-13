package org.bridgejs.android.phonebridge.library.pluginmanager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bridgejs.android.phonebridge.library.DroidBridge;
import org.bridgejs.android.phonebridge.library.browser.ProgressBarUpdater;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ActivityEventsModifier;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;


import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

public class PluginRequests {

	private WebView webView;

	private Handler handler;

	private DroidBridge droidBridge;

	private ActivityEventsModifier activityEventsModifier;
	
	private ProgressBarUpdater progressBarUpdater;

	public void addJavascriptInterface(Object obj, String interfaceName){
		webView.addJavascriptInterface(obj, interfaceName);
	}

	public PluginRequests(WebView webView, ProgressBarUpdater progressBarUpdater, DroidBridge droidBridge, Handler handler, PluginManager pluginManager){
		this.webView = webView;
		this.droidBridge = droidBridge;
		this.handler = handler;
		this.progressBarUpdater = progressBarUpdater;

		this.activityEventsModifier = new ActivityEventsModifier(droidBridge);
	}

	public Handler getHandler(){
		return handler;
	}

	public Object getSystemService(String service) {
		return droidBridge.getSystemService(service);
	}

	public void postInvalidate(){
		webView.postInvalidate();
	}

	public AssetManager getAssetManager(){
		return droidBridge.getAssets();
	}

	public String getUrlData(String url){
		if (url.startsWith("file")){
			return getLocalAsset(url);
		}
		else {
			return downloadUrlData(url);
		}
	}
	
	public void setProgressBar(int progress){
		progressBarUpdater.setProgress(progress);
	}
	
	public String downloadUrlData(String urlString){
		InputStream urlInputStream = null;
		String content = "";
		try {
			URL url = new URL(urlString);
			urlInputStream = url.openStream();
			content = getDataFromInputStream(urlInputStream);
			urlInputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public String getBridgeJSAsset(String asset){
		String content = "";;
		try {
			URL url = this.getClass().getClassLoader().getResource("org/bridgejs/android/phonebridge/library/bridgejs/" + asset);
			content = getDataFromInputStream(url.openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return content;
	}

	public String getLocalAsset(String asset){
		asset = asset.replaceAll("file:///android_asset/", "");
		String content = "";
		try{
			InputStream is = getAssetManager().open(asset);
			content = getDataFromInputStream(is);
			is.close(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}
	
	public String getDataFromInputStream(InputStream inputStream) throws IOException{
		String content = "";
		DataInputStream urlDataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
		String line = "";
		while ((line = urlDataInputStream.readLine()) != null) {
			if (!line.startsWith("//"))
				content += line + "\n";
		}
		return content;
	}
	
	
	public void postJavascript(final String javascript, final Object context, final String messageToLog) {
		final AtomicBoolean isPaused = droidBridge.getIsPaused();
		try {
			handler.post(new Runnable(){
				public void run() {
					if (webView == null || isPaused.get())
						return;
					Log.d("JSPost", "Loading js from: " + context.getClass() + ", msg: " + messageToLog);
					webView.loadUrl("javascript:" + javascript);
				}
			});
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void postJavascript(final String javascript, final Object context) {
		AtomicBoolean isPaused = droidBridge.getIsPaused();
		if (webView == null || isPaused.get())
			return;
		try {
			handler.post(new Runnable(){
				public void run() {
					Log.d("JSPost", "Loading js from: " + context.getClass());
					webView.loadUrl("javascript:" + javascript);
				}
			});
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean callSuperOnKeyDown(int keyCode, KeyEvent event) {
		return droidBridge.superOnKeyDown(keyCode, event);
	}

	public void addToOnResume(Runnable onResumeTask) {
		activityEventsModifier.addToOnResumeModifier(onResumeTask);
	}

	public void addToOnStop(Runnable onStopTask) {
		activityEventsModifier.addToOnStopModifier(onStopTask);
	}
	
	public void addToOnStart(Runnable onStartTask) {
		activityEventsModifier.addToOnStartModifier(onStartTask);
	}
	
	public void addToOnDestroy(Runnable onDestroyTask) {
		activityEventsModifier.addToOnStartModifier(onDestroyTask);
	}
	
	public void addToOnPause(Runnable onPauseTask) {
		activityEventsModifier.addToOnStartModifier(onPauseTask);
	}
	
	
	public void addToOnMenuButtonModifier(final ButtonRunnable newAction) {
		activityEventsModifier.addToOnMenuButtonModifier(newAction);
	}
	
	public void addToOnBackButtonModifier(final ButtonRunnable newAction) {
		activityEventsModifier.addToOnBackButtonModifier(newAction);
	}
	
	public void addToOnVolumedownButtonModifier(final ButtonRunnable newAction) {
		activityEventsModifier.addToOnVolumedownButtonModifier(newAction);
	}
	
	public void addToOnVolumeupButtonModifier(final ButtonRunnable newAction) {
		activityEventsModifier.addToOnVolumeupButtonModifier(newAction);
	}
	
	public void addToOnHomeButtonModifier(final ButtonRunnable newAction) {
		activityEventsModifier.addToOnHomeButtonModifier(newAction);
	}
	
	

	public ActivityEventsModifier getActivityEventsModifier() {
		return this.activityEventsModifier;
	}

}
