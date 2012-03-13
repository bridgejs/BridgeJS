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
		System.out.println("Registering backbutton with no super in java");
		if (!backButton.getHasIsDoSuperBeenSetTrue())
			backButton.setIsDoSuper(false);
		
		backButton.addToCallbacks(callback);
	}
	
	public void registerSuperBackButtonDownCallback(int callback) {
		System.out.println("Registering backbutton with super in java");
		backButton.setIsDoSuper(true);
		backButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperVolumedownButtonDownCallback(int callback) {
		System.out.println("Registering volumedown with no super in java");
		if (!volumedownButton.getHasIsDoSuperBeenSetTrue())
			volumedownButton.setIsDoSuper(false);
		
		volumedownButton.addToCallbacks(callback);
	}
	
	public void registerSuperVolumedownButtonDownCallback(int callback) {
		System.out.println("Registering volumedown with super in java");
		volumedownButton.setIsDoSuper(true);
		volumedownButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperVolumeupButtonDownCallback(int callback) {
		System.out.println("Registering volumeup with no super in java");
		if (!volumeupButton.getHasIsDoSuperBeenSetTrue())
			volumeupButton.setIsDoSuper(false);
		
		volumeupButton.addToCallbacks(callback);
	}
	
	public void registerSuperVolumeupButtonDownCallback(int callback) {
		System.out.println("Registering volumeup with super in java");
		volumeupButton.setIsDoSuper(true);
		volumeupButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperMenuButtonDownCallback(int callback) {
		System.out.println("Registering menu with no super in java");
		if (!menuButton.getHasIsDoSuperBeenSetTrue())
			menuButton.setIsDoSuper(false);
		
		menuButton.addToCallbacks(callback);
	}
	
	public void registerSuperMenuButtonDownCallback(int callback) {
		System.out.println("Registering menu with super in java");
		menuButton.setIsDoSuper(true);
		menuButton.addToCallbacks(callback);
	}
	
	public void registerNoSuperHomeButtonDownCallback(int callback) {
		System.out.println("Registering home with no super in java");
		if (!homeButton.getHasIsDoSuperBeenSetTrue())
			homeButton.setIsDoSuper(false);
		
		homeButton.addToCallbacks(callback);
	}
	
	public void registerSuperHomeButtonDownCallback(int callback) {
		System.out.println("Registering home with super in java");
		homeButton.setIsDoSuper(true);
		homeButton.addToCallbacks(callback);
	}
}
