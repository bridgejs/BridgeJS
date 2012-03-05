package org.bridgejs.android.phonebridge.library.plugins.acceleratedCanvas2D;

import org.bridgejs.android.phonebridge.library.pluginmanager.BridgeJSPlugin;
import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.graphics.Canvas;
import android.os.Handler;


public class AcceleratedCanvas2DPlugin implements BridgeJSPlugin{

	private NativeCanvases nativeCanvases;

	private PluginRequests requests;

	private String loadingJS;

	public void init(PluginRequests requests){

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
