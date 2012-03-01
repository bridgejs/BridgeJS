package com.eas.android.libraries.rapidjs.plugins.acceleratedCanvas2D;

import android.graphics.Canvas;
import android.os.Handler;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.JSTools;

public class AcceleratedCanvas2DPlugin extends AcceleratedWebViewPlugin{

	private NativeCanvases nativeCanvases;

	private AcceleratedWebViewRequests requests;

	private String loadingJS;

	@Override
	public void init(JSTools pluginApplyer, AcceleratedWebViewRequests requests){

		this.requests = requests;

		loadingJS = requests.getRapidJSAsset("acceleratedCanvas2dLoader.js");

		nativeCanvases = new NativeCanvases(requests);
		pluginApplyer.addJavascriptInterface(nativeCanvases.canvasUnifier, "__androidCanvas");

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

	@Override
	public void onDraw(Canvas canvas, int left, int top, float scale){
		if (nativeCanvases != null){
			nativeCanvases.draw(canvas, left, top, scale);
		}
	}

	@Override
	public String getPluginJS() {
		return loadingJS;
	}

	@Override
	public void onPageFinishedLoading(){
		startPeriodicScanningForCanvasUpdates();
	}

	@Override
	public void onPageStartedLoading() {

	}
}
