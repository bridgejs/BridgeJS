package org.bridgejs.android.phonebridge.library.plugins.button;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;
import org.bridgejs.android.phonebridge.library.plugins.button.handlers.BackButtonHandler;
import org.bridgejs.android.phonebridge.library.plugins.button.handlers.ButtonHandler;
import org.bridgejs.android.phonebridge.library.plugins.button.handlers.HomeButtonHandler;
import org.bridgejs.android.phonebridge.library.plugins.button.handlers.MenuButtonHandler;
import org.bridgejs.android.phonebridge.library.plugins.button.handlers.VolumedownButtonHandler;
import org.bridgejs.android.phonebridge.library.plugins.button.handlers.VolumeupButtonHandler;

import android.view.KeyEvent;

public class ButtonJS {

	private PluginRequests requests;
	
	private BackButtonHandler backButton;
	private VolumedownButtonHandler volumedownButton;
	private VolumeupButtonHandler volumeupButton;
	private MenuButtonHandler menuButton;
	private HomeButtonHandler homeButton;
	
	public ButtonJS(PluginRequests requests) {
		this.requests = requests;
		
		this.backButton = new BackButtonHandler(requests);
		this.volumedownButton = new VolumedownButtonHandler(requests);
		this.volumeupButton = new VolumeupButtonHandler(requests);
		this.menuButton = new MenuButtonHandler(requests);
		this.homeButton = new HomeButtonHandler(requests);
	}
	
	public void registerNoSuperBackButtonDownCallback(int callback) {
		if (!backButton.getHasIsDoSuperBeenSetTrue())
			backButton.setIsDoSuper(false);
		
		backButton.addToCallbacks(callback);
	}
	
	public void registerSuperBackButtonDownCallback(int callback) {
		backButton.setIsDoSuper(true);
		backButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperVolumedownButtonDownCallback(int callback) {
		if (!volumedownButton.getHasIsDoSuperBeenSetTrue())
			volumedownButton.setIsDoSuper(false);
		
		volumedownButton.addToCallbacks(callback);
	}
	
	public void registerSuperVolumedownButtonDownCallback(int callback) {
		volumedownButton.setIsDoSuper(true);
		volumedownButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperVolumeupButtonDownCallback(int callback) {
		if (!volumeupButton.getHasIsDoSuperBeenSetTrue())
			volumeupButton.setIsDoSuper(false);
		
		volumeupButton.addToCallbacks(callback);
	}
	
	public void registerSuperVolumeupButtonDownCallback(int callback) {
		volumeupButton.setIsDoSuper(true);
		volumeupButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperMenuButtonDownCallback(int callback) {
		if (!menuButton.getHasIsDoSuperBeenSetTrue())
			menuButton.setIsDoSuper(false);
		
		menuButton.addToCallbacks(callback);
	}
	
	public void registerSuperMenuButtonDownCallback(int callback) {
		menuButton.setIsDoSuper(true);
		menuButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperHomeButtonDownCallback(int callback) {
		if (!homeButton.getHasIsDoSuperBeenSetTrue())
			homeButton.setIsDoSuper(false);
		
		homeButton.addToCallbacks(callback);
	}
	
	public void registerSuperHomeButtonDownCallback(int callback) {
		homeButton.setIsDoSuper(true);
		homeButton.addToCallbacks(callback);
	}
}
