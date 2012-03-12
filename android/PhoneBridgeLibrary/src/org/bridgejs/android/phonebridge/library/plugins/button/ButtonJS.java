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

			public boolean run(KeyEvent event) {
				return ButtonJS.this.backButtonCallback(event);
			}
			
		});
		
	}
	
	private void callBackButtonCallbacks() {
		for (int callback : backButtonCallbacks)
			requests.postJavascript("__gotBackButtonCallback(" + callback + ");", this);
		
		backButtonCallbacks.clear();
	}
	
	private boolean backButtonCallback(KeyEvent event) {
		callBackButtonCallbacks();
		
		if (isDoBackButtonSuper.get()) {
			System.out.println("Sending fake key event");
			return requests.callSuperOnKeyDown(KeyEvent.KEYCODE_BACK, event);
		}
		else
			return true;
	}
	
	public void registerNoSuperBackButtonDownCallback(int callback) {
		System.out.println("Registering with no super in java");
		backButtonCallbacks.add(callback);
	}
	
	public void registerSuperBackButtonDownCallback(int callback) {
		System.out.println("Registering with super in java");
		if (isDoBackButtonSuper.get() == false) {
			System.out.println("Before setting the atomic isDoBackButtonSuper");
			isDoBackButtonSuper.set(true);
		}
		backButtonCallbacks.add(callback);
	}
}
