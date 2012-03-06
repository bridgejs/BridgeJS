package org.bridgejs.android.phonebridge.library.pluginmanager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ActivityEventsModifier;


import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Handler;
import android.webkit.WebView;

public class PluginRequests {

	private WebView webView;

	private Handler handler;

	private Activity activity;

	private ActivityEventsModifier activityEventsModifier;

	public void addJavascriptInterface(Object obj, String interfaceName){
		webView.addJavascriptInterface(obj, interfaceName);
	}

	public PluginRequests(WebView webView, Activity activity, Handler handler, PluginManager pluginManager){
		this.webView = webView;
		this.activity = activity;
		this.handler = handler;

		this.activityEventsModifier = new ActivityEventsModifier(activity);
	}

	public Handler getHandler(){
		return handler;
	}

	public Object getSystemService(String service) {
		return activity.getSystemService(service);
	}

	public void postInvalidate(){
		webView.postInvalidate();
	}

	public AssetManager getAssetManager(){
		return activity.getAssets();
	}

	public String getUrlData(String url){
		if (url.startsWith("file")){
			return getLocalAsset(url);
		}
		else {
			return downloadUrlData(url);
		}
	}

	public String downloadUrlData(String urlString){
		InputStream urlInputStream = null;
		String content = "";
		System.out.println(urlString);
		try {
			URL url = new URL(urlString);
			urlInputStream = url.openStream();
			DataInputStream urlDataInputStream = new DataInputStream(new BufferedInputStream(urlInputStream));
			String line = "";
			while ((line = urlDataInputStream.readLine()) != null) {
				if (!line.startsWith("//"))
					content += line + "\n";
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (urlInputStream != null){
			try {
				urlInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	public String getBridgeJSAsset(String asset){
		return getLocalAsset("bridgejs/" + asset);
	}

	public String getLocalAsset(String asset){
		asset = asset.replaceAll("file:///android_asset/", "");
		String content = "";
		try{
			InputStream is = getAssetManager().open(asset);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line;
			while (( line = br.readLine()) != null) {
				if (!line.startsWith("//"))
					content += line + "\n";
			}
			is.close(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}

	public void postJavascript(final String javascript){
		try {
			handler.post(new Runnable(){
				public void run() {
					webView.loadUrl("javascript:" + javascript);
				}
			});
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public void addToOnResume(Runnable onResumeTask) {
		activityEventsModifier.addToOnResumeModifier(onResumeTask);
	}

	public void addToOnStop(Runnable onStopTask) {
		activityEventsModifier.addToOnStopModifier(onStopTask);
	}

	public ActivityEventsModifier getActivityEventsModifier() {
		return this.activityEventsModifier;
	}

}
