package org.bridgejs.android.phonebridge.library.browser;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class BridgeJSWebChromeClient extends WebChromeClient{
	
	private ProgressBar progressBar;
	
	public BridgeJSWebChromeClient(ProgressBar progressBar){
		this.progressBar = progressBar;
	}
	
	@Override
	public void onProgressChanged(WebView view, int progress)   
	{
		progressBar.setProgress(progress);
		if (progress == 100)
			progressBar.setVisibility(ProgressBar.GONE);
		else 
			progressBar.setVisibility(ProgressBar.VISIBLE);
	}
}
