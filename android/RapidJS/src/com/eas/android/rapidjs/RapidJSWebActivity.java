package com.eas.android.rapidjs;

import com.eas.android.libraries.rapidjs.RapidJSWebView;
import com.eas.android.libraries.rapidjs.pluginmanager.ActivityEventsModifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

public class RapidJSWebActivity extends Activity {
	/** Called when the activity is first created. */

	private RapidJSWebView rapidJSWebView;

	private String currentUrl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		String url = "http://html5.litten.com/layers/canvaslayers.html";
		//		String url = "http://impactjs.com/drop";
		//		String url = "http://rapidjs.com/gameNative.html";
		//		String url = "file:///android_asset/gamePhoneGap.html";
		//		String url = "file:///android_asset/gameNative.html";
		//		String url = "file:///android_asset/test2.html";

		this.rapidJSWebView = (RapidJSWebView) this.findViewById(R.id.rapidJSWebView);
		rapidJSWebView.init(this, new Handler(), false);

		loadFromIntent(getIntent(), url);
		loadCurrentUrlWithPlugins();

	}

	public void loadFromIntent(Intent i, String defaultUrl){
		String rapidURI = i.getDataString();
		String webURI = i.getStringExtra(Intent.EXTRA_TEXT);
		if (rapidURI != null){
			defaultUrl = parseRapidURL(i.getDataString());
		}
		else if (webURI != null){
			defaultUrl = webURI;
		}

		System.out.println("URI: " + defaultUrl);

		currentUrl = defaultUrl;
	}

	public String parseRapidURL(String rapidURI) {

		rapidURI = rapidURI.replaceFirst("rapid://.*?/+", "");
		return "http://" + rapidURI;
	}

	public void loadCurrentUrlWithPlugins(){
		rapidJSWebView.accelerate = true;
		rapidJSWebView.loadUrlWithPlugins(currentUrl);
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