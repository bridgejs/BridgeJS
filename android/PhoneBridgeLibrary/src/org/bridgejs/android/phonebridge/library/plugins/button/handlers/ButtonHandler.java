package org.bridgejs.android.phonebridge.library.plugins.button.handlers;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.view.KeyEvent;

public abstract class ButtonHandler {

	private ArrayList<Integer> callbacks;
	private PluginRequests requests;
	private AtomicBoolean isDoSuper;
	private AtomicBoolean hasIsDoSuperBeenSetTrue;
	private int thisKeyCode;
	
	public ButtonHandler(PluginRequests requests, int thisKeyCode) {
		callbacks = new ArrayList<Integer>();
		isDoSuper = new AtomicBoolean(true);
		hasIsDoSuperBeenSetTrue = new AtomicBoolean(false);
		this.requests = requests;
		
		this.thisKeyCode = thisKeyCode;
	}
	
	private void callCallbacks(KeyEvent event) {
		for (int callback : callbacks)
			requests.postJavascript("__gotButtonCallback(" + callback + ");", this);
	}
	
	private void resetState() {
		callbacks.clear();
		hasIsDoSuperBeenSetTrue.set(false);
		isDoSuper.set(true);
	}
	
	public boolean callback(KeyEvent event) {
		callCallbacks(event);
		boolean shouldDoSuper = isDoSuper.get();
		resetState();
		
		if (shouldDoSuper) {
			System.out.println("Sending fake key event");
			return requests.callSuperOnKeyDown(thisKeyCode, event);
		}
		else
			return true;
	}

	public void addToCallbacks(int callbackToAdd) {
		// TODO Auto-generated method stub
		callbacks.add(callbackToAdd);
	}

	public void setIsDoSuper(boolean state) {
		// TODO Auto-generated method stub
		if (!hasIsDoSuperBeenSetTrue.get() && state == true)
			hasIsDoSuperBeenSetTrue.set(true);
		
		isDoSuper.set(state);
	}
	
	public boolean getIsDoSuper() {
		return isDoSuper.get();
	}
	
	public boolean getHasIsDoSuperBeenSetTrue() {
		return hasIsDoSuperBeenSetTrue.get();
	}
}
