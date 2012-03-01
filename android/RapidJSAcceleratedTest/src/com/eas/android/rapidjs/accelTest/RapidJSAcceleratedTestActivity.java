package com.eas.android.rapidjs.accelTest;

import com.eas.android.libraries.rapidjs.RapidJSWebView;
import com.eas.android.libraries.rapidjs.pluginmanager.ActivityEventsModifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RapidJSAcceleratedTestActivity extends Activity {
    /** Called when the actvity is first created. */

	private RapidJSWebView rapidJSWebView;
	
	private String currentUrl;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		String url = "http://html5.litten.com/layers/canvaslayers.html";
//		String url = "file:///android_asset/gamePhoneGap.html";
//		String url = "file:///android_asset/gameNative.html";
//		String url = "file:///android_asset/test2.html";
		

		this.rapidJSWebView = (RapidJSWebView) this.findViewById(R.id.rapidJSWebView);
		rapidJSWebView.init(this, new Handler(), true);
		
		loadFromIntent(getIntent(), url);
		loadCurrentUrlWithoutPlugins();
		
		setupButton();
		
	}
	
	public void setupButton(){
		final Button button = (Button) this.findViewById(R.id.switchButton);
		button.setText("Switch to Accelerated WebView");
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				if (rapidJSWebView.accelerate){
					loadCurrentUrlWithoutPlugins();
					button.setText("Switch to Accelerated WebView");
				}
				else {
					loadCurrentUrlWithPlugins();
					button.setText("Switch to Normal WebView");
				}
			}
		});
	}
	
	public void loadFromIntent(Intent i, String defaultUrl){
		String rapidURI = i.getDataString();
		if (rapidURI != null)
			defaultUrl = parseRapidURL(i.getDataString());

		currentUrl = defaultUrl;
	}
	
	public String parseRapidURL(String rapidURI) {
		String noRapid = rapidURI.substring(8);
		return noRapid.substring(0, 4) + ":" + noRapid.substring(4);
	}
	
	public void loadCurrentUrlWithPlugins(){
		rapidJSWebView.accelerate = true;
		rapidJSWebView.loadUrlWithPlugins(currentUrl);
	}
	
	public void loadCurrentUrlWithoutPlugins(){
		rapidJSWebView.accelerate = false;
		rapidJSWebView.loadUrl(currentUrl);
	}

	/* Note: we do have to get the activityEventsModifier in *EVERY* event listener we override 
	 * 			since we don't know if the pointer to the runnable will have changed at some point */

	@Override
	public void onResume() {
		super.onResume();
		ActivityEventsModifier activityEventsModifier = rapidJSWebView.getActivityEventsModifier();
		activityEventsModifier.getOnResumeModifier().run();
	}

	@Override
	public void onStop() {
		ActivityEventsModifier activityEventsModifier = rapidJSWebView.getActivityEventsModifier();
		activityEventsModifier.getOnStopModifier().run();
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if (keyCode == KeyEvent.KEYCODE_BACK){
			System.out.println("DONE...");
//			accWebView.destroy(); //TODO: Throws an error
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}