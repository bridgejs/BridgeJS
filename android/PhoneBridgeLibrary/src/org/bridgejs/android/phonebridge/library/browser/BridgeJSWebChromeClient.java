package org.bridgejs.android.phonebridge.library.browser;

import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ActivityEventsModifier;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class BridgeJSWebChromeClient extends WebChromeClient{

	private ProgressBarUpdater progressBarUpdater;
	
	private Activity activity;

	public BridgeJSWebChromeClient(Activity activity, ProgressBarUpdater progressBarUpdater){
		this.progressBarUpdater = progressBarUpdater;
		this.activity = activity;
	}

	@Override
	public void onProgressChanged(WebView view, int progress)   
	{
		progressBarUpdater.setProgress(progress);
	}

	@Override
	public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
		Builder dialog = new AlertDialog.Builder(activity);
		dialog.setMessage(message);
		dialog.setCancelable(true);
		dialog.show();
		result.confirm();
		return true;
	}
}
