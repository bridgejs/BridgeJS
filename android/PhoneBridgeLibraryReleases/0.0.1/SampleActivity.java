package org.bridgejs.android.phonebridge;

import org.bridgejs.android.phonebridge.library.DroidBridge;
import org.bridgejs.android.phonebridge.library.browser.BridgeJSBrowser;

import android.os.Bundle;
import android.view.Window;

public class SampleActivity extends DroidBridge {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.main);
		browser = (BridgeJSBrowser)findViewById(R.id.browser);
		super.onCreate(savedInstanceState);

		String sampleHttpUrl = "http://www.bridgejs.com/";
		//String sampleLocalUrl = "file:///android_assets/index.html";

		loadUrlWithPlugins(sampleHttpUrl);
		//loadUrlWithPlugins(sampleLocalUrl);
	}
}
