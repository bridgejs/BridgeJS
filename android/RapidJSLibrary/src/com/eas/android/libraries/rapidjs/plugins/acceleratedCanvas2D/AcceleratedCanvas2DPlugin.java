package com.eas.android.libraries.rapidjs.plugins.acceleratedCanvas2D;

import android.graphics.Canvas;
import android.os.Handler;

import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.RapidJSRequests;

public class AcceleratedCanvas2DPlugin implements RapidJSWebViewPlugin{

	private NativeCanvases nativeCanvases;

	private RapidJSRequests requests;

	private String loadingJS;

	public void init(RapidJSRequests requests){

		this.requests = requests;

		loadingJS = requests.getRapidJSAsset("acceleratedCanvas2dLoader.js");

		nativeCanvases = new NativeCanvases(requests);
		requests.addJavascriptInterface(nativeCanvases.canvasUnifier, "__androidCanvas");

	}
	
	public void startPeriodicScanningForCanvasUpdates(){
		final Handler handler = requests.getHandler();
		handler.postDelayed(new Runnable(){
			public void run() {
				lookForCanvasUpdates();
				handler.postDelayed(this, 500);
			}
		}, 500);
	}

	public void lookForCanvasUpdates(){
		requests.postJavascript("__bindCanvasesToAndroid();");
	}

	public void onDraw(Canvas canvas, int left, int top, float scale){
		if (nativeCanvases != null){
			nativeCanvases.draw(canvas, left, top, scale);
		}
	}
	
	public String getPluginJS() {
		return loadingJS;
	}

	public void onPageFinishedLoading(){
		startPeriodicScanningForCanvasUpdates();
	}

	public void onPageStartedLoading() {

	}
}
