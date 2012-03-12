package org.bridgejs.android.phonebridge.library.plugins.button;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;

import android.view.KeyEvent;

public class ButtonJS {

	private PluginRequests requests;
	private ArrayList<Integer> backButtonCallbacks;
	private AtomicBoolean isDoBackButtonSuper;
	
	public ButtonJS(PluginRequests requests) {
		this.requests = requests;
		this.backButtonCallbacks = new ArrayList<Integer>();
		this.isDoBackButtonSuper = new AtomicBoolean(false);
		
		requests.addToOnBackButtonModifier(new ButtonRunnable() {

			public boolean run() {
				return ButtonJS.this.backButtonCallback();
			}
			
		});
		
	}
	
	private void callBackButtonCallbacks() {
		for (int callback : backButtonCallbacks)
			requests.postJavascript("__gotBackButtonCallback(" + callback + ");", this);
	}
	
	private boolean backButtonCallback() {
		callBackButtonCallbacks();
		
		if (isDoBackButtonSuper.get()) {
			KeyEvent fakeEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
			return requests.callSuperOnKeyDown(KeyEvent.KEYCODE_BACK, fakeEvent);
		}
		else
			return true;
	}
	
	public void registerNoSuperBackButtonDownCallback(int callback) {
		backButtonCallbacks.add(callback);
	}
	
	public void registerSuperBackButtonDownCallback(int callback) {
		if (!isDoBackButtonSuper.get()) {
			isDoBackButtonSuper.set(true);
		}
		backButtonCallbacks.add(callback);
	}
}
