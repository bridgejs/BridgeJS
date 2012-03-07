package org.bridgejs.android.phonebridge.library.browser;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class BridgeJSWebChromeClient extends WebChromeClient{
	
	private ProgressBarUpdater progressBarUpdater;
	
	public BridgeJSWebChromeClient(ProgressBarUpdater progressBarUpdater){
		this.progressBarUpdater = progressBarUpdater;
	}
	
	@Override
	public void onProgressChanged(WebView view, int progress)   
	{
		progressBarUpdater.setProgress(progress);
	}
}
