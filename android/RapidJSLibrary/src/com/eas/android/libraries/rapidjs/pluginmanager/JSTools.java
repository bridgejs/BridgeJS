package com.eas.android.libraries.rapidjs.pluginmanager;

import android.webkit.WebView;

public class JSTools {

	private WebView webView;
	
	public JSTools(WebView webView){
		this.webView = webView;
	}
	
	public void addJavascriptInterface(Object obj, String interfaceName){
		webView.addJavascriptInterface(obj, interfaceName);
	}
}
